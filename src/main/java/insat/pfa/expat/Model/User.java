package insat.pfa.expat.Model;

import javax.jws.soap.SOAPBinding;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Proxy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import static java.util.stream.Collectors.toList;

@Entity
@Getter
@Setter
//@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
@Proxy(lazy = false)
public class User implements Serializable, UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private Date birthDate;

    private String nativeCountry;

    private String residenceCountry;

    private String address;

    private String tel;

    private String email;

    private String password;

    @Column(columnDefinition = "TEXT", length=65535)
    @org.hibernate.annotations.Type( type = "text" )
    private String photo;

    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    private List<User> following;

    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    private List<User> followers;


    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    public User(String username, Date birthDate, String nativeCountry, String residenceCountry, String address, String tel, String email, String password, String photo, List<String> roles) {
        this.username = username;
        this.birthDate = birthDate;
        this.nativeCountry = nativeCountry;
        this.residenceCountry = residenceCountry;
        this.address=address;
        this.tel = tel;
        this.email = email;
        this.password = password;
        this.photo = photo;
        this.roles=roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(SimpleGrantedAuthority::new).collect(toList());
        //List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        //authorities.add((new SimpleGrantedAuthority("ROLE_"+this.getRoles().get(0))));
        //System.out.println("the roles " + this.getRoles().get(0));
        //System.out.println("the autorities " + authorities.get(0).toString());
        //return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
