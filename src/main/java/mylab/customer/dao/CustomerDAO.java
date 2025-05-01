package mylab.customer.dao;

import java.util.List;

import mylab.customer.vo.CustomerVO;

public interface CustomerDAO {
	public CustomerVO selectCustomer(int id);
	public List<CustomerVO> selectAllCustomer();
	public void insertCustomer(CustomerVO customer);
}
