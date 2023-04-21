//package mypack.model;
//
//import java.util.Date;
//import java.util.List;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
//import javax.validation.constraints.NotBlank;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Entity
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class Comment {
//
//	@Id
//	@Column
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//
//	@ManyToOne
//	@JoinColumn(name = "user_id")
//	private User user;
//
//	@ManyToOne
//	@JoinColumn(name = "post_id")
//	private Post post;
//
//	@Column
//	@NotBlank
//	private String content;
//
//	@Column
//	private Date date;
//
//	// Id of parent comment
//	@ManyToOne
//	private Comment reply;
//
//	@OneToMany(mappedBy = "reply")
//	private List<Comment> childs;
//
//	// @PreRemove
//	// private void preRemove() {
//	// for (Product p : products) {
//	// p.setProductType(null);
//	// }
//	// }
//}
