package insat.pfa.expat.Business;

import insat.pfa.expat.Model.Message;
import insat.pfa.expat.Model.User;
import insat.pfa.expat.Repository.MessageRepository;
import insat.pfa.expat.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageBusiness {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;


    public Message createMessage(Message message){
        return messageRepository.save(message);
    }

    public ResponseEntity<Message> findById(long id){
        return messageRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    public List findAll(){ return messageRepository.findAll(); }


    public ResponseEntity<Message> updateMessage(long id, Message message){
        return messageRepository.findById(id)
                .map(record -> {
                    record.setContent(message.getContent());
                    record.setReceiver(message.getReceiver());
                    record.setSender(message.getSender());

                    Message updated = messageRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    /*public ResponseEntity<List<Message>> findByUsers(long idUser1,long idUser2)
    {

        User user1 =userRepository.findById(idUser1).orElse(null);
        User user2 =userRepository.findById(idUser2).orElse(null);
        Message messageUser1=messageRepository
    }*/

}
