package mylab.notification.di.annot.config;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import mylab.notification.di.annot.EmailNotificationService;
import mylab.notification.di.annot.NotificationManager;
import mylab.notification.di.annot.SmsNotificationService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = NotificationConfig.class, loader = AnnotationConfigContextLoader.class)
public class NotificationConfigTest {
	@Autowired
	NotificationManager notificationManager;
	@Test
	void testNotificationManager() {
		notificationManager.sendNotificationByEmail("안태경");
		notificationManager.sendNotificationBySms("SK Shieldus 루키즈");
		assertEquals("SKT", ((SmsNotificationService)notificationManager.getSmsService()).getProvider());
		assertEquals(587, ((EmailNotificationService)notificationManager.getEmailService()).getPort());
	}
}
