package insat.pfa.expat.Controller;

import insat.pfa.expat.Business.CommentBusiness;
import insat.pfa.expat.Model.Advert;
import insat.pfa.expat.Model.Comment;
import insat.pfa.expat.Model.Publication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping({"/comments"})
@CrossOrigin(origins = "*")
public class CommentController {

    @Autowired
    CommentBusiness commentBusiness;

    @GetMapping
    public List findAll(){
        return commentBusiness.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Comment> findById(@PathVariable long id){
        return commentBusiness.findById(id);
    }

    @PostMapping
    public Comment createComment(@RequestBody Comment comment){
        return commentBusiness.createComment(comment);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable("id") long id, @RequestBody Comment comment){
        return commentBusiness.updateComment(id,comment);
    }

    @PostMapping(value="/advert/{id1}/{id2}/{date}/{content}")
    public ResponseEntity<Advert> addCommentToAdvert(@PathVariable("id1") long id1, @PathVariable("id2") long id2 , @PathVariable("date")   @DateTimeFormat(pattern="MMddyyyy") Date date, @PathVariable("content") String c){
        return commentBusiness.addCommentToAdvert(id1,id2,date,c);
    }

    @PostMapping(value="/publication/{id1}/{id2}/{date}/{content}")
    public ResponseEntity<Publication> addCommentToPublication(@PathVariable("id1") long id1, @PathVariable("id2") long id2 , @PathVariable("date")   @DateTimeFormat(pattern="MMddyyyy") Date date, @PathVariable("content") String c){
        return commentBusiness.addCommentToPublication(id1,id2,date,c);
    }

    @PostMapping(value="/like/{idComment}/{idUser}")
    public ResponseEntity<Comment> addLikeToComment(@PathVariable("idComment") long id1, @PathVariable("idUser") long id2){
        return commentBusiness.addLikeToComment(id1,id2);
    }
    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable long id) {
        commentBusiness.deleteById(id);
    }


}
