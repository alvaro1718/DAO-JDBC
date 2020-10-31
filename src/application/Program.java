package application;

import model.entities.Department;

public class Program {

	public static void main(String [] args) {
		
		Department dept1 = new Department(11,"RH");
		
		System.out.println(dept1.toString());
		
		dept1.setId(4);
		dept1.setName("Test");
		
		System.out.println(dept1.toString());
	}
}
