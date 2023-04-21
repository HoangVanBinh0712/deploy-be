package mypack.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Item;
import com.paypal.api.payments.ItemList;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

import mypack.controller.exception.CommonRuntimeException;
import mypack.model.Service;
import mypack.model.User;
import mypack.model.UserOrder;
import mypack.repository.OrderRepository;
import mypack.repository.UserRepository;
import mypack.utility.RandomString;
import mypack.utility.datatype.EROrderStatus;

@org.springframework.stereotype.Service
public class PaypalService {

	@Autowired
	private APIContext apiContext;

	@Autowired
	private OrderRepository orderRepo;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private NotificationService notificationService;

	@Value("${email.content.employer-notification-order}")
	private String content;

	@Autowired
	private SendEmailService sendEmailService;

	public Payment createPayment(Long orderId, String cancelUrl, String successUrl) throws PayPalRESTException {
		// Pay for service
		UserOrder order = orderRepo.findById(orderId).get();
		if (order.getStatus() == EROrderStatus.PAID)
			throw new CommonRuntimeException("You already paid for this !");
		Service service = order.getService();

		Double total = order.getTotal();
		String currency = order.getCurrency().toString();
		String method = "paypal";
		String intent = "sale";
		String description = String.format("Rent service %s in %d months (%d).", service.getName(), order.getDuration(),
				order.getId());

		ItemList itemList = new ItemList();
		List<Item> items = new ArrayList<>();

		Item item = new Item();
		item.setName(String.format("Rent service %s in %d months.", service.getName(), order.getDuration()));
		if (currency.equals("USD")) {
			item.setCurrency(currency);
			item.setPrice(service.getPrice().toString());
		} else {
			// VND
			total = total / 25000;
			item.setCurrency("USD");
			Double x = service.getPrice() / 25000;
			item.setPrice(x.toString());
		}

		item.setSku("001");
		item.setQuantity(order.getDuration().toString());

		items.add(item);
		itemList.setItems(items);

		Amount amount = new Amount();
		amount.setCurrency("USD");
		total = new BigDecimal(total).setScale(2, RoundingMode.HALF_UP).doubleValue();
		amount.setTotal(String.format("%.2f", total));

		Transaction transaction = new Transaction();
		transaction.setDescription(description);
		transaction.setAmount(amount);
		transaction.setItemList(itemList);

		List<Transaction> transactions = new ArrayList<>();
		transactions.add(transaction);

		Payer payer = new Payer();
		payer.setPaymentMethod(method.toString());

		Payment payment = new Payment();
		payment.setIntent(intent.toString());
		payment.setPayer(payer);
		payment.setTransactions(transactions);
		RedirectUrls redirectUrls = new RedirectUrls();
		redirectUrls.setCancelUrl(cancelUrl);
		redirectUrls.setReturnUrl(successUrl);
		payment.setRedirectUrls(redirectUrls);

		String requestId = RandomString.get(20);
		apiContext.setRequestId(requestId);
		return payment.create(apiContext);
	}

	public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
		Payment payment = new Payment();
		payment.setId(paymentId);
		PaymentExecution paymentExecute = new PaymentExecution();
		paymentExecute.setPayerId(payerId);

		return payment.execute(apiContext, paymentExecute);
	}

	public Boolean handleSuccess(Payment payment) throws PayPalRESTException {

		try {
			String description = payment.getTransactions().get(0).getDescription();
			Matcher m = Pattern.compile("\\d+").matcher(description);
			List<Integer> lstNum = new ArrayList<>();
			while (m.find()) {
				lstNum.add(Integer.parseInt(m.group()));
			}

			Long orderId = (long) lstNum.get(lstNum.size() - 1);

			UserOrder order = orderRepo.findById(orderId).get();

			User user = order.getUser();
			// Add notification for user
			notificationService.addNotification(user.getId(), "Payment success with order: " + payment.getId(), null);
			Service sv = user.getService();
			SimpleDateFormat cmf = new SimpleDateFormat("yyyy-MM-dd");
			Date today = new Date();

			if (sv == null) {
				sv = order.getService();
				LocalDate newExpirate = LocalDate.parse(cmf.format(today)).plusMonths(order.getDuration());

				// Set service and new expiration date
				user.setService(sv);
				user.setServiceExpirationDate(
						Date.from(newExpirate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
				order.setNote("Rent new service for " + order.getDuration().toString() + " month(s).");
			} else {
				if (user.getServiceExpirationDate() == null || user.getServiceExpirationDate().before(today)) {
					user.setServiceExpirationDate(today);
				}
				Date oldDate = user.getServiceExpirationDate();
				LocalDate newExpirate = LocalDate.parse(cmf.format(user.getServiceExpirationDate()))
						.plusMonths(order.getDuration());
				user.setServiceExpirationDate(
						Date.from(newExpirate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
				Date newDate = new Date(user.getServiceExpirationDate().getTime());
				order.setNote("Update service form " + oldDate.toString() + " to " + newDate.toString() + " .");

			}

			order.setPaidDate(new Date());
			order.setStatus(EROrderStatus.PAID);

			orderRepo.save(order);
			userRepository.save(user);
			// Send email
			if (user.getEmailConfirm())
				sendEmailService.sendMailForNotification(new String[] { user.getEmail() },
						String.format(content, payment.getId()));
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public void saveLink(Long orderId, String href) {
		UserOrder order = orderRepo.findById(orderId).get();
		order.setPaymentUrl(href);
		orderRepo.save(order);
	}
}
