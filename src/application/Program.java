package application;

import java.awt.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
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
		System.out.println("---- TEST : Seller findAll -----");
		
		sellerList = sellerDao.findAll();
		for (Seller s : sellerList) {
			System.out.println(s.toString());
		}
		
		
		System.out.println();
		System.out.println("---- TEST : Seller insert -----");		
		
		Seller seller_insert = new Seller(null, "Maria Insert", "TesteInsertet@gmail.com", 9000.0, new Date(), new Department(1,"Computers"));
		//sellerDao.insert(seller_insert);
		//System.out.println("Id gerado: "+seller_insert.getId());
		
		System.out.println();
		System.out.println("---- TEST : Seller update -----");		
		
		Seller seller_update = new Seller(9, "Maria Insert", "TesteUpdate@gmail.com", 1000.0, new Date(), new Department(3,""));
		sellerDao.update(seller_update);
		//System.out.println("Id gerado: "+seller_insert.getId());
		
		
		System.out.println();
		System.out.println("---- TEST : Seller delete -----");		
		sellerDao.deleteById(15);
		
		System.out.println();
		System.out.println("---- TEST : Department findAll -----");		
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		java.util.List<Department> deptList = departmentDao.findAll();
		
		for (Department dept : deptList) {
			System.out.println(dept.toString());
			
		}
		
		System.out.println();
		System.out.println("---- TEST : Department findById -----");		
		
		Department department = departmentDao.findById(2);
		System.out.println(department.toString());
		
		System.out.println();
		System.out.println("---- TEST : Department insert -----");		
		Department dept_insert = new Department(null, "Happiness");
	//	departmentDao.insert(dept_insert);
	//	System.out.println("new dept id :"+dept_insert.getId());
		
		System.out.println("---- TEST : Department update -----");		
		department.setName("ElectronicsUPDATE");
		departmentDao.update(department);
		
		
	}
}
