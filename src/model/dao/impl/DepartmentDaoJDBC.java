package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao{

	private Connection conn;
	
	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Department department) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Department department) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Department findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Department> findAll() {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Department> deptList = new ArrayList<>();
		try {
			st = conn.prepareStatement("SELECT * FROM coursejdbc.department");
			rs = st.executeQuery();
		
			while(rs.next()) {
				
			deptList.add(instantiateDepartment(rs));
				
			}
			
			if(deptList.isEmpty()) {
				return null;
			}
			
			return deptList;
		
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department department = new Department();
		
		department.setId(rs.getInt("Id"));
		department.setName(rs.getString("Name"));
		
		return department;
	}
}