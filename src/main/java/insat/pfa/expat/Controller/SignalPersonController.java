package insat.pfa.expat.Controller;

import insat.pfa.expat.Business.SignalPersonBusiness;
import insat.pfa.expat.Model.SignalPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/signalPersons"})
public class SignalPersonController {

    @Autowired
    SignalPersonBusiness signalPersonBusiness;

    @GetMapping
    public List findAll(){
        return signalPersonBusiness.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<SignalPerson> findById(@PathVariable long id){
        return signalPersonBusiness.findById(id);
    }

    @PostMapping
    public SignalPerson createSignalPerson(@RequestBody SignalPerson signalPerson){
        return signalPersonBusiness.createSignalPerson(signalPerson);
    }

}
