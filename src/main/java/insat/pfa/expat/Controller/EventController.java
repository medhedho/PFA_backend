package insat.pfa.expat.Controller;

import insat.pfa.expat.Business.EventBusiness;
import insat.pfa.expat.Model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/events"})
@CrossOrigin(origins = "*")
public class EventController {

    @Autowired
    EventBusiness eventBusiness;

    @GetMapping
    public List findAll(){
        return eventBusiness.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Event> findById(@PathVariable long id){
        return eventBusiness.findById(id);
    }

    @PostMapping
    public Event createEvent(@RequestBody Event event){
        return eventBusiness.createEvent(event);
    }
    @PostMapping(value="/like/{idEvent}/{idUser}")
    public ResponseEntity<Event> addLikeToEvent(@PathVariable("idEvent") long id1, @PathVariable("idUser") long id2){
        return eventBusiness.addParticipantToPub(id1,id2);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable("id") long id, @RequestBody Event event){
        return eventBusiness.updateEvent(id,event);
    }


}
