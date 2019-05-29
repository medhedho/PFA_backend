package insat.pfa.expat.Business;

import insat.pfa.expat.Model.Publication;
import insat.pfa.expat.Model.User;
import insat.pfa.expat.Repository.PublicationRepository;
import insat.pfa.expat.Repository.UserRepository;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PublicationBusiness {

    @Autowired
    private PublicationRepository publicationRepository;

    @Autowired
    UserRepository userRepository;




    public Publication createPublication(Publication publication,long userId){
        User user=userRepository.getOne(userId);
        System.out.println(user);
        publication.setUser(user);
        publication.setCreatedAt(new Date());
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

   public /*List<Publication> */ void findFollowingPublications(Long idUser) {
      /* List<Publication> publications= new ArrayList<>();
       User user=userRepository.getOne(idUser);
       List<Long> listFollowing =user.getFollowing();
       for (long i: listFollowing) {

             publicationRepository.findPublicationsOfUser(i);
       }
       System.out.println(listFollowing);*/
      /* List<Publication> publications= new ArrayList<>();
       List<BigInteger> list=publicationRepository.findFollowingPublication(idUser);

       for(int i=0; i < list.size(); i++) {
           BigInteger element = list.get(i);
            Publication p=publicationRepository.getOne(element.longValue());
            publications.add(p);

       }
       System.out.println(publications);
        return publications;
        //return publicationRepository.findFollowingPublication(idUser);*/
    }
}
