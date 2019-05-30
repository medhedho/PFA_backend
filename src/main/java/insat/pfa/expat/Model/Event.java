package insat.pfa.expat.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="events")
public class Event implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private User user;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date eventDate;

    private String location;

    private String content;

    private String type;

    @OneToMany
    private List<User> participants;

    @OneToMany
    private List<Comment> comments;

    public Event(User user, Date eventDate, String location, String content, String type) {
        this.user = user;
        this.createdAt = new Date();
        this.eventDate = eventDate;
        this.location = location;
        this.content = content;
        this.type = type;
    }
}
