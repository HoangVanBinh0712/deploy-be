package mypack.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "follow_user", uniqueConstraints = { @UniqueConstraint(columnNames = { "user_id", "follower_id" }) })
@AllArgsConstructor
@NoArgsConstructor
public class FollowUser {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // only Employer
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Only user
    @ManyToOne
    @JoinColumn(name = "follower_id")
    private User follower;

    @Column
    private Date date;

}
