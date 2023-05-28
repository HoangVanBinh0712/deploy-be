package mypack.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import mypack.controller.exception.CommonRuntimeException;
import mypack.model.User;
import mypack.payload.BaseResponse;
import mypack.repository.UserRepository;
import mypack.utility.RandomString;
import mypack.utility.datatype.EmailType;

@Service
public class SendEmailService {
	@Autowired
	UserRepository userRepo;

	@Autowired
	private JavaMailSender sender;

	@Autowired
	private Configuration config;

	@Value("${spring.mail.username}")
	private String appEmail;

	@Value("${email.content.confirmemail}")
	private String content;

	@Value("${email.content.resetpassword}")
	private String contentRP;

	public BaseResponse sendVerifyAndResetPasswordCode(String email, String type) {
		String code = RandomString.get(10);
		String name = "";

		Optional<User> oUser = userRepo.findByEmail(email);
		if (oUser.isEmpty()) {
			throw new CommonRuntimeException("No user is associated with this email !");
		}
		User user = oUser.get();

		if (type.equals(EmailType.CONFIRM_EMAIL) && user.getEmailConfirm()) {
			throw new CommonRuntimeException("Email is already confirmed before !");
		}
		name = user.getName();
		user.setCode(code);
		userRepo.save(user);
		MimeMessage message = sender.createMimeMessage();
		try {
			// set mediaType
			MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name());
			// add attachment

			Template t = config.getTemplate("verify-code-email-template.ftl");
			Map<String, Object> model = new HashMap<>();

			if (type.equals(EmailType.RESET_PASSWORD)) {
				model.put("subject", "Reset Password");
				model.put("content", contentRP);

			} else {
				model.put("subject", "Confirm Email");
				model.put("content", content);

			}

			model.put("name", name);
			model.put("email", email);
			model.put("code", code);

			if (EmailType.CONFIRM_EMAIL.equals(type))
				model.put("type", "verify");
			else if (EmailType.RESET_PASSWORD.equals(type))
				model.put("type", "reset-password");

			String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

			helper.setTo(email);
			helper.setText(html, true);
			helper.setSubject(type);
			helper.setFrom(appEmail);
			sender.send(message);

		} catch (MessagingException | IOException | TemplateException | MailException e) {
			throw new CommonRuntimeException(String.format("Send verify code failed (%s)", e.getMessage()));
		}

		return new BaseResponse(true, "Code have sent successfully ! Check your email to recieve code.");
	}

	public BaseResponse sendMailForNotification(String[] email, String emailContent) {
		String name = "Our Clients";

		MimeMessage message = sender.createMimeMessage();
		try {
			// set mediaType
			MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name());
			// add attachment

			Template t = config.getTemplate("notification-template.ftl");
			Map<String, Object> model = new HashMap<>();
			String subject = EmailType.NOTIFICATION;
			model.put("subject", subject);
			model.put("content", emailContent);
			model.put("name", name);

			String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

			helper.setTo(email);
			helper.setText(html, true);
			helper.setSubject(subject);
			helper.setFrom(appEmail);
			sender.send(message);

		} catch (MessagingException | IOException | TemplateException | MailException e) {
			throw new CommonRuntimeException(String.format("Send notification fail: (%s)", e.getMessage()));
		}

		return new BaseResponse(true, "Success");
	}

	public BaseResponse sendPassword(String email, String password) {
		String name = "Our Clients";

		MimeMessage message = sender.createMimeMessage();
		try {
			// set mediaType
			MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name());
			// add attachment

			Template t = config.getTemplate("notification-template.ftl");
			Map<String, Object> model = new HashMap<>();
			String subject = EmailType.NOTIFICATION;
			model.put("subject", subject);
			model.put("content", password);
			model.put("name", name);
			model.put("email", email);

			String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

			helper.setTo(email);
			helper.setText(html, true);
			helper.setSubject(subject);
			helper.setFrom(appEmail);
			sender.send(message);

		} catch (MessagingException | IOException | TemplateException | MailException e) {
			throw new CommonRuntimeException(String.format("Send password fail: (%s)", e.getMessage()));
		}

		return new BaseResponse(true, "Success");
	}
}
