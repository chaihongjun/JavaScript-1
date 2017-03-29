package com.wcc.service;

import java.util.List;

import com.wcc.daomain.Customer;
import com.wcc.uitl.Page;

public interface BusinessService {
	/**
	 * 查询所有的客户信息
	 * @return
	 */
    List<Customer> findAll();
    
    /**
     * 添加客户信息
     * @param c
     */
	void  addCustomer(Customer c);
	
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
	 * 根据用户要查看的页码，获取封装了所有分页有关的对象page
	 * 
	 * @param num  用户要查看的页码，如果没有指定，默认为1
	 */
	Page findPageRecords(String  num);
	
	
	

}
