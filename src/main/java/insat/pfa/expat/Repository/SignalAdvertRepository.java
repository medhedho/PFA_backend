package insat.pfa.expat.Repository;

import insat.pfa.expat.Model.SignalAdvert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SignalAdvertRepository extends JpaRepository<SignalAdvert,Long> {
}
