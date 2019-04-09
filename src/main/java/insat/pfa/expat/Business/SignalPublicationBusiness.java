package insat.pfa.expat.Business;

import insat.pfa.expat.Model.SignalPublication;
import insat.pfa.expat.Model.User;
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


    public SignalPublication createSignalPublication(SignalPublication signalPublication){
        return signalPublicationRepository.save(signalPublication);
    }

    public ResponseEntity<SignalPublication> findById(long id){
        return signalPublicationRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    public List findAll(){ return signalPublicationRepository.findAll(); }



}
