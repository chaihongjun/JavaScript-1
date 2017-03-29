package com.wcc.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import com.wcc.daomain.Customer;
import com.wcc.sysdao.CustomerDao;

//import sun.print.PeekGraphics;

//import com.sun.org.apache.bcel.internal.generic.GETSTATIC;

import com.wcc.uitl.JdbcUtil;

public class CustomerDaoMySQLImpl implements CustomerDao {

	@Override
	public List<Customer> findAll() {
		// 准备好连接对象  preparedStatement  还有结果集
		Connection connection = null;
		PreparedStatement  preparedStatement = null;
		ResultSet resultSet = null;
		
		try {

			connection = JdbcUtil.getConnection();
			preparedStatement = connection.prepareStatement("select * from customer ");
			resultSet = preparedStatement.executeQuery();
			List<Customer> customers = new ArrayList<Customer>();
			
			while (resultSet.next()) {
				// id,name,gender,birthday,cellphone,email,hobby,type,description
				Customer c = new Customer(resultSet.getString("id"), 
						resultSet.getString("name"), 
						resultSet.getString("gender"), 
						resultSet.getDate("birthday"), 
						resultSet.getString("cellphone"), 
						resultSet.getString("email"), 
						resultSet.getString("hobby"), 
						resultSet.getString("type"), 
						resultSet.getString("description"));
				//将对象添加到集合中
				customers.add(c);
			}
			
			return customers;
			
		} catch (SQLException e) {
			throw new RuntimeException("数据库实现接口出现问题了");
		}finally{
			//最终还是要进行资源的释放的
			JdbcUtil.release(resultSet, preparedStatement,  connection);
		}
	}

	@Override
	public void save(Customer c) {
		Connection connection = null;
		PreparedStatement pStatement = null;
		
		try {
			connection = JdbcUtil.getConnection();
			//// id,name,gender,birthday,cellphone,email,hobby,type,description
			pStatement = connection.prepareStatement("insert into customer values(?,?,?,?,?,?,?,?,?)");
			pStatement.setString(1, c.getId());
			pStatement.setString(2, c.getName());
			pStatement.setString(3, c.getGender());
			
			// 对于日期类型要进行转换的
			pStatement.setDate(4, new Date(c.getBirthday().getTime()));
			pStatement.setString(5, c.getCellphone());
			pStatement.setString(6, c.getEmail());
			pStatement.setString(7, c.getHobby());
			pStatement.setString(8, c.getType());
			pStatement.setString(9, c.getDescription());
			// 进行执行
			pStatement.executeUpdate();
			
			
		} catch (SQLException e) {
			throw new RuntimeException("添加用户的数据库接口出现问题了");
		}finally{
			JdbcUtil.release(null, pStatement, connection);
		}
		
	}

	// 找到用户的
	@Override
	public Customer findCustomerById(String customerId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConnection();
			stmt = conn.prepareStatement("select id,name,gender,birthday,cellphone,email,hobby,type,description from customer where id=?");
			stmt.setString(1, customerId);
			rs = stmt.executeQuery();
			if(rs.next()){
				Customer c = new Customer(rs.getString("id"), 
						rs.getString("name"), 
						rs.getString("gender"), 
						rs.getDate("birthday"), 
						rs.getString("cellphone"), 
						rs.getString("email"), 
						rs.getString("hobby"), 
						rs.getString("type"), 
						rs.getString("description"));
				return c;
			}
			return null;
		}catch(Exception e){
			throw new RuntimeException("查询用户的数据库接口出现问题了");
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}
	}

	@Override
	public void updateCustomer(Customer c) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try{
			conn = JdbcUtil.getConnection();
			stmt = conn.prepareStatement("update customer set name=?,gender=?,birthday=?,cellphone=?,email=?,hobby=?,type=?,description=? where id=?");
			
			stmt.setString(1, c.getName());
			stmt.setString(2, c.getGender());
			stmt.setDate(3, new java.sql.Date(c.getBirthday().getTime()));
			stmt.setString(4, c.getCellphone());
			stmt.setString(5, c.getEmail());
			stmt.setString(6, c.getHobby());
			stmt.setString(7, c.getType());
			stmt.setString(8, c.getDescription());
			stmt.setString(9, c.getId());
			stmt.executeUpdate();
		}catch(Exception e){
			throw new RuntimeException("更新界面的数据库接口出现问题了");
		}finally{
			JdbcUtil.release(null, stmt, conn);
		}

	}

	@Override
	public void deleteCustomer(String customerId) {
		
			Connection conn = null;
			PreparedStatement stmt = null;
			try{
				conn = JdbcUtil.getConnection();
				stmt = conn.prepareStatement("delete from customer where id=?");
				stmt.setString(1, customerId);
				stmt.executeUpdate();
			}catch(Exception e){
				throw new RuntimeException("删除的数据库接口出现异常");
			}finally{
				JdbcUtil.release(null, stmt, conn);
			}
	}

	@Override
	public int getTotaoRecode() {
		PreparedStatement pStatement = null;
		Connection connection = null;
		ResultSet resultSet = null;
		try {
			
			connection = JdbcUtil.getConnection();
			pStatement = connection.prepareStatement("select count(*) from customer");
			resultSet = pStatement.executeQuery();
				
			if(resultSet.next()) {
				//因为查询的是表中的记录，所以的话就是只有一条记录的。
				return resultSet.getInt(1) ;
			}
			// 没有的话就直接返回 0 
			return 0;
			
		} catch (SQLException e) {
			throw new RuntimeException("运行时出现异常！ ");
		}finally{
			JdbcUtil.release(resultSet, pStatement, connection);
		}

	}

	/**
	 * 根据分页的起始下标 从数据库中查询出相应的记录出来
	 */
	@Override
	public List<Customer> findPageRecods(int startIndex, int pageSize) {
		Connection connection = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		connection = JdbcUtil.getConnection();
		try {
			pStatement = connection.prepareStatement("select * from customer  limit ?, ? ");
			pStatement.setInt(1, startIndex);
			pStatement.setInt(2, pageSize);
			List<Customer>  customers = new ArrayList<Customer>();
			
			resultSet =pStatement.executeQuery();
			while (resultSet.next()) {
				Customer c = new Customer(
						resultSet.getString("id"), 
						resultSet.getString("name"), 
						resultSet.getString("gender"), 
						resultSet.getDate("birthday"), 
						resultSet.getString("cellphone"), 
						resultSet.getString("email"), 
						resultSet.getString("hobby"), 
						resultSet.getString("type"), 
						resultSet.getString("description")
						);
				customers.add(c);
			}
		return customers;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
