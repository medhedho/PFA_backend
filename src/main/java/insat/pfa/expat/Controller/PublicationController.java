package insat.pfa.expat.Controller;

import insat.pfa.expat.Business.PublicationBusiness;
import insat.pfa.expat.Model.Publication;
import insat.pfa.expat.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/publications"})
@CrossOrigin(origins = "*")
public class PublicationController {

    @Autowired
    PublicationBusiness publicationBusiness;

    @GetMapping
    public List findAll(){
        return publicationBusiness.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Publication> findById(@PathVariable long id){
        return publicationBusiness.findById(id);
    }

   //Retourne tous les publications des following d'un utilisateur
   @GetMapping(path = {"/PubOfFollowers/{idUser}"})
    public /*List */ void findAllPubOfFollowers(@PathVariable long idUser){
         publicationBusiness.findFollowingPublications(idUser);
    }

    @PostMapping({"/{userId}"})
    public Publication createPublication(@PathVariable long userId,@RequestBody Publication publication){
        return publicationBusiness.createPublication(publication,userId);
    }
    @PostMapping(value="/like/{idPub}/{idUser}")
    public ResponseEntity<Publication> addLikeToPub(@PathVariable("idPub") long id1, @PathVariable("idUser") long id2){
        return publicationBusiness.addLikeToPub(id1,id2);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<Publication> updatePublication(@PathVariable("id") long id, @RequestBody Publication publication){
        return publicationBusiness.updatePublication(id,publication);
    }
    @DeleteMapping("/{id}")
    public void deletePub(@PathVariable long id) {
        publicationBusiness.deleteById(id);
    }

}
