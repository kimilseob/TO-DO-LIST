package com.dao;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.DriverManager;
import com.dto.TodoDto;

public class TodoDao {

	private static String dburl = "jdbc:mysql://localhost:3306/connectdb?serverTimezone=Asia/Seoul&useSSL=false";
	private static String dbUser = "tester";
	private static String dbpasswd = "1205";
	
	public List<TodoDto> getTodos(){
		List<TodoDto> list = new ArrayList<>();
		TodoDto td = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dburl , dbUser , dbpasswd);
			String sql = "SELECT id,title,name,sequence,regdate,type from todo";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				long id = rs.getLong("id");
				String title = rs.getString("title");
				String name = rs.getString("name");
				int sequence = rs.getInt("sequence");
				String regdate = rs.getString("regdate");
				String type = rs.getString("type");
				
				TodoDto todo = new TodoDto(id,name,regdate,sequence,title,type);
				list.add(todo);
				
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	
	
}
