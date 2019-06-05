package com.zjut.azure2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.jdt.internal.compiler.ast.FalseLiteral;

import com.sun.javafx.logging.PulseLogger;

public class TypeDao extends BaseDao {

	public TypeDao() {
		// TODO Auto-generated constructor stub
	}

	// 查询所有房型
	public ArrayList<Type> findType() {

		ArrayList<Type> typelist = new ArrayList<>();
		String sql = "SELECT * FROM Type";
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement pStatement = connection.prepareStatement(sql);
			ResultSet resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				Type type = new Type();
				type.setType(resultSet.getString("Ttype").trim());
				type.setBed(resultSet.getString("Tbed").trim());
				type.setCapability(resultSet.getInt("Tcapability"));
				type.setWifi(resultSet.getBoolean("Twifi"));
				type.setRoomsize(resultSet.getInt("Troomsize"));
				type.setPrice(resultSet.getFloat("Tprice"));
				typelist.add(type);

			}
			connection.close();
			return typelist;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

}
