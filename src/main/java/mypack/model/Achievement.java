package mypack.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import mypack.utility.datatype.EAchievementType;

@Data
@Entity
public class Achievement {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank
    private String name;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "image_id")
    private MediaResource image;

    @Column
    @NotNull
	@Enumerated(EnumType.STRING)
    private EAchievementType type;

    @Column
    private String url;
    
    @Column(name = "create_date")
    private Date createDate;
}
