package com.selenium.concepts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.testng.annotations.Test;

import java.sql.Statement;

public class DBTestDemo {

	@Test
	public void test() throws SQLException {
		// 1.connect to data base
		String url = "jdbc:mysql://localhost:3306/akila";
		String uname = "root";
		String pwd = "WWWwww@123";
		Connection con = DriverManager.getConnection(url, uname, pwd);
		System.out.println("success");
		// String data="insert into AKI values(200,'raj1')";
		java.sql.Statement st = con.createStatement();
		// st.execute(data);
		System.out.println("success1");

		String dat2 = "insert into AKI values(300,'thrishiv')";
		st.execute(dat2);
		String data1 = "select * from AKI";
		ResultSet name = st.executeQuery(data1);

		while (name.next()) {
			String na = name.getString("name");
			System.out.println(na);
			int id = name.getInt("id");
			System.out.println(id);
		}

	}

}
