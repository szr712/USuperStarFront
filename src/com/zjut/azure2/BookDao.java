package com.zjut.azure2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.jdt.internal.compiler.ast.FalseLiteral;

import com.sun.javafx.logging.PulseLogger;

public class BookDao extends BaseDao {

	public BookDao() {
		// TODO Auto-generated constructor stub
	}

	// 增加一个房型
	public boolean addBook(Book book) {
		String sql = "INSERT INTO Book VALUES(?,?,?,?,?,?)";
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, book.getNum());
			preparedStatement.setString(2, book.getType());
			preparedStatement.setString(3, book.getName());
			preparedStatement.setString(4, book.getCheckin());
			preparedStatement.setString(5, book.getCheckout());
			preparedStatement.setInt(6, book.getQuantity());
			preparedStatement.executeUpdate();
			connection.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	// 删除房型
	public boolean deleteBook(Book book) {
		String sql = "DELETE FROM Book WHERE Bnum=?";
		Connection connection;
		try {
			connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, book.getNum());
			preparedStatement.executeUpdate();
			connection.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	// 更改一个房型
	public boolean changeBook(Book book,String Bnum) {
		String sql="UPDATE Book	SET Bnum=?,Btype=?,Bname=?,Bcheckin=?,Bcheckout=?,Bquantity=? WHERE Bnum=?";
		Connection connection;
		try {
			connection=dataSource.getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, book.getNum());
			preparedStatement.setString(2, book.getType());
			preparedStatement.setString(3, book.getName());
			preparedStatement.setString(4, book.getCheckin());
			preparedStatement.setString(5, book.getCheckout());
			preparedStatement.setInt(6, book.getQuantity());
			preparedStatement.setString(7, Bnum);
			preparedStatement.executeUpdate();
			connection.close();
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}

	// 查询所有房型
	public ArrayList<Book> findBook() {

		ArrayList<Book> booklist = new ArrayList<>();
		String sql = "SELECT * FROM Book";
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement pStatement = connection.prepareStatement(sql);
			ResultSet resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				Book book = new Book();
				book.setNum(resultSet.getString("Bnum").trim());
				book.setType(resultSet.getString("Btype").trim());
				book.setName(resultSet.getString("Bname").trim());
				book.setCheckin(resultSet.getString("Bcheckin").trim());
				book.setCheckout(resultSet.getString("Bcheckout").trim());
				book.setQuantity(resultSet.getInt("Bquantity"));
				booklist.add(book);
			}
			connection.close();
			return booklist;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

}
