package insat.pfa.expat.Controller;

import insat.pfa.expat.Business.UserBusiness;
import insat.pfa.expat.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/users"})
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserBusiness userBusiness;

    @GetMapping
    public List findAll(){
        return userBusiness.findAll();
    }

    @GetMapping(path = {"/{id}/followers"})
    public List findFollowers(@PathVariable long id) { return userBusiness.findFollowers(id); }

    @GetMapping(path = {"/{id}/following"})
    public List findFollowing(@PathVariable long id){
        return userBusiness.findFollowing(id);
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


    @PostMapping(value = "/{idUser}/Follow/{idUserToFollow}" )
    public void addFollowing(@PathVariable("idUser")Long idUser, @PathVariable("idUserToFollow")Long idUserToFollow){
        userBusiness.addFollowing(idUser,idUserToFollow);
    }




}
