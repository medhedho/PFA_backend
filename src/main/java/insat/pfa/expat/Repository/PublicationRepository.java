package insat.pfa.expat.Repository;

import insat.pfa.expat.Model.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicationRepository extends JpaRepository<Publication,Long> {
    @Query(value = "select id from publications p where p.user_id = ?1" ,nativeQuery = true)
    List findFollowingPublication(Long idUser);
    @Query(value = "select id from publications p where p.user_id = ?1" ,nativeQuery = true)
    List findPublicationsOfUser(Long idUser);
}
