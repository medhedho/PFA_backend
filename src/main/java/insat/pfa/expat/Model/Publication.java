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
@Table(name="publications")
public class Publication implements Serializable{
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

    //@ElementCollection
    @Column(columnDefinition = "TEXT", length=65535)
    @org.hibernate.annotations.Type( type = "text" )
    private String photo;

    private String video;

    @OneToMany
    private List<User> likes;


    private String type;

    @OneToMany
    private List<Comment> comments;

    public Publication(User user, String content, String photo, String video, String type) {
        this.user = user;
        this.createdAt = new Date();
        this.content = content;
        this.photo = photo;
        this.video = video;
        this.type = type;
    }
}
