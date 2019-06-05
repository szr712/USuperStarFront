package com.zjut.azure2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class checkinDao extends BaseDao{

	public checkinDao() {
		// TODO Auto-generated constructor stub
	}
	
	//获得空闲或预约房间号
	public ArrayList<checkinData> findRoom() {

		ArrayList<checkinData> checkinDatas = new ArrayList<>();
		String sql = "SELECT Room.Rnum,Room.Rtype,Room.Rstatus,Room.Rname,Room.Rcheckin,Room.Rcheckout,Type.Tprice FROM Room,Type WHERE Room.Rtype=Type.Ttype and Room.Rstatus<>'有客'";
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement pStatement = connection.prepareStatement(sql);
			ResultSet resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				checkinData checkinData=new checkinData();
				checkinData.setNum(resultSet.getString("Rnum").trim());
				checkinData.setType(resultSet.getString("Rtype").trim());
				checkinData.setPrice(resultSet.getFloat("Tprice"));
				checkinData.setStatus(resultSet.getString("Rstatus").trim());
				if(resultSet.getString("Rname")!=null) {
					checkinData.setName(resultSet.getString("Rname").trim());
				}
				if(resultSet.getString("Rcheckin")!=null) {
					checkinData.setCheckin(resultSet.getString("Rcheckin").trim());
				}
				if(resultSet.getString("Rcheckout")!=null) {
					checkinData.setCheckout(resultSet.getString("Rcheckout").trim());
				}
				
				checkinDatas.add(checkinData);
			}
			connection.close();
			return checkinDatas;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}
	//改变对应的房间状态
	public Boolean changeStatus(checkinData checkinData) {
		String sql="UPDATE Room	SET Rstatus=?,Rname=?,Rcheckin=?,Rcheckout=?,Ridtype=?,Rid=? WHERE Rnum=?";
		Connection connection;
		try {
			connection=dataSource.getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, "有客");
			preparedStatement.setString(2, checkinData.getName());
			preparedStatement.setString(3, checkinData.getCheckin());
			preparedStatement.setString(4, checkinData.getCheckout());
			preparedStatement.setString(5, checkinData.getIdtype());
			preparedStatement.setString(6, checkinData.getId());
			preparedStatement.setString(7, checkinData.getNum());
			preparedStatement.executeUpdate();
			connection.close();
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}
}
