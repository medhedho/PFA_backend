package insat.pfa.expat.Business;

import insat.pfa.expat.Model.*;
import insat.pfa.expat.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentBusiness {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AdvertRepository advertRepository;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    PublicationRepository publicationRepository;

    public Comment createComment(Comment comment){
        return commentRepository.save(comment);
    }

    public ResponseEntity<Comment> findById(long id){
        return commentRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    public List findAll(){ return commentRepository.findAll(); }


    public ResponseEntity<Comment> updateComment(long id, Comment comment){
        return commentRepository.findById(id)
                .map(record -> {
                    record.setUser(comment.getUser());
                    record.setCreatedAt(comment.getCreatedAt());
                    record.setContent(comment.getContent());
                    record.setLikes(comment.getLikes());


                    Comment updated = commentRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Advert> addCommentToAdvert(Long idAdvert, Long idUser, String c){
        return advertRepository.findById(idAdvert)
                .map(record -> {
                    User user=userRepository.findById(idUser).orElse(null);
                    List<Comment> listComment =record.getComments();
                    Comment comment =new Comment();
                    comment.setContent(c);
                    comment.setCreatedAt(new Date());
                    comment.setUser(user);
                    listComment.add(comment);
                    record.setComments(listComment);
                    Advert updated = advertRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Publication> addCommentToPublication(Long idPub, Long idUser, String c){
        return publicationRepository.findById(idPub)
                .map(record -> {
                    User user=userRepository.findById(idUser).orElse(null);
                    List<Comment> listComment =record.getComments();
                    Comment comment =new Comment();
                    comment.setContent(c);
                    comment.setCreatedAt(new Date());
                    comment.setUser(user);
                    listComment.add(comment);
                    record.setComments(listComment);
                    Publication updated = publicationRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }


    public ResponseEntity<Event> addCommentToEvent(Long idPub, Long idUser, String c){
        return eventRepository.findById(idPub)
                .map(record -> {
                    User user=userRepository.findById(idUser).orElse(null);
                    List<Comment> listComment =record.getComments();
                    Comment comment =new Comment();
                    comment.setContent(c);
                    comment.setCreatedAt(new Date());
                    comment.setUser(user);
                    listComment.add(comment);
                    record.setComments(listComment);
                    Event updated = eventRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }


    public ResponseEntity<Comment> addLikeToComment(Long idComment, Long idUser){
        return commentRepository.findById(idComment)
                .map(record -> {
                    User user=userRepository.findById(idUser).orElse(null);
                    List<User> list =record.getLikes();
                    list.add(user);
                    record.setLikes(list);

                    Comment updated = commentRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }


    public void deleteById(long id) {
        commentRepository.deleteById(id);
    }
}
