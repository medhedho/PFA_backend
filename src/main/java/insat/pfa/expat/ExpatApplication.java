package insat.pfa.expat;

import insat.pfa.expat.Business.MessageBusiness;
import insat.pfa.expat.Model.Message;
import insat.pfa.expat.Model.User;
import insat.pfa.expat.Repository.MessageRepository;
import insat.pfa.expat.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;

@SpringBootApplication
public class ExpatApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpatApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	/*@Bean
	CommandLineRunner init(UserRepository userRepository, MessageRepository messageRepository, MessageBusiness messageBusiness) {
		return args -> {
			User user1 = new User("username1", new Date(), null, null, null, "email1", "password1", null);
			User user2 = new User("username2", new Date(), null, null, null, "email2", "password2", null);
			User user3 = new User("username3", new Date(), null, null, null, "email3", "password3", null);
			userRepository.save(user1);userRepository.save(user2);userRepository.save(user3);
			Message msg1 = new Message(user1,user2,"Hi");
			TimeUnit.SECONDS.sleep(1);
			Message msg2 = new Message(user2,user1,"Hello");
			TimeUnit.SECONDS.sleep(1);
			Message msg3 = new Message(user1,user2,"What's up");
			TimeUnit.SECONDS.sleep(1);
			Message msg4 = new Message(user2,user1,"Fine");
			TimeUnit.SECONDS.sleep(1);
			Message msg5 = new Message(user3,user1,"WOW");
			messageRepository.save(msg2);messageRepository.save(msg3);messageRepository.save(msg1);messageRepository.save(msg5);messageRepository.save(msg4);
			System.out.println("msg1 : " + msg1.getCreatedAt());
			System.out.println("msg2 : " + msg2.getCreatedAt());
			System.out.println("msg3 : " + msg3.getCreatedAt());
			System.out.println("msg4 : " + msg4.getCreatedAt());
			List <Message> msgs = messageBusiness.findByUsers(user1.getId(),user2.getId());
			Iterator<Message> iterator = msgs.iterator();
			while (iterator.hasNext()){
				Message ms = iterator.next();
				System.out.println(ms.getContent());
			}
		};
	}*/
/*
	@Bean
    CommandLineRunner init(UserRepository repository) {
		return args -> {
			repository.deleteAll();
			LongStream.range(1, 11)
					.mapToObj(i -> {
						User u = new User();
						u.setUsername("Rym Kallel");
						u.setTel("465632652");
						u.setStatus("User");
						u.setNativeCountry("Tunisia");
						u.setResidenceCountry("Paris,France");
						u.setBirthDate(new Date(1996,6,15));
						u.setEmail("kallelrym@ieee.org");
                        List<String> l=new ArrayList<String>();
                        l.add("ADMIN");
						u.setRoles(l);
						return u;
					})
					.map(v -> repository.save(v))
					.forEach(System.out::println);
		};
	}*/
/*
	@Bean
	CommandLineRunner init(AdvertRepository repository) {
		return args -> {
			repository.deleteAll();
			LongStream.range(1, 5)
					.mapToObj(i -> {
						Advert a = new Advert();
						a.setContent("blaaaa blaaaa");
						a.setCreatedAt(new Date());
						a.setType("job");
						a.setLocation("kjfkzjfekllfkpariszjfmzj,m");
												return a;
					})
					.map(v -> repository.save(v))
					.forEach(System.out::println);

		};
	}*/
/*
	@Bean
	CommandLineRunner init(PublicationRepository repository) {
		return args -> {
			repository.deleteAll();
			LongStream.range(1, 5)
					.mapToObj(i -> {
						Publication p=new Publication();
						p.setContent("blaaaa blaaaa");
						p.setCreatedAt(new Date());
						p.setType(TypePublication.TEXT);

												return p;
					})
					.map(v -> repository.save(v))
					.forEach(System.out::println);

		};
	}*/

}

@Configuration
@EnableJpaAuditing
class DataJpaConfig {

	@Bean
	public AuditorAware<User> auditor() {
		return () -> Optional.ofNullable(SecurityContextHolder.getContext())
				.map(SecurityContext::getAuthentication)
				.filter(Authentication::isAuthenticated)
				.map(Authentication::getPrincipal)
				.map(User.class::cast);
	}
}