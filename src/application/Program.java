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
		
		SellerDao sellerDao =	DaoFactory.createSellerDao();
		
		System.out.println("---- TEST : Seller findById -----");
		Seller seller = sellerDao.findById(4);
		
		System.out.println(seller.toString());
		
		System.out.println("---- TEST : Seller findById -----");
	}
}
