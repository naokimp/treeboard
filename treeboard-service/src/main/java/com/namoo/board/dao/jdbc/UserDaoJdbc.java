package com.namoo.board.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.namoo.board.dao.UserDao;
import com.namoo.board.domain.User;

@Repository
public class UserDaoJdbc implements UserDao {

	private ReturnResources returnResources = new ReturnResources();

	@Autowired
	private DataSource dataSource;
	
	@Override
	public List<User> readAllUsers() {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<User> userList = new ArrayList<User>();
		
		try {
			conn = dataSource.getConnection();
			
			String sql = "SELECT user_id, passwd, name, email, nickname FROM user_tb";
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				String userId = rset.getString("user_id");	// user ID
				String passwd = rset.getString("passwd"); // user password
				String name = rset.getString("name");	// name
				String email = rset.getString("email");	// email
				String nickname = rset.getString("nickname");	// nickname
				
				System.out.println("\n" + "user ID : " + userId + "\n" + "name : " + name
						+ "\n" + "email : " + email + "\n" + "nickname : " + nickname);
				User users = new User(userId, passwd, name, email, nickname);
				userList.add(users);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			returnResources.returnResources(rset, pstmt, conn);
		}
		return userList;
	}

	@Override
	public User readUser(String userId) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		User users = null;
		
		try {
			conn = dataSource.getConnection();
			
			String sql = "SELECT user_id, passwd, name, email, nickname FROM user_tb WHERE user_id = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				String user_id = rset.getString("user_id");	// user ID
				String passwd = rset.getString("passwd");;	// user password
				String name = rset.getString("name");	// name
				String email = rset.getString("email");	// email
				String nickname = rset.getString("nickname");	// nickname
				
				System.out.println("\n" + "user ID : " + user_id + "\n" + "name : " + name
						+ "\n" + "email : " + email + "\n" + "nickname : " + nickname);
				users = new User(userId, passwd, name, email, nickname);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			returnResources.returnResources(rset, pstmt, conn);
		}
		return users;
	}

	@Override
	public void createUser(User user) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "INSERT INTO user_tb (user_id, passwd, name, email, nickname)"
					+ "VALUES (?, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getEmail());
			pstmt.setString(5, user.getNickname());
			
			int count = pstmt.executeUpdate();
			System.out.println(count + "개의 쿼리가 실행되었습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			returnResources.returnResources(pstmt, conn);
		}
		
	}

	@Override
	public void updateUser(User user) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "UPDATE user_tb SET passwd = ?, email = ?, nickname = ?"
					+ "WHERE user_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, user.getPassword());
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3, user.getNickname());
			pstmt.setString(4, user.getUserId());
			
			int count = pstmt.executeUpdate();
			System.out.println(count + "개의 쿼리가 실행되었습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			returnResources.returnResources(pstmt, conn);
		}
	}

	@Override
	public void removeUser(String userId) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "DELETE FROM user_tb WHERE user_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			
			int count = pstmt.executeUpdate();
			System.out.println(count + "개의 쿼리가 실행되었습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			returnResources.returnResources(pstmt, conn);
		}
	}
}
