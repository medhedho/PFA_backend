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
@Table(name="messages")
public class Message implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private User sender;

    private User receiver;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    private String content;
}
