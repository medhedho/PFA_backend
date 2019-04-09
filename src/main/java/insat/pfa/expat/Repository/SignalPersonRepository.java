package insat.pfa.expat.Repository;

import insat.pfa.expat.Model.SignalPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SignalPersonRepository extends JpaRepository<SignalPerson,Long> {
}
