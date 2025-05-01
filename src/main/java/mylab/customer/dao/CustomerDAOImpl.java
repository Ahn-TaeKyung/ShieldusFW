package mylab.customer.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import mylab.customer.dao.mapper.CustomerMapper;
import mylab.customer.vo.CustomerVO;

public class CustomerDAOImpl implements CustomerDAO{
	@Autowired
	private CustomerMapper customerMapper;

	private SqlSession session;
	
	public void setSqlSession(SqlSession session) {
		this.session = session;
	}
	@Override
	public CustomerVO selectCustomer(int id) {
		CustomerVO customer = session.selectOne("CustomerMapper.selectCustomerById", id);
		return customer;
	}

	@Override
	public List<CustomerVO> selectAllCustomer() {
		List<CustomerVO> customerList = session.selectList("CustomerMapper.selectAllCustomer");
		return customerList;
	}

	@Override
	public void insertCustomer(CustomerVO customer) {
		session.update("CustomerMapper.insertCustomer", customer);
		System.out.println("등록된 Record CustomerId=" + customer.getId() + " Name=" + customer.getName());
	}
	
	
}
