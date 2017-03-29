package com.wcc.sysdao;

import java.util.List;

import com.wcc.daomain.Customer;



public interface CustomerDao {

	/**
	 * 查询所有的客户信息
	 * @return
	 */
    List<Customer> findAll();
    
 
	/**
	 *更据ID查询客户信息
	 * @param customerId
	 * @return  没有找到返回null
	 */
	Customer findCustomerById(String customerId);
	
	/**
	 * 更新可的信息
	 * @param c
	 */
	void updateCustomer(Customer c);
	
	/**
	 * 删除用户
	 * @param customerId
	 */
	void deleteCustomer(String  customerId);

	/**
	 * 保存用户
	 * @param c
	 */
	void save(Customer c);
	
	/**
	 * 获取所有记录的条数
	 * @return
	 */
	int getTotaoRecode();	
	
	/**
	 * 
	 * @param startIndex   开始记录的索引
	 * @param pageSize     每次查询的条数
	 * @return
	 */
	List<Customer> findPageRecods(int startIndex, int pageSize);
	
}
