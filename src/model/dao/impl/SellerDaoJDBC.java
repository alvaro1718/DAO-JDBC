package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao{

	private Connection conn;
	
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Seller seller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller seller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		
				
		try {
			st = conn.prepareStatement("SELECT coursejdbc.seller.*, coursejdbc.department.Name as deptName FROM coursejdbc.seller inner join coursejdbc.department "
					+ "on coursejdbc.seller.DepartmentId=coursejdbc.department.Id"
					+ " where coursejdbc.seller.Id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if(rs.next()) {
				Department department = instantiateDepartment(rs);
				Seller seller = instantiateSeller(rs, department);

				return seller;
			}
				return null;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

	private Seller instantiateSeller(ResultSet rs, Department department) throws SQLException{
		Seller seller = new Seller();
		seller.setDepartment(department);
		seller.setId(rs.getInt("Id"));
		seller.setBaseSalary(rs.getDouble("BaseSalary"));
		seller.setName(rs.getString("Name"));
		seller.setEmail(rs.getString("Email"));
		seller.setBirthDate(rs.getDate("BirthDate"));
		return seller;
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department department = new Department();
		department.setId(rs.getInt("DepartmentId"));
		department.setName(rs.getString("deptName"));
		return department;
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Seller> findByDepartment(Department department) {
		ResultSet rs = null;
		PreparedStatement st = null;
		List<Seller> sellerList = new ArrayList<>();
		
		try {
			st = conn.prepareStatement("SELECT coursejdbc.seller.*,coursejdbc.department.Name as deptName "
					+ "FROM coursejdbc.seller INNER JOIN coursejdbc.department "
					+ "ON coursejdbc.seller.DepartmentId = coursejdbc.department.Id "
					+ "WHERE coursejdbc.seller.DepartmentId = ? "
					+ "ORDER BY coursejdbc.seller.Name;");
		
	
		st.setInt(1, department.getId());
		rs = st.executeQuery();
		
		Map<Integer, Department> map = new HashMap<>();
		
		while(rs.next()) {
			
			Department dept = map.get(rs.getInt("DepartmentId"));
			
			if(dept == null) {
				dept = instantiateDepartment(rs);
				map.put(rs.getInt("DepartmentId"), dept);
			}
			
			Seller seller = instantiateSeller(rs, dept);
			sellerList.add(seller);
		}
		
		if(sellerList.isEmpty()) {
			return null;
		}
		
		return sellerList;
		
		} catch (SQLException e) {
			
			throw new DbException(e.getMessage());
		}
		
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

	
}
