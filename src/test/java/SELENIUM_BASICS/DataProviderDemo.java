package SELENIUM_BASICS;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderDemo {
	
	/*
	 * @DataProvider(name="raj") public static String[] dataProvider() { String
	 * name[]= {"hh","bb","jj"}; return name; }
	 */
	
	/*
	 * @DataProvider(name="raj") public static String[][] dataProvider() { String
	 * name[][]= {{"hh","bb"},{"jj","kk"}}; return name; }
	 */
	@DataProvider(name="raj")
	public static Iterator<String> dataProvider()
	{
		 List<String> names=new ArrayList<String>();
		 names.add("hh");
		 names.add("kk");
		 names.add("jj");
		 return names.iterator();
		  

		 
	}
	@Test(dataProvider="raj",enabled=true)
	public static void printNames(String name[])
	{
		 
 	}

}
