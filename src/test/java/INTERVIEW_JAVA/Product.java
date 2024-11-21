package INTERVIEW_JAVA;

import java.util.ArrayList;
import java.util.List;

public class Product {
	public static void main(String args[]) throws ProductNotFound
	{
	List<String> productName= new ArrayList<String>();
	productName.add("soap");
	productName.add("shampoo");
	
	for(String g:productName)
	{
		if(g.equalsIgnoreCase("honey"))
		{
			System.out.println("product available");
		}
		else
		{
			throw new ProductNotFound("product is not available in the list");
		}
	}
	
	}

}
