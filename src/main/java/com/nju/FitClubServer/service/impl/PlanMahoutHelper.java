package com.nju.FitClubServer.service.impl;

import org.apache.hadoop.conf.Configuration;

import com.nju.FitClubServer.dao.UserDao;
import com.nju.FitClubServer.dao.WeightChangeRecordDao;
import com.nju.FitClubServer.dao.WeightRecordDao;
import com.nju.FitClubServer.dao.impl.UserDaoImpl;
import com.nju.FitClubServer.dao.impl.WeightChangeRecordDaoImpl;
import com.nju.FitClubServer.dao.impl.WeightRecordDaoImpl;
import com.nju.FitClubServer.model.Plan;
import com.nju.FitClubServer.model.User;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.util.Progressable;
import org.apache.hadoop.util.ReflectionUtils;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.SequenceFile.CompressionType;
import org.apache.hadoop.io.Writable;
import org.apache.mahout.clustering.classify.WeightedPropertyVectorWritable;
import org.apache.mahout.clustering.classify.WeightedVectorWritable;
import org.apache.mahout.clustering.conversion.InputDriver;
import org.apache.mahout.clustering.kmeans.KMeansDriver;
import org.apache.mahout.clustering.kmeans.RandomSeedGenerator;
import org.apache.mahout.common.distance.DistanceMeasure;
import org.apache.mahout.common.distance.EuclideanDistanceMeasure;
import org.apache.mahout.math.NamedVector;
import org.apache.mahout.math.SequentialAccessSparseVector;
import org.apache.mahout.math.Vector;
import org.apache.mahout.math.VectorWritable;
import org.apache.mahout.utils.clustering.ClusterDumper;
import org.apache.mahout.math.Vector.Element;

public class PlanMahoutHelper {
	private User user;

	private WeightChangeRecordDao dao = new WeightChangeRecordDaoImpl();
	private WeightRecordDao weightDao = new WeightRecordDaoImpl();
	private UserDao userDao = new UserDaoImpl();
	private static String remotePath = "hdfs://localhost:9000/fitclub";
	private static String tmpData = "/home/xxd/ibeyondy/hadoop-2.6.0/fitclub/reverseData/data.txt";
	private static String permanentData = "/home/xxd/ibetondy/haodop-2.6.0/fitclub/permanentData/data.txt";
	private static ArrayList<String> IDList = new ArrayList<String>();

	private static Configuration conf = new Configuration();

	public PlanMahoutHelper(User userInfo) throws Exception {
		this.user = userInfo;
		writeToTmpData();
	}

	public void writeToTmpData() throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(new File(
				permanentData)));
		BufferedWriter bw = new BufferedWriter(
				new FileWriter(new File(tmpData)));
		String tmp = "";
		while ((tmp = br.readLine()) != null) {
			bw.write(tmp + "\n");
			bw.flush();
		}
		bw.write("19940511142011 " + user.getAge() + " " + user.getHeight()
				+ " " + weightDao.getWeightByUserID(user.getUserID()) + " "
				+ user.getLoseWeightTime() + " " + user.getLoseWeight());
		bw.flush();
		bw.close();
		br.close();

	}

	public static void deleteData(String path) throws Exception {
		FileSystem fs = FileSystem.get(URI.create(path), conf);
		if (fs.exists(new Path(path))) {
			fs.delete(new Path(path));
		}
	}

	public static void toSeq(String dataInput, String seqOutput)
			throws Exception {
		InputDriver.runJob(new Path(dataInput), new Path(seqOutput),
				"org.apache.mahout.math.RandomAccessSparseVector");
	}

	public static void toNamedSeq(String seqPath, String namedSeqPath)
			throws Exception {
		FileSystem fs = FileSystem.get(URI.create(namedSeqPath), conf);
		SequenceFile.Reader reader = new SequenceFile.Reader(fs, new Path(
				seqPath), conf);
		Writable reader_key = (Writable) ReflectionUtils.newInstance(
				reader.getKeyClass(), conf);
		Writable reader_value = (Writable) ReflectionUtils.newInstance(
				reader.getValueClass(), conf);

		SequenceFile.Writer writer = SequenceFile.createWriter(fs, conf,
				new Path(namedSeqPath), LongWritable.class,
				VectorWritable.class, CompressionType.BLOCK);

		Vector vector = null;

		final LongWritable named_key = new LongWritable();
		final VectorWritable named_value = new VectorWritable();

		long line_num = 0;

		while (reader.next(reader_key, reader_value)) {
			System.out.println("key : " + reader_key);
			System.out.println("value : " + reader_value);
			String tmp = modifyValue(reader_value.toString());
			StringTokenizer st = new StringTokenizer(tmp, ",");
			/*
			 * Set the Key of the NamedVector
			 */
			while (st.hasMoreTokens()) {
				String s = st.nextToken();
				if (s.startsWith("0:")) {
					String k = modifyKey(modifyStr(s));
					vector = new NamedVector(
							new SequentialAccessSparseVector(5), k);
					break;
				}
			}
			/*
			 * Add Value to NamedVector
			 */
			st = new StringTokenizer(tmp, ",");
			int index = 0;
			while (st.hasMoreTokens()) {
				String s = st.nextToken();
				if (s.startsWith("0:"))
					continue;
				vector.set(index++, Double.parseDouble(modifyStr(s)));
			}
			named_value.set(vector);
			named_key.set(line_num++);
			writer.append(named_key, named_value);
		}
		reader.close();
		writer.close();

	}

	public static void kmeans(String clusterSeeds, String namedSeq,
			String output, int k) throws Exception {
		Path clustersSeeds = new Path(clusterSeeds);
		Path seqFilePath = new Path(namedSeq);
		DistanceMeasure measure = new EuclideanDistanceMeasure();
		clustersSeeds = RandomSeedGenerator.buildRandom(conf, seqFilePath,
				clustersSeeds, k, measure);
		KMeansDriver.run(new Path(namedSeq), clustersSeeds, new Path(output),
				0.0001, 5, true, 0.0, false);
	}

	public static int readData(String dataPath, String ID) throws Exception {
		FileSystem fs = FileSystem.get(URI.create(dataPath), conf);
		SequenceFile.Reader reader = new SequenceFile.Reader(fs, new Path(
				dataPath), conf);
		HashMap<String, HashMap<String, Vector>> clusterList = new HashMap<String, HashMap<String, Vector>>();
		IntWritable key = new IntWritable();
		WeightedPropertyVectorWritable value = new WeightedPropertyVectorWritable();

		IDList.clear();

		String clusterNo = "";
		while (reader.next(key, value)) {
			NamedVector vector = (NamedVector) value.getVector();
			if (vector.getName().equals(ID)) {
				clusterNo = key.toString();
			}
		}

		reader = new SequenceFile.Reader(fs, new Path(dataPath), conf);

		File file = new File(tmpData);
		if (!file.exists())
			file.createNewFile();
		else {
			file.delete();
			file.createNewFile();
		}
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		int pp = 0;
		while (reader.next(key, value)) {
			if (key.toString().equals(clusterNo)) {
				NamedVector vector = (NamedVector) value.getVector();
				String result = vector.getName() + " ";
				IDList.add(vector.getName());
				int s = vector.getDelegate().size();
				int tmp = 0;
				for (Element element : vector.getDelegate().all()) {
					result += ((int) element.get() + "");
					if (tmp != s - 1)
						result += " ";
					tmp++;
					pp++;
				}
				bw.write(result);
				bw.newLine();
			}
		}
		bw.close();

		return pp;
	}

	public static String modifyValue(String value) {
		String result = "";
		char[] cList = value.toCharArray();
		for (int i = 1; i < cList.length - 1; i++) {
			result += cList[i];
		}
		return result;
	}

	public static String modifyStr(String input) {
		String result = "";
		char[] cList = input.toCharArray();
		int i = 0;
		for (; i < cList.length; i++) {
			if (cList[i] == ':')
				break;
		}
		for (int k = i + 1; k < cList.length; k++) {
			result += cList[k];
		}
		return result;
	}

	public static String modifyKey(String key) {
		String result = "";
		char[] keyList = key.toCharArray();
		for (int i = 0; i < key.length(); i++) {
			if (keyList[i] == '.')
				continue;
			if (keyList[i] == 'E')
				break;
			result += keyList[i];
		}
		return result;
	}

	public static void copyFromLocal(String remoteData, String localPath)
			throws Exception {
		InputStream in = new BufferedInputStream(new FileInputStream(new File(
				localPath)));
		FileSystem fs = FileSystem.get(URI.create(remoteData), conf);
		OutputStream out = fs.create(new Path(remoteData), new Progressable() {

			public void progress() {
				// TODO Auto-generated method stub
				System.out.println("*");
			}

		});
		IOUtils.copyBytes(in, out, 4096, true);
	}

	public static void printResult(String output, String clusteredPoints)
			throws Exception {
		Path outGlobPath = new Path(output, "clusters-*-final");
		Path clusteredPointsPath = new Path(clusteredPoints);
		System.out
				.printf("Dumping out clusters from clusters: %s and clusteredPoints: %s\n",
						outGlobPath, clusteredPointsPath);

		ClusterDumper clusterDumper = new ClusterDumper(outGlobPath,
				clusteredPointsPath);
		clusterDumper.printClusters(null);
	}

	public String run() throws Exception {

		int index = 0;

		int leftNum = dao.getAllWeightChangeRecord().size();

		boolean pp = false;

		while (true) {

			if (index != 0) {
				copyFromLocal(remotePath + "/tmp/data.txt", tmpData);
			}

			deleteData(remotePath + "/seq");
			deleteData(remotePath + "/nameSeq");
			deleteData(remotePath + "/output");
			deleteData(remotePath + "/clusterSeed");

			// copyFromLocal(remotePath + "/input/data.txt");
			toSeq(remotePath + "/data", remotePath + "/seq");
			toNamedSeq(remotePath + "/seq/part-m-00000", remotePath
					+ "/namedSeq/result.data");

			kmeans(remotePath + "/clusterSeed", remotePath + "/namedSeq",
					remotePath + "/output", (int) Math.sqrt(leftNum));
			printResult(remotePath + "/output", remotePath
					+ "/output/clusteredPoints");
			leftNum = readData(remotePath
					+ "/output/clusteredPoints/part-m-00000", "19940511142011");
			if (leftNum <= 4)
				break;
		}

		/*
		 * 性别匹配
		 */

		String weightChangeID = "";

		for (int i = 0; i < IDList.size(); i++) {
			String userID = dao.getWeightChangeRecordByID(IDList.get(i))
					.getUserID();
			User tmpUser = userDao.searchUserByID(userID);
			if (!"19940511142011".equals(IDList.get(i))
					&& user.getSex().equals(tmpUser.getSex())) {
				weightChangeID = IDList.get(i);
				break;
			}
		}

		return weightChangeID;
	}

//	public static void main(String[] args) throws Exception {
//		PlanMahoutHelper helper = new PlanMahoutHelper(new User());
//		helper.run();
//	}

}
