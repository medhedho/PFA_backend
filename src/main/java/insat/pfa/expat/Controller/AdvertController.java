package insat.pfa.expat.Controller;

import insat.pfa.expat.Business.AdvertBusiness;
import insat.pfa.expat.Model.Advert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping({"/adverts"})
@CrossOrigin(origins = "*")
public class AdvertController {
    @Autowired
    AdvertBusiness advertBusiness;

    @GetMapping
    public List findAll(){
        return advertBusiness.findAll();
    }

    @GetMapping("/search")
    public List findByTypeAndLocation(){
        return advertBusiness.findByTypeAndLocation("job","paris");
    }


    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Advert> findById(@PathVariable long id){
        return advertBusiness.findById(id);
    }

    @PostMapping({"/{userId}"})
    public Advert createAdvert(@PathVariable long userId,@RequestBody Advert advert){
        return advertBusiness.createAdvert(advert,userId);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<Advert> updateAdvert(@PathVariable("id") long id, @RequestBody Advert advert){
        return advertBusiness.updateAdvert(id,advert);
    }

    @PostMapping(value="/interest/{idAdvert}/{idUser}")
    public ResponseEntity<Advert> addInterestToAdvert(@PathVariable("idAdvert") long id1, @PathVariable("idUser") long id2){
        return advertBusiness.addInterestToAdvert(id1,id2);
    }



}
