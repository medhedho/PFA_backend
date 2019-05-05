package insat.pfa.expat.Controller;

import insat.pfa.expat.Business.SignalAdvertBusiness;
import insat.pfa.expat.Model.SignalAdvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/signalAdverts"})
@CrossOrigin(origins = "*")
public class SignalAdvertController {

    @Autowired
    SignalAdvertBusiness signalAdvertBusiness;

    @GetMapping
    public List findAll(){
        return signalAdvertBusiness.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<SignalAdvert> findById(@PathVariable long id){
        return signalAdvertBusiness.findById(id);
    }

    @PostMapping
    public SignalAdvert createSignalAdvert(@RequestBody SignalAdvert signalAdvert){
        return signalAdvertBusiness.createSignalAdvert(signalAdvert);
    }

}
