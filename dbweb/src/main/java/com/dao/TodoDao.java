package com.dao;


import java.sql.Connection;
import java.sql.Driver;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.DriverManager;
import com.dto.TodoDto;

public class TodoDao {

	private static String dburl = "jdbc:mysql://localhost:3306/connectdb?serverTimezone=Asia/Seoul&useSSL=false";
	private static String dbUser = "connectuser";
	private static String dbpasswd = "1205";
	
	public List<TodoDto> getTodos(){
		
		List<TodoDto> list = new ArrayList<>();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String sql = "SELECT id, title, name, sequence, regdate, type FROM todo ORDER BY regdate";
		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
				PreparedStatement ps = conn.prepareStatement(sql)) {
			
			try (ResultSet rs = ps.executeQuery()) {

				while (rs.next()) {
					long id = rs.getLong("id");
					String title = rs.getString("title");
					String name = rs.getString("name");
					int sequence = rs.getInt("sequence");
					String regdate = rs.getString("regdate");
					String type = rs.getString("type");
					
					TodoDto todo = new TodoDto(id, name, regdate, sequence, title, type);
					list.add(todo);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	public int addTodo(TodoDto todo) {
		int insertCount = 0;
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
			String sql = "INSERT INTO todo(title, name, sequence) VALUES(?, ?, ?)";
			
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, todo.getTitle());
			ps.setString(2, todo.getName());
			ps.setInt(3, todo.getSequence());
			
			insertCount = ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(ps!=null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return insertCount;
	}
	public int updateTodo(TodoDto todo) {
		int updateCount = 0;
		
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName( "com.mysql.cj.jdbc.Driver" );
			
			conn = DriverManager.getConnection (dburl, dbUser, dbpasswd );
			
			if(todo.getType().equals("TODO")) {
				String sql = "update todo set type = 'DOING' where id = ?";
				ps = conn.prepareStatement(sql);
				ps.setLong(1, todo.getId());
				updateCount = ps.executeUpdate();
			}
			else if(todo.getType().equals("DOING")) {
				String sql = "update todo set type = 'DONE' where id = ?";
				ps = conn.prepareStatement(sql);
				ps.setLong(1, todo.getId());
				updateCount = ps.executeUpdate();
			}

		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(ps != null) {
				try {
					ps.close();
				}catch(Exception ex) {}
			}
			
			if(conn != null) {
				try {
					conn.close();
				}catch(Exception ex) {}
			}
		}
		
		return updateCount;
	}
	
}
