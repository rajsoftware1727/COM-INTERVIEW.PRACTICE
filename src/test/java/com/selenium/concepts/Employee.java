package com.selenium.concepts;

public class Employee {
	
	int salary;
	String name;
	
	Employee(String name, int salary)
	{
		this.name=name;this.salary=salary;
	}
	
	int getSalary()
	{
		return salary;
	}
	
	void getDetails()
	{
		System.out.println("employee name is "+this.name+" salary is "+this.salary);
	}
	 
	
	public static void main(String args[])
	{
		/*
		 * Employee e1=new Employee("raj",2000); e1.getDetails();
		 */
		Manager m1=new Manager("aki",3000,1000);
		m1.getDetails();
 			
	}

}

class Manager extends Employee
{

	Manager(String name, int salary,int bonus) {
		super(name, salary);
		this.salary=salary+bonus;
		// TODO Auto-generated constructor stub
	}
	 
	
	/*
	 * int getSalary(int bonus) { return super.getSalary()+bonus; }
	 */
	
}
