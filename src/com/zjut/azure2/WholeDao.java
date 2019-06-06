package com.zjut.azure2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.jdt.internal.compiler.ast.FalseLiteral;

import com.sun.javafx.logging.PulseLogger;

public class WholeDao extends BaseDao {

	public WholeDao() {
		// TODO Auto-generated constructor stub
	}


	// 查询所有房型
	public ArrayList<WholeData> findWhole() {

		ArrayList<WholeData> Wholelist = new ArrayList<>();
		String sql = "SELECT Room.Rnum,Room.Rtype,Room.Rstatus,Room.Rname,Room.Rcheckin,Room.Rcheckout,Room.Rremark,Room.Rid,Room.Ridtype,Type.Tprice FROM Room,Type WHERE Room.Rtype=Type.Ttype ";
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement pStatement = connection.prepareStatement(sql);
			ResultSet resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				//Book book = new Book();
				WholeData WholeData =new WholeData();				 
				WholeData.setNum(resultSet.getString("Rnum").trim());					
				WholeData.setType(resultSet.getString("Rtype").trim());
				WholeData.setStatus(resultSet.getString("Rstatus").trim());
				WholeData.setPrice(resultSet.getFloat("Tprice"));
				
				
				if(resultSet.getString("Rremark")!=null&&resultSet.getString("Rremark")!="")
				WholeData.setMark(resultSet.getString("Rremark").trim());
				else WholeData.setMark("无");
				
				if(resultSet.getString("Rcheckin")!=null&&resultSet.getString("Rcheckin")!="")
				WholeData.setCheckin(resultSet.getString("Rcheckin").trim());
				else WholeData.setCheckin("/");
				
				if(resultSet.getString("Rcheckout")!=null&&resultSet.getString("Rcheckout")!="")
				WholeData.setCheckout(resultSet.getString("Rcheckout").trim());
				else WholeData.setCheckout("/");
				
				if(resultSet.getString("Rid")!=null&&resultSet.getString("Rid")!="")
				WholeData.setId(resultSet.getString("Rid").trim());
				else WholeData.setId("/");
				
				if(resultSet.getString("Ridtype")!=null&&resultSet.getString("Ridtype")!="")
				WholeData.setIdtype(resultSet.getString("Ridtype").trim());
				else WholeData.setIdtype("/");
				
				if(resultSet.getString("Rname")!=null&&resultSet.getString("Rname")!="")
				WholeData.setName(resultSet.getString("Rname").trim());
				else WholeData.setName("/");
				
				Wholelist.add(WholeData);			

			}
			connection.close();
			return Wholelist;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

}