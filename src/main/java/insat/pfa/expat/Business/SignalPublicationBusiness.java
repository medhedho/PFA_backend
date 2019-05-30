package insat.pfa.expat.Business;

import insat.pfa.expat.Model.Publication;
import insat.pfa.expat.Model.SignalPublication;
import insat.pfa.expat.Model.User;
import insat.pfa.expat.Repository.PublicationRepository;
import insat.pfa.expat.Repository.SignalPublicationRepository;
import insat.pfa.expat.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SignalPublicationBusiness {

    @Autowired
    private SignalPublicationRepository signalPublicationRepository;
    private UserRepository userRepository;
    private PublicationRepository publicationRepository;


    public SignalPublication createSignalPublication(SignalPublication signalPublication, long userId, long pubId){
        User user =userRepository.getOne(userId);
        Publication pub=publicationRepository.getOne(pubId);
        signalPublication.setPublication(pub);
        signalPublication.setUser(user);
        return signalPublicationRepository.save(signalPublication);
    }

    public ResponseEntity<SignalPublication> findById(long id){
        return signalPublicationRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    public List findAll(){ return signalPublicationRepository.findAll(); }


    public void deleteById(long id) {
        signalPublicationRepository.deleteById(id);
    }
}
