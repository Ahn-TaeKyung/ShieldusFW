package mylab.notification.di.annot;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component("notificationManager")
public class NotificationManager {
    private NotificationService emailService;
    @Resource(name = "smsNotificationService")
    private NotificationService smsService;
    
    
    public NotificationManager(NotificationService emailService, NotificationService smsService) {
        this.emailService = emailService;
        this.smsService = smsService;
    }
    
    public NotificationService getEmailService() { return emailService; }
    
    public NotificationService getSmsService() { return smsService; }
    
    public void sendNotificationByEmail(String message) {
        emailService.sendNotification(message);
    }
    public void sendNotificationBySms(String message) {
        smsService.sendNotification(message);
    }
    

}