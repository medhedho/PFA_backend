package insat.pfa.expat.Repository;

import insat.pfa.expat.Model.Message;
import insat.pfa.expat.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {
    public List<Message> findBySenderAndAndReceiver(User sender, User receiver);
}
