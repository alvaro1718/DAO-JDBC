package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String [] args) {
		
		Department dept1 = new Department(11,"RH");
		
		System.out.println(dept1.toString());
		
		dept1.setId(4);
		dept1.setName("Test");
		
		System.out.println(dept1.toString());
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		String mydate = "11/04/1998 03:03:45";
		
		Seller seller;
		
		try {
				seller = new Seller(2,"ferros","ferros@ferros.pt",2344.0, simpleDateFormat.parse(mydate),dept1);
				System.out.println(seller);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		SellerDao sellerDao =	DaoFactory.createSellerDao();
			
		
	}
}
