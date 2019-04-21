package insat.pfa.expat.Business;

import insat.pfa.expat.Model.User;
import insat.pfa.expat.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserBusiness {

    @Autowired
    private UserRepository userRepository;


    public User createUser(User user){
        return userRepository.save(user);
    }

    public ResponseEntity<User> findById(long id){
        return userRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    public List findAll(){ return userRepository.findAll(); }


    public ResponseEntity<User> updateUser(long id, User user){
        return userRepository.findById(id)
                .map(record -> {
                    record.setUsername(user.getUsername());
                    record.setEmail(user.getEmail());
                    record.setAdress(user.getAdress());
                    record.setBirthDate(user.getBirthDate());
                    record.setNativeCountry(user.getNativeCountry());
                    record.setStatus(user.getStatus());
                    record.setTel(user.getTel());

                    User updated = userRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

}
