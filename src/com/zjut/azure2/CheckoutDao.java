package com.zjut.azure2;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class CheckoutDao extends BaseDao {

	// 根据房号获得结账所需信息
	public CheckoutData findCheckout(String num) {
		CheckoutData checkoutData = new CheckoutData();
		String sql = "SELECT Room.Rnum,Room.Rname,Room.Rcheckin,Room.Rcheckout,Room.Rtype,Type.Tprice FROM Room,Type WHERE Room.Rtype=Type.Ttype and Room.Rnum=?";
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, num);
			ResultSet resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				checkoutData.setNum(resultSet.getString("Rnum").trim());
				checkoutData.setName(resultSet.getString("Rname").trim());
				checkoutData.setCheckin(resultSet.getString("Rcheckin").trim());
				checkoutData.setCheckout(resultSet.getString("Rcheckout").trim());
				checkoutData.setType(resultSet.getString("Rtype").trim());
				checkoutData.setPrice(resultSet.getFloat("Tprice"));
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Date in = format.parse(checkoutData.getCheckin());
				Date out = format.parse(checkoutData.getCheckout());
				
				Calendar inCalendar = Calendar.getInstance();
				Calendar outCalendar = Calendar.getInstance();
				inCalendar.setTime(in);
				outCalendar.setTime(out);
				int day1 = inCalendar.get(Calendar.DAY_OF_YEAR);
				int day2 = outCalendar.get(Calendar.DAY_OF_YEAR);

				int year1 = inCalendar.get(Calendar.YEAR);
				int year2 = outCalendar.get(Calendar.YEAR);
				if (year1 != year2) {
					int timeDistance = 0;
					for (int i = year1; i < year2; i++) {
						if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) // 闰年
						{
							timeDistance += 366;
						} else // 不是闰年
						{
							timeDistance += 365;
						}
					}

					checkoutData.setDays(timeDistance + (day2 - day1));
				} else {
					//System.out.println("判断day2 - day1 : " + (day2 - day1));
					checkoutData.setDays((day2 - day1));
				}
				checkoutData.setTotal(checkoutData.getPrice()*checkoutData.getDays());

			}
			connection.close();
			return checkoutData;
		} catch (SQLException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}
	//将结账房间变空闲
	public boolean changeStatus( String num) {
		String sql="UPDATE Room	SET Rstatus=?,Rname=null,Rcheckin=null,Rcheckout=null,Ridtype=null,Rid=null WHERE Rnum=?";
		Connection connection;
		try {
			connection=dataSource.getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			
			preparedStatement.setString(1, "空闲");
			preparedStatement.setString(2, num);

			preparedStatement.executeUpdate();
			connection.close();
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public CheckoutDao() {
		// TODO Auto-generated constructor stub
	}

}
