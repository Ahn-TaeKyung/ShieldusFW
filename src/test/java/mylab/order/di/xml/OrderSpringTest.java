package mylab.order.di.xml;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:mylab-order-di.xml")
public class OrderSpringTest {
	@Autowired
	OrderService service;
	
	@Autowired
	ShoppingCart cart;
	
	@Test
	void testShoppingCart() {
		assertNotNull(cart);
		assertEquals("P001", cart.getProducts().get(0).getId());
		System.out.println(cart.getTotalPrice());
		System.out.println(cart.toString());
		assertEquals(2, cart.getProducts().size());
	}
	
	@Test
	void testOrderService() {
		assertNotNull(service);
		System.out.println(service.calculateOrderTotal());
		assertEquals(39000, service.calculateOrderTotal(), 0.1);
		System.out.println(service.toString());
	}
}
