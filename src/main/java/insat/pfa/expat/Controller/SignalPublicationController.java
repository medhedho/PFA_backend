package insat.pfa.expat.Controller;

import insat.pfa.expat.Business.SignalPublicationBusiness;
import insat.pfa.expat.Model.SignalPublication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/signalPublications"})
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

    @PostMapping
    public SignalPublication createSignalPublication(@RequestBody SignalPublication signalPublication){
        return signalPublicationBusiness.createSignalPublication(signalPublication);
    }

}
