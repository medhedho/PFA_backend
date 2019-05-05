package insat.pfa.expat.Model;

import org.springframework.data.annotation.CreatedDate;
import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="adverts")
public class Advert implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    private String content;

    private String location;

    @OneToMany
    private List<User> interested;

    private String type;

    @OneToMany(cascade=CascadeType.ALL)
    private List<Comment> comments;

    public Advert(User user, String content, String location, String type) {
        this.user = user;
        this.createdAt = new Date();
        this.content = content;
        this.location = location;
        this.type = type;
    }
}
