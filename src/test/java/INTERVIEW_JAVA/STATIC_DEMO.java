package INTERVIEW_JAVA;

public class STATIC_DEMO {
	
	int a=10;
	
	static
	{
		System.out.println("am static block");
	}
	
	public static void main(String args[])
	{
		/*
		 * Student s1=new Student("raj",100); s1.displayStudentDetail();
		 */
		
		/*
		 * A a=new A(); A.B b=new A.B();//static nested class
		 */	
		
		/*
		 * STATIC_DEMO a1=new STATIC_DEMO(); System.out.println(a1.a);
		 */
		System.out.println("am main method");
		
	}
	
	

}

class Student
{
	Student(String name,int rollNo)
	{
		this.name=name;this.rollNo=rollNo;
		
	}
	String name;
	int rollNo;
	static String collegeName="BTS";
	
	void displayStudentDetail()
	{
		System.out.println("student name is "+this.name+" id is "+this.rollNo+" and college name "+collegeName);
	}
}

class A
{
	static class B
	{
		int no;
		String name;
	}
}
 
