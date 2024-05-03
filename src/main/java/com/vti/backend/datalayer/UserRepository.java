package com.vti.backend.datalayer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.vti.backend.utils.JDBCUtils;
import com.vti.backend.utils.Validate;
import com.vti.entity.Users;

public class UserRepository implements IUserRepository {
	@Override
	public boolean insertUser(String username, String password, String email, String phone) throws SQLException {
		if(password.length() < 6) {
			System.out.println("Password needs minimum 6 charaters");
			return false;
		}

		if(!Validate.isValidEmail(email)) {
			System.out.println("Email is not correct!");
			return false;
		}

		if(!Validate.isValidPhone(phone)) {
			System.out.println("Phone is not correct!");
			return false;
		}

		Connection con = null;
		PreparedStatement checkUserPstm = null;
		PreparedStatement insertUserPstm = null;
		ResultSet rs = null;

		try {
			con = JDBCUtils.getConnection();

			String checkUserSql = "SELECT COUNT(*) FROM users WHERE username = ?";
			checkUserPstm = con.prepareStatement(checkUserSql);
			checkUserPstm.setString(1, username);
			rs = checkUserPstm.executeQuery();

			if (rs.next() && rs.getInt(1) > 0) {
				System.out.println("Username already exists!");
				return false;
			}


			String insertUserSql = "INSERT INTO users(username, password) VALUES(?, ?)";
			insertUserPstm = con.prepareStatement(insertUserSql);
			insertUserPstm.setString(1, username);
			insertUserPstm.setString(2, password);
			int affectedRows = insertUserPstm.executeUpdate();

			return affectedRows > 0;

		} finally {
			JDBCUtils.closeConnection(con, rs, checkUserPstm, (CallableStatement) insertUserPstm);
		}
	}

	@Override
	public boolean checkExists(String username, String password) throws SQLException {
		Connection con = JDBCUtils.getConnection();
		String sql = "select * from users where username = ? and password = ?";
		PreparedStatement pstm = con.prepareStatement(sql);
		pstm.setString(1, username);
		pstm.setString(2, password);
		ResultSet rs = pstm.executeQuery();
		if (rs != null) {
			JDBCUtils.closeConnection(con, rs, pstm, null);
			return true;
		}
		JDBCUtils.closeConnection(con, rs, pstm, null);
		return false;
	}

	@Override
	public List<Users> findAll() throws SQLException {
		List<Users> userList = new ArrayList<>();
		Connection con = JDBCUtils.getConnection();
		String sql = "select * from users order by userId";
		PreparedStatement pstm = con.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			Users user = new Users();
			user.setUserID(rs.getInt("userId"));
			user.setUserName(rs.getString("username"));
			user.setAddress(rs.getString("address"));
			user.setCreated_by(rs.getString("created_by"));
			user.setCreated_date(rs.getDate("created_date").toLocalDate());
			userList.add(user);
		}

		return userList;
	}

	@Override
	public Optional<Users> findById(int userId) {
		Optional<Users> optionalUser = Optional.empty();

		try (Connection con = JDBCUtils.getConnection()) {
			String sql = "SELECT * FROM users WHERE userId = ?";
			try (PreparedStatement pstm = con.prepareStatement(sql)) {
				pstm.setInt(1, userId);
				try (ResultSet rs = pstm.executeQuery()) {
					if (rs.next()) {
						Users users = new Users();

						users.setUserID(rs.getInt("userId"));
						users.setUserName(rs.getString("username"));
						users.setAddress(rs.getString("address"));
						users.setCreated_by(rs.getString("created_by"));
						users.setCreated_date(rs.getDate("created_date").toLocalDate());

						optionalUser = Optional.of(users);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace(); // Handle or log the SQLException
		}

		return optionalUser;
	}
}
