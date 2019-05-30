package insat.pfa.expat.Business;

import insat.pfa.expat.Model.Advert;
import insat.pfa.expat.Model.Comment;
import insat.pfa.expat.Model.User;
import insat.pfa.expat.Repository.AdvertRepository;
import insat.pfa.expat.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AdvertBusiness {
    
    @Autowired
    AdvertRepository advertRepository;

    @Autowired
    UserRepository userRepository;

    public Advert createAdvert(Advert advert, long userId){
       advert.setUser(userRepository.getOne(userId));
       advert.setCreatedAt(new Date());
       return advertRepository.save(advert);
    }

    public ResponseEntity<Advert> findById(long id){
        return advertRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    public List findAll(){ return advertRepository.findAll(); }


    public ResponseEntity<Advert> updateAdvert(long id, Advert advert){
        return advertRepository.findById(id)
                .map(record -> {
                    record.setUser(advert.getUser());
                    record.setCreatedAt(advert.getCreatedAt());
                    record.setContent(advert.getContent());
                    record.setType(advert.getType());
                    record.setLocation(advert.getLocation());

                    Advert updated = advertRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Advert> addInterestToAdvert(Long idAdvert,Long idUser){
        return advertRepository.findById(idAdvert)
                .map(record -> {
                   User user=userRepository.findById(idUser).orElse(null);
                   List<User> list =record.getInterested();
                   list.add(user);
                   record.setInterested(list);

                    Advert updated = advertRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }


    public List<Advert> findByTypeAndLocation(String type, String location)
    {
        return advertRepository.findByTypeAndLocation(type,location);

    }

    public List<Advert> findByType(String type)
    {
        return advertRepository.findByType(type);

    }

    public List<Advert> findByLocation(String location)
    {
        return advertRepository.findByLocation(location);

    }


    public void deleteById(long id) {
        advertRepository.deleteById(id);
    }

}
