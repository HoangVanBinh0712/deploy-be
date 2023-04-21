package mypack.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import mypack.model.Notification;
import mypack.model.User;
import mypack.repository.NotificationRepository;
import mypack.repository.UserRepository;

@Service
public class SchedulingService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private NotificationRepository notificationRepository;

	@Autowired
	private SendEmailService sendEmailService;

	@Value("${email.content.employer-notification-service}")
	private String content;

	// s - m - h - d - m - y
	// Every 5:00:00 of the day cron = "0 0 5 * * *"
	@Scheduled(cron = "0 0 6 * * *")
	public void run() {
		// Check if employer Service is near to end
		// if user Post is near to finish
		Date day = new Date();
		List<User> lst = userRepository.getEmployerNearToEndService(day, new Date(day.getTime() + 86400000 * 2));
		List<User> users = lst.stream().filter(x -> x.getEmailConfirm()).toList();
		String[] str = new String[users.size()];

		for (int i = 0; i < users.size(); i++) {
			str[i] = users.get(i).getEmail();
		}
		// send email to str
		if (str.length > 0)
			sendEmailService.sendMailForNotification(str, content);
		// add notification
		List<Notification> newNoti = new ArrayList<>();
		for (User user : lst) {
			Notification n = new Notification(null, user, null, content, day);
			newNoti.add(n);
		}
		notificationRepository.saveAll(newNoti);
		// delete old noti
		notificationRepository.deleteOldNotice(new Date(day.getTime() - 7 * 86400000));

	}
}
