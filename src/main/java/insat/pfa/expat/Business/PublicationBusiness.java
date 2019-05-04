package insat.pfa.expat.Business;

import insat.pfa.expat.Model.Publication;
import insat.pfa.expat.Model.User;
import insat.pfa.expat.Repository.PublicationRepository;
import insat.pfa.expat.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicationBusiness {

    @Autowired
    private PublicationRepository publicationRepository;

    @Autowired
    UserRepository userRepository;


    public Publication createPublication(Publication publication){
        return publicationRepository.save(publication);
    }

    public ResponseEntity<Publication> findById(long id){
        return publicationRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    public List findAll(){ return publicationRepository.findAll(); }


    public ResponseEntity<Publication> updatePublication(long id, Publication publication){
        return publicationRepository.findById(id)
                .map(record -> {
                    record.setUser(publication.getUser());
                    record.setCreatedAt(publication.getCreatedAt());
                    record.setContent(publication.getContent());

                    record.setPhoto(publication.getPhoto());
                    record.setVideo(publication.getVideo());
                    record.setLikes(publication.getLikes());
                    record.setType(publication.getType());
                    record.setComments(publication.getComments());

                    Publication updated = publicationRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Publication> addLikeToPub(Long idPub, Long idUser){
        return publicationRepository.findById(idPub)
                .map(record -> {
                    User user=userRepository.findById(idUser).orElse(null);
                    List<User> list =record.getLikes();
                    list.add(user);
                    record.setLikes(list);

                    Publication updated = publicationRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

   /* public List<Publication> findFollowingPublications(User user) {
        return publicationRepository.findFollowingPublication(user);
    }*/
}
