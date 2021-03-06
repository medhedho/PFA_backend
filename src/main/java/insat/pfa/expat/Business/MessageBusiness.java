package insat.pfa.expat.Business;

import insat.pfa.expat.Model.Message;
import insat.pfa.expat.Model.User;
import insat.pfa.expat.Repository.MessageRepository;
import insat.pfa.expat.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class MessageBusiness {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;


    public Message createMessage(Message message, long id1, long id2){
        Message m = new Message(userRepository.getOne(id1),userRepository.getOne(id2),message.getContent());
        return messageRepository.save(m);
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

    public List<Message> findByUsers(long idUser1,long idUser2)
    {
        User user1 =userRepository.findById(idUser1).orElse(null);
        User user2 =userRepository.findById(idUser2).orElse(null);
        List<Message> senderUser1=messageRepository.findBySenderAndAndReceiver(user1,user2);
        List<Message> senderUser2=messageRepository.findBySenderAndAndReceiver(user2,user1);
        senderUser1.addAll(senderUser2);
        Collections.sort(senderUser1,new CustomComparator());
        return senderUser1;
    }

    public void deleteById(long id) {
        messageRepository.deleteById(id);
    }
}

 class CustomComparator implements Comparator<Message> {
    @Override
    public int compare(Message o1, Message o2) {
        return o1.getCreatedAt().compareTo(o2.getCreatedAt());
    }
}