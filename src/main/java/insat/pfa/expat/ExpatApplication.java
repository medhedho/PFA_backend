package insat.pfa.expat;

import insat.pfa.expat.Model.Advert;
import insat.pfa.expat.Model.Publication;
import insat.pfa.expat.Model.TypePublication;
import insat.pfa.expat.Model.User;
import insat.pfa.expat.Repository.AdvertRepository;
import insat.pfa.expat.Repository.PublicationRepository;
import insat.pfa.expat.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.LongStream;

@SpringBootApplication
public class ExpatApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpatApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepository repository) {
		return args -> {
			repository.deleteAll();
			LongStream.range(1, 11)
					.mapToObj(i -> {
						User u = new User();
						u.setName("Rym Kallel");
						u.setTel("465632652");
						u.setStatus("User");
						u.setNativeCountry("Tunisia");
						u.setAdress("Paris");
						u.setBirthDate(new Date(1996,6,15));
						u.setEmail("kallelrym@ieee.org");
						return u;
					})
					.map(v -> repository.save(v))
					.forEach(System.out::println);
		};
	}

	/*@Bean
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
	}

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
