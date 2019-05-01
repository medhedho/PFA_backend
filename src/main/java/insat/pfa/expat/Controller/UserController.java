package insat.pfa.expat.Controller;

import insat.pfa.expat.Business.UserBusiness;
import insat.pfa.expat.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/users"})
public class UserController {

    @Autowired
    UserBusiness userBusiness;

    @GetMapping
    public List findAll(){
        return userBusiness.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<User> findById(@PathVariable long id){
        return userBusiness.findById(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user){
        return userBusiness.createUser(user);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user){
        return userBusiness.updateUser(id,user);
    }


    @PutMapping(value = "/{idUser}/Follow/{idUserToFollow}" )
    public void addFollowing(Long idUser, Long idUserToFollow){
        userBusiness.addFollowing(idUser,idUserToFollow);
    }




}
