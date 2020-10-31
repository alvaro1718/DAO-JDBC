package application;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String [] args) {
		
		Department dept1 = new Department(11,"RH");
		
		System.out.println(dept1.toString());
		
		dept1.setId(4);
		dept1.setName("Test");
		
		System.out.println(dept1.toString());
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
		try {
			Seller seller = new Seller(2,"ferros","ferros@ferros.pt",2344.0, new java.sql.Date(sdf.parse("11/12/1997").getTime()),dept1);
			System.out.println(seller);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
