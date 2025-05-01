package mylab.customer.service;

import java.util.List;
import mylab.customer.vo.CustomerVO;

public interface CustomerService {
	public List<CustomerVO> getAllCustomerList();
	public CustomerVO getCustomerInfo(int id);
	public void insertCustomer(CustomerVO customer);
}
