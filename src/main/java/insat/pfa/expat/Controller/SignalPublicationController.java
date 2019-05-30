package insat.pfa.expat.Controller;

import insat.pfa.expat.Business.SignalPublicationBusiness;
import insat.pfa.expat.Model.SignalPublication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/signalPublications"})
@CrossOrigin(origins = "*")
public class SignalPublicationController {

    @Autowired
    SignalPublicationBusiness signalPublicationBusiness;

    @GetMapping
    public List findAll(){
        return signalPublicationBusiness.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<SignalPublication> findById(@PathVariable long id){
        return signalPublicationBusiness.findById(id);
    }

    @PostMapping({"/{userId}/{pubId}"})
    public SignalPublication createSignalAdvert(@PathVariable long userId,@PathVariable long pubId,@RequestBody SignalPublication signalAdvert){
        return signalPublicationBusiness.createSignalPublication(signalAdvert,userId,pubId);
    }

    @DeleteMapping("/{id}")
    public void deleteSignalPub(@PathVariable long id) {
        signalPublicationBusiness.deleteById(id);
    }

}
