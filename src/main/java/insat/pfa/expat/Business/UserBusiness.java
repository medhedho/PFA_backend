package insat.pfa.expat.Business;

import insat.pfa.expat.Model.User;
import insat.pfa.expat.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserBusiness {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    public User createUser(User u){
        User user = new User(u.getUsername(),u.getBirthDate(),u.getNativeCountry(),u.getResidenceCountry(),u.getTel(),u.getEmail(),passwordEncoder.encode(u.getPassword()),u.getRoles());
        return userRepository.save(user);
    }

    public ResponseEntity<User> findById(long id){
        return userRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    public List findAll(){ return userRepository.findAll(); }

    public List findFollowers(long id){
        User user = userRepository.getOne(id);
        return user.getFollowers();
    }

    public List findFollowing(long id){
        User user = userRepository.getOne(id);
        return user.getFollowing();
    }

    public ResponseEntity<User> updateUser(long id, User user){
        return userRepository.findById(id)
                .map(record -> {
                    record.setUsername(user.getUsername());
                    record.setEmail(user.getEmail());

                    record.setBirthDate(user.getBirthDate());
                    record.setNativeCountry(user.getNativeCountry());
                    record.setStatus(user.getStatus());
                    record.setTel(user.getTel());

                    User updated = userRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    public void addFollowing(Long idUser, Long idUserToFollow){

                    User user=userRepository.getOne(idUser);
                    User userToFollow=userRepository.getOne(idUserToFollow);
                    List<User> list =user.getFollowing();
                    List<User> listUserToFollow =userToFollow.getFollowers();
                    list.add(userToFollow);
                    user.setFollowing(list);
                    listUserToFollow.add(user);
                    userToFollow.setFollowers(listUserToFollow);
                    userRepository.save(user);
                    userRepository.save(userToFollow);

    }

}
