package application;

import java.awt.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String [] args) {
		
		SellerDao sellerDao =	DaoFactory.createSellerDao();
		
		System.out.println("---- TEST : Seller findById -----");
		Seller seller = sellerDao.findById(2);
		
		System.out.println(seller.toString());
		
		System.out.println();
		System.out.println("---- TEST : Seller findByDepartment -----");
		
		java.util.List<Seller> sellerList = sellerDao.findByDepartment(seller.getDepartment());
		for (Seller s : sellerList) {
			System.out.println(s.toString());
		}
		
		System.out.println();
		System.out.println("---- TEST : Seller findByAll -----");
		
		sellerList = sellerDao.findAll();
		for (Seller s : sellerList) {
			System.out.println(s.toString());
		}
	}
}
