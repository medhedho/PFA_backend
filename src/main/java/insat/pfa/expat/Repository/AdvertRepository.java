package insat.pfa.expat.Repository;

import insat.pfa.expat.Model.Advert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvertRepository extends JpaRepository<Advert,Long> {
    @Query("SELECT a FROM Advert a WHERE a.type= :t and upper(a.location) like %:l% ")
    public List<Advert> findByTypeAndLocation(@Param("t") String type,@Param("l") String location);

    @Query("SELECT a FROM Advert a WHERE a.type= :t  ")
    public List<Advert> findByType(@Param("t") String type);

    @Query("SELECT a FROM Advert a WHERE upper(a.location) like %:l% ")
    public List<Advert> findByLocation(@Param("l") String location);


    @Modifying
    @Query(value = "insert into  adverts_interested(advert_id_advert, interested_id_user) values (:id_advert, :id_user)",
            nativeQuery = true)
    void UpdateAvertInterest(@Param("id_advert") Long id_advert, @Param("id_user") Long id_user);

}
