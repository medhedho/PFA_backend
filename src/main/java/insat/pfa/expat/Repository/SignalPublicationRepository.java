package insat.pfa.expat.Repository;

import insat.pfa.expat.Model.SignalPublication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SignalPublicationRepository extends JpaRepository<SignalPublication,Long> {
}
