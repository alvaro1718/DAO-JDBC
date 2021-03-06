package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import db.DbIntegrityException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao{

	private Connection conn;
	
	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Department department) {
		PreparedStatement st = null;
		int rowsAffected = 0;
		try {
			st = conn.prepareStatement("insert into coursejdbc.department (Name) values (?)",Statement.RETURN_GENERATED_KEYS);
			st.setString(1, department.getName());
			rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
				int id = rs.getInt(1);
				department.setId(id);
				System.out.println("new id: " + id +" rows affected: "+rowsAffected);
				}
				DB.closeResultSet(rs);
			}else
			{
				throw new DbException("ERROR!");
				
			}
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void update(Department department) {
		PreparedStatement st = null;
		int rowsAffected = 0;
		try {
			st = conn.prepareStatement("update coursejdbc.department set Name = ? where (Id = ?)");
			st.setString(1, department.getName());
			st.setInt(2, department.getId());
			rowsAffected = st.executeUpdate();
			
			if ( rowsAffected > 0 ) {
				System.out.println(" rows affected: "+rowsAffected);
			}else
			{
				throw new DbException("ERROR!");
			}
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}

		
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		
		try {
			
			st = conn.prepareStatement("DELETE FROM coursejdbc.department "
					+ "WHERE "
					+ "Id = ?");
			st.setInt(1, id);
			int rowAffected = st.executeUpdate();
			System.out.println("Linhas Afetadas: "+ rowAffected);
		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		}
	}

	@Override
	public Department findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("SELECT * FROM coursejdbc.department WHERE coursejdbc.department.Id=?");
			
			st.setInt(1, id);
			
			rs = st.executeQuery();
		
			if(rs.next()) {
				
			Department department = instantiateDepartment(rs);
			return department;
			
			}
			
			return null;
		
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		
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
