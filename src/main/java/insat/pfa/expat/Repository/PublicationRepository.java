package insat.pfa.expat.Repository;

import insat.pfa.expat.Model.Publication;
import insat.pfa.expat.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicationRepository extends JpaRepository<Publication,Long> {
    @Query("SELECT p from Publication p join p.user u where u in ?1")
    List<Publication> findFollowingPublication(User user);
}
