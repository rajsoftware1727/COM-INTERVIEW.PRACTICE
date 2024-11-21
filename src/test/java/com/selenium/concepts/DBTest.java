package com.selenium.concepts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.Test;

public class DBTest {

	// connct to database
	@Test
	public void test() throws SQLException {
		String dbUrl = "jdbc:mysql://localhost:3306/employee1";

		String uname = "root";
		String pwd = "WWWwww@123";
		Connection con = DriverManager.getConnection(dbUrl, uname, pwd);

		System.out.println("connect to db successfully");

		Statement g = con.createStatement();
		// String data="CREATE DATABASE `employee1`;";
		// String data1="create table Raj(name varchar(20),id int primary key)";
		// String data2="insert into Raj values('HHH',100);";
		// String data3="insert into Raj values('CCC',200);";
		// String data4="insert into Raj values('DDD',300);";
		String data5 = "select name from Raj;";
		ResultSet name;

		name = g.executeQuery(data5);
		while (name.next()) {
			String name1 = name.getString("name");
			System.out.println(name1);
		}

		String data6 = "select id from Raj;";
		String data7 = "delete into Raj values where id=200;";

		g.executeUpdate(data7);
		/*
		 * while(name.next()) { String name1=name.getString("id");
		 * System.out.println(name1); }
		 */

		con.close();

	}

}
