package insat.pfa.expat.Business;

import insat.pfa.expat.Model.SignalPerson;
import insat.pfa.expat.Model.User;
import insat.pfa.expat.Repository.SignalPersonRepository;
import insat.pfa.expat.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SignalPersonBusiness {

    @Autowired
    private SignalPersonRepository signalPersonRepository;
    private UserRepository userRepository;



    public SignalPerson createSignalPerson(SignalPerson signalPerson, long reportedUserId, long reporterUserId){
        User reported=userRepository.getOne(reportedUserId);
        User reporter=userRepository.getOne(reporterUserId);
        signalPerson.setReportedUser(reported);
        signalPerson.setReporter(reporter);
        return signalPersonRepository.save(signalPerson);
    }

    public ResponseEntity<SignalPerson> findById(long id){
        return signalPersonRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    public List findAll(){ return signalPersonRepository.findAll(); }


    public void deleteById(long id) {
        signalPersonRepository.deleteById(id);
    }
}
