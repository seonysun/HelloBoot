package com.sist.main;
import java.util.*;
import java.sql.*;

public class MainClass {
	public static void main(String[] args) {
		String url="jdbc:mysql://localhost:3306/mydb?serverTimezone=UTC";
		try {
			Scanner scan=new Scanner(System.in);
			System.out.print("page 입력: ");
			int page=scan.nextInt();
			int rowSize=5;
			int start=(page-1)*rowSize;
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn=DriverManager.getConnection(url,"root","happy");
			String sql="SELECT no,name,ifnull(addr,'없음'),date_format(regdate,'%Y-%m-%d') "
					+ "from member order by no desc limit ?,?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, rowSize);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getInt(1)+". "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4));
			}
			rs.close();
			ps.close();
			conn.close();
		} catch(Exception ex) {}
	}
}
