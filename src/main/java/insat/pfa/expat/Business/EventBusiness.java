package insat.pfa.expat.Business;

import insat.pfa.expat.Model.Event;
import insat.pfa.expat.Model.User;
import insat.pfa.expat.Repository.EventRepository;
import insat.pfa.expat.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventBusiness {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    UserRepository userRepository;


    public Event createEvent(Event event){
        return eventRepository.save(event);
    }

    public ResponseEntity<Event> findById(long id){
        return eventRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    public List findAll(){ return eventRepository.findAll(); }


    public ResponseEntity<Event> updateEvent(long id, Event event){
        return eventRepository.findById(id)
                .map(record -> {
                    record.setUser(event.getUser());
                    record.setCreatedAt(event.getCreatedAt());
                    record.setContent(event.getContent());
                    record.setPhoto(event.getPhoto());
                    record.setVideo(event.getVideo());
                    record.setEventDate(event.getEventDate());
                    record.setParticipants(event.getParticipants());
                    record.setComments(event.getComments());

                    Event updated = eventRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Event> addParticipantToPub(Long idAdvert, Long idUser){
        return eventRepository.findById(idAdvert)
                .map(record -> {
                    User user=userRepository.findById(idUser).orElse(null);
                    List<User> list =record.getParticipants();
                    list.add(user);
                    record.setParticipants(list);

                    Event updated = eventRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    public void deleteById(long id) {
        eventRepository.deleteById(id);
    }
}
