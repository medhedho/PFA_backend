package insat.pfa.expat.Business;

import insat.pfa.expat.Model.Advert;
import insat.pfa.expat.Model.SignalAdvert;
import insat.pfa.expat.Model.User;
import insat.pfa.expat.Repository.AdvertRepository;
import insat.pfa.expat.Repository.SignalAdvertRepository;
import insat.pfa.expat.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SignalAdvertBusiness {

    @Autowired
    private SignalAdvertRepository signalAdvertRepository;
    private UserRepository userRepository ;
    private AdvertRepository advertRepository;


    public SignalAdvert createSignalAdvert(SignalAdvert signalAdvert,long userId,long advertId){
        User user=userRepository.getOne(userId);
        Advert advert=advertRepository.getOne(advertId);
        signalAdvert.setUser(user);
        signalAdvert.setAdvert(advert);
        return signalAdvertRepository.save(signalAdvert);
    }

    public ResponseEntity<SignalAdvert> findById(long id){
        return signalAdvertRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    public List findAll(){ return signalAdvertRepository.findAll(); }


    public void deleteById(long id) {
        signalAdvertRepository.deleteById(id);
    }
}
