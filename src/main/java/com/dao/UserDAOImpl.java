package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.models.Reimbursement;
import com.models.User;

public class UserDAOImpl implements UserDAO {
	
	private static String url = "jdbc:oracle:thin:@db0715javausf.chts6t7vjaia.us-east-2.rds.amazonaws.com:1521:orcl";
	private static String username = "user0715java";
	private static String password = "p4ssw0rd";
	
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean createUser(User u) {
		try (Connection conn = DriverManager.getConnection(url, username, password)) {

		PreparedStatement ps = conn.prepareStatement("INSERT INTO Users VALUES(?,?,?,?,?,?,?)");
		ps.setInt(1, u.getUserId());
		ps.setString(2, u.getUserName());
		ps.setString(3, u.getPassWord());
		ps.setString(4, u.getFirstName());
		ps.setString(5, u.getLastName());
		ps.setString(6, u.getEmail());
		ps.setInt(7, u.getRoleId());
		
		ps.executeUpdate();		
		return true;

		
	} catch (SQLException ex) {
		ex.printStackTrace();
	}
		return false;
	}

	@Override
	public User selectUserById(int userId) {
		User u = null;
	try (Connection conn = DriverManager.getConnection(url, username, password)) {

		PreparedStatement ps = conn.prepareStatement("SELECT * FROM Users WHERE user_id =?");
		ps.setInt(1, userId);

		ps.executeQuery();
		ResultSet rs = ps.getResultSet();
		while (rs.next()) {
			u = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getString(6), rs.getInt(7));
		}

	} catch (SQLException e) {
		e.printStackTrace();
	}
	return u;
	}

	@Override
	public List<User> selectAllUsers() {
		List<User> u = new ArrayList<User>();
		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Users");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				u.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getInt(7)));
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return u;
	}

	@Override
	public int updateUser() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean deleteUser(int userId) {
		try (Connection conn = DriverManager.getConnection(url, username, password)) {

			PreparedStatement ps = conn.prepareStatement("DELETE FROM Users WHERE user_id=?");
			ps.setInt(1, userId);
			ps.executeUpdate();
			return true;
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}

}
