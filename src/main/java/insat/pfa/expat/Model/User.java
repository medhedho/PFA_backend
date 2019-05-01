package insat.pfa.expat.Model;

import javax.jws.soap.SOAPBinding;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="users")
public class User implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Date birthDate;

    private String nativeCountry;

    private String tel;

    private String adress;

    private String email;

    private String role;

    private String status;

    @OneToMany
    private List<User> following;

    @OneToMany
    private List<User> followers;


}
