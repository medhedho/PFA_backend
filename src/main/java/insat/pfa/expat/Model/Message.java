package insat.pfa.expat.Model;

import org.hibernate.annotations.Proxy;
import org.springframework.data.annotation.CreatedDate;
import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="messages")
@Proxy(lazy = false)
public class Message implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender",referencedColumnName = "id")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver",referencedColumnName = "id")
    private User receiver;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    private String content;

    public Message(User sender, User receiver, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.createdAt = new Date();
        this.content = content;
    }
}
