package INTERVIEW_JAVA;

public class RuntimePolyDemo {
	
	public static void main(String args[])
	{
		 
		Person p1;
		p1=new Father();
		p1.sleep();
		p1=new Son();
		p1.sleep();
		
		
	}
	
	
}


class Person
{
	 void sleep()
	 {
		 System.out.println("person sleeping");
	 }
}

class Father extends Person
{
	 void sleep()
	 {
		 System.out.println("father sleeping");
	 }
}

class Son extends Person
{
	 void sleep()
	 {
		 System.out.println("son sleeping");
	 }
}

