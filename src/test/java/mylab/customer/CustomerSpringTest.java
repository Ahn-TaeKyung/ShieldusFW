package mylab.customer;


import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import mylab.customer.dao.mapper.CustomerMapper;
import mylab.customer.vo.CustomerVO;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:spring-beans-mybatis.xml")
public class CustomerSpringTest {
	protected static final Logger logger = LogManager.getLogger();
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	SqlSessionFactory sessionFactory;
	
	@Autowired
	SqlSession sqlSession;
	
	@Autowired
	CustomerMapper customerMapper;
	@Test @Disabled
	void insert() {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate;
		try {
			birthDate = format.parse("2025-05-01");
			CustomerVO customer = new CustomerVO(1, "naver", "안태경", 25, birthDate);
			customerMapper.insertCustomer(customer);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test @Disabled
	void mapper() {
		CustomerVO customer = customerMapper.selectCustomerById(1);
		logger.debug(customer);
	}
	
	@Test 
	void sqlSession() {
		System.out.println(sessionFactory.getClass().getName());
		
		//Anonymous Inner Class (익명 내부 클래스)
		List<CustomerVO> customerList = sqlSession.selectList("CustomerMapper.selectAllCustomer");  //List<UserVO>
		//기존의 For Loop
		for (CustomerVO customerVO : customerList) {
			logger.debug(customerVO);
		}
		//.forEach(Consumer)에서 Consumer를 Anonymous Inner class 형태로 선언하는 방식
		customerList.forEach(new Consumer<CustomerVO>() {
			@Override
			public void accept(CustomerVO customer) {
				logger.debug(customer);				
			}
		});
		
		//Consumer 추상메서드 void accept(T t)
		//.forEach(Consumer)에서 Consumer를 Lambda Expression (람다식)으로 선언하는 방식
		customerList.forEach(customer1 -> System.out.println(customer1));
		//.forEach(Consumer)에서 Consumer를 Method Reference 으로 선언하는 방식
		customerList.forEach(System.out::println);
	}
	
	
	@Test @Disabled
	void connection() {
		try {
			Connection connection = dataSource.getConnection();
			DatabaseMetaData metaData = connection.getMetaData();
			logger.debug("DB URL = " + metaData.getURL());
			logger.debug("DB Username = " + metaData.getUserName());
			logger.debug("DB Vendor Name = " + metaData.getDatabaseProductName());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
