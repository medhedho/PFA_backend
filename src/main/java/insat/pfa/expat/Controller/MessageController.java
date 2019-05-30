package insat.pfa.expat.Controller;

import insat.pfa.expat.Business.MessageBusiness;
import insat.pfa.expat.Model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/messages"})
@CrossOrigin(origins = "*")
public class MessageController {

    @Autowired
    MessageBusiness messageBusiness;

    @GetMapping
    public List findAll(){
        return messageBusiness.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Message> findById(@PathVariable long id){
        return messageBusiness.findById(id);
    }

    @PostMapping(path = {"/new/{id1}/{id2}"})
    public Message createMessage(@PathVariable long id1, @PathVariable long id2, @RequestBody Message message){
        return messageBusiness.createMessage(message,id1,id2);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<Message> updateMessage(@PathVariable("id") long id, @RequestBody Message message){
        return messageBusiness.updateMessage(id,message);
    }

    @GetMapping(value="/{id1}/{id2}")
    public List findByUsers(@PathVariable long id1, @PathVariable long id2){
        return messageBusiness.findByUsers(id1,id2);
    }
    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable long id) {
        messageBusiness.deleteById(id);
    }

}
