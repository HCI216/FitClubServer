package com.nju.FitClubServer.dao.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.util.Progressable;
import org.apache.hadoop.util.ReflectionUtils;
import org.apache.hadoop.io.IOUtils;


import com.nju.FitClubServer.dao.WeightChangeRecordDao;
import com.nju.FitClubServer.model.User;
import com.nju.FitClubServer.model.WeightChangeRecord;

public class WeightChangeRecordDaoImpl implements WeightChangeRecordDao {

	private static Configuration conf = new Configuration();
	String url = "jdbc:mysql://localhost:3306/fitclub/data";

	public Connection getCon() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(url, "root", "nzbbzlks");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}

	
//	public static void main(String[] args) throws Exception{
//		WeightChangeRecordDao dao = new WeightChangeRecordDaoImpl();
//		User user = new User();
//		user.setUserID("201504301427");
//		user.setBirthDay("19940511");
//		user.setHeight(173);
//		WeightChangeRecord weightChangeRecord = new WeightChangeRecord();
//		weightChangeRecord.setStartTime("20150505");
//		weightChangeRecord.setEndTime("20150510");
//		weightChangeRecord.setStartWeight(180);
//		weightChangeRecord.setEndWeight(160);
//		weightChangeRecord.setUserID("201504301427");
//		weightChangeRecord.setWeightChangeRecordRecordID("01");
//		dao.addWeightChangeRecordDao(user, weightChangeRecord);
//	}
	
	public boolean addWeightChangeRecordDao(User user,
			WeightChangeRecord weightChangeRecord) throws Exception {
		// TODO Auto-generated method stub

		String filePath = "/home/xxd/ibeyondy/hadoop-2.6.0/fitclub/data.txt";
		String remotePath = "hdfs://localhost:9000/fitclub/data/data.txt";
		
		writeToFile(weightChangeRecord, user ,filePath);
		deleteData(remotePath);
		copyFromLocal(remotePath,filePath);
		
		Connection con = getCon();
		if (con == null)
			return false;
		String query = "insert into weightChangeRecord values(?,?,?,?,?,?)";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, weightChangeRecord.getWeightChangeRecordRecordID());
		stmt.setString(2, weightChangeRecord.getUserID());
		stmt.setString(3, weightChangeRecord.getStartTime());
		stmt.setString(4, weightChangeRecord.getEndTime());
		stmt.setDouble(5, weightChangeRecord.getStartWeight());
		stmt.setDouble(6, weightChangeRecord.getEndWeight());
		stmt.execute();
		return true;
	}

	private void writeToFile(WeightChangeRecord weightChangeRecord, User user,String filePath)
			throws Exception {
		String userID = user.getUserID();
		double changeWeight = weightChangeRecord.getEndWeight()
				- weightChangeRecord.getStartWeight();
		File file = new File(filePath);
		FileWriter fw = new FileWriter(file, true);

		String str = weightChangeRecord.getWeightChangeRecordRecordID()
				+ " "
				+ user.getAge()
				+ " "
				+ user.getHeight()
				+ " "
				+ weightChangeRecord.getStartWeight()
				+ " "
				+ weightChangeRecord.duringTime()
				+ " "
				+ (0 - changeWeight);

		fw.write(str + "\n");
		fw.flush();
	}
	
	public static void deleteData(String path) throws Exception {
		FileSystem fs = FileSystem.get(URI.create(path), conf);
		if (fs.exists(new Path(path))) {
			fs.delete(new Path(path));
		}
	}
	
	public static void copyFromLocal(String remoteData,String localPath) throws Exception {
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
	

	public ArrayList<WeightChangeRecord> getAllWeightChangeRecord()
			throws Exception {
		// TODO Auto-generated method stub
		Connection con = getCon();
		if (con == null)
			return null;
		ArrayList<WeightChangeRecord> weightChangeRecordList = new ArrayList<WeightChangeRecord>();
		String query = "select * from weightChangeRecord";
		PreparedStatement stmt = con.prepareStatement(query);
		ResultSet set = stmt.executeQuery();
		while (set.next()) {
			WeightChangeRecord record = new WeightChangeRecord();
			record.setWeightChangeRecordRecordID(set
					.getString("weightChangeRecordID"));
			record.setUserID(set.getString("userID"));
			record.setStartTime(set.getString("startTime"));
			record.setEndTime(set.getString("endTime"));
			record.setStartWeight(set.getDouble("startWeight"));
			record.setEndWeight(set.getDouble("endWeight"));
			weightChangeRecordList.add(record);
		}
		return weightChangeRecordList;
	}

	public WeightChangeRecord getWeightChangeRecordByID(String id)
			throws Exception {
		// TODO Auto-generated method stub
		Connection con = getCon();
		if (con == null)
			return null;
		String query = "select * from weightChangeRecord where weightChangeRecordID=?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, id);
		ResultSet set = stmt.executeQuery();
		while (set.next()) {
			WeightChangeRecord record = new WeightChangeRecord();
			record.setWeightChangeRecordRecordID(set
					.getString("weightChangeRecordID"));
			record.setUserID(set.getString("userID"));
			record.setStartTime(set.getString("startTime"));
			record.setEndTime(set.getString("endTime"));
			record.setStartWeight(set.getDouble("startWeight"));
			record.setEndWeight(set.getDouble("endWeight"));
			return record;

		}
		return null;
	}

}
