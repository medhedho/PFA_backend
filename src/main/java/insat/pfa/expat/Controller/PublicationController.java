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

  /*  @GetMapping
    public List findAll(){
        return publicationBusiness.findAll();
    }
*/
    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Publication> findById(@PathVariable long id){
        return publicationBusiness.findById(id);
    }

   //Retourne tous les publications des following d'un utilisateur
   /* @GetMapping
    public List findAll(@RequestBody User user){
        return publicationBusiness.findFollowingPublications(user);
    }*/

    @PostMapping
    public Publication createPublication(@RequestBody Publication publication){
        return publicationBusiness.createPublication(publication);
    }
    @PostMapping(value="/like/{idPub}/{idUser}")
    public ResponseEntity<Publication> addLikeToPub(@PathVariable("idPub") long id1, @PathVariable("idUser") long id2){
        return publicationBusiness.addLikeToPub(id1,id2);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<Publication> updatePublication(@PathVariable("id") long id, @RequestBody Publication publication){
        return publicationBusiness.updatePublication(id,publication);
    }


}
