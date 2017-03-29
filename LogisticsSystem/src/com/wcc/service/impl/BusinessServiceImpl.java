package com.wcc.service.impl;

import java.util.List;
import java.util.UUID;

import com.wcc.dao.impl.CustomerDaoMySQLImpl;
import com.wcc.daomain.Customer;
import com.wcc.service.BusinessService;
import com.wcc.sysdao.CustomerDao;
import com.wcc.uitl.Page;

public class BusinessServiceImpl  implements BusinessService{

	private CustomerDao   customerDao  = new  CustomerDaoMySQLImpl();	
	@Override
	public List<Customer> findAll() {
		return  customerDao.findAll();
	}

	@Override
	public void addCustomer(Customer c) {
		c.setId(UUID.randomUUID().toString());
		customerDao.save(c);
	}

	@Override
	public Customer findCustomerById(String customerId) {
		return customerDao.findCustomerById(customerId);
	}

	@Override
	public void updateCustomer(Customer c) {
		if (c.getId()  == null || c.getId().trim().equals("")) {
			throw new IllegalArgumentException("传入参数异常");
		}
		customerDao.updateCustomer(c);
	}

	@Override
	public void deleteCustomer(String customerId) {
		//先要有这个id才是可以的
		if (customerId == null ||   customerId.trim().toString().equals("")) {
			throw new IllegalArgumentException("传入参数异常");
		}
		customerDao.deleteCustomer(customerId);
	}

	
	/**
	 *  实现在数据库中的
	 */
	@Override
	public Page findPageRecords(String num) {
		// 默认是第一页的数据哦
		int  pageNum = 1;
		if (num != null && !num.trim().equals("")) {
			pageNum = Integer.parseInt(num);
		}
		// 从数据接口实现层中去查找所有的记录
		int totalRecods = customerDao.getTotaoRecode();
		Page page = new Page(pageNum, totalRecods);
		
		List<Customer>  list =
				customerDao.findPageRecods(page.getStartIndex(),page.getPageSize());
		
		page.setRecords(list);
		
		return page;
		
	}

}
