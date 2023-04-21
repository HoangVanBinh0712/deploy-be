package mypack.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mypack.utility.datatype.ECurrency;
import mypack.utility.datatype.EROrderStatus;

@Entity
@Data
@Table(name = "user_order")
@NoArgsConstructor
@AllArgsConstructor
public class UserOrder implements Serializable {

	private static final long serialVersionUID = 8322776213884428425L;
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "service_id")
	private Service service;

	// Month
	@Column(name = "duration")
	@NotNull
	@Min(1)
	private Integer duration;
	
	@Column(name = "price")
	@NotNull
	@Min(0)
	private Double price;

	// Duration * price
	@Column(name = "total")
	@NotNull
	@Min(0)
	private Double total;

	@Column(name = "currency", columnDefinition = "varchar(30)")
	@Enumerated(EnumType.STRING)
	@NotNull
	private ECurrency currency;

	@Column(name = "status", columnDefinition = "varchar(30)")
	@Enumerated(EnumType.STRING)
	@NotNull
	private EROrderStatus status;

	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "paid_date")
	private Date paidDate;

	@Column
	private String note;

	@Column
	private String paymentUrl;

}
