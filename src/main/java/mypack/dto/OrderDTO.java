package mypack.dto;

import java.util.Date;

import mypack.utility.datatype.ECurrency;
import mypack.utility.datatype.EROrderStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
	private Long id;

	private PostDTO post;

	private ServiceDTO service;

	private Integer duration;

	// Duration * service.price
	private Double total;

	private ECurrency currency;

	private EROrderStatus status;

	private Date createdDate;

	private Date paidDate;

	private String note;

	private String paymentUrl;
}
