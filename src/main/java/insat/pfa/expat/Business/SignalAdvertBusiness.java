package insat.pfa.expat.Business;

import insat.pfa.expat.Model.SignalAdvert;
import insat.pfa.expat.Repository.SignalAdvertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SignalAdvertBusiness {

    @Autowired
    private SignalAdvertRepository signalAdvertRepository;


    public SignalAdvert createSignalAdvert(SignalAdvert signalAdvert){
        return signalAdvertRepository.save(signalAdvert);
    }

    public ResponseEntity<SignalAdvert> findById(long id){
        return signalAdvertRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    public List findAll(){ return signalAdvertRepository.findAll(); }



}
