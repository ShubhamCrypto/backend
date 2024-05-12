package dev.shubham.productcatalog;

import dev.shubham.productcatalog.inheritancedemo.singletable.Mentor;
import dev.shubham.productcatalog.inheritancedemo.singletable.User;
import dev.shubham.productcatalog.inheritancedemo.singletable.repository.MentorRepository;
import dev.shubham.productcatalog.inheritancedemo.singletable.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
//public class ProductcatalogApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(ProductcatalogApplication.class, args);
//	}
//
//}
@SpringBootApplication
public class ProductcatalogApplication implements CommandLineRunner {
	private MentorRepository mentorRepository;
	private UserRepository userRepository;
	public ProductcatalogApplication(MentorRepository mentorRepository, UserRepository userRepository){
		this.mentorRepository=mentorRepository;
		this.userRepository=userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(ProductcatalogApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Mentor mentor=new Mentor();
		mentor.setName("shubham");
		mentor.setEmail("@.com");
		mentor.setAverageRating(9.99);
		mentorRepository.save(mentor);
		User u=new User();
		u.setEmail(".com");
		u.setName("shubh");
		userRepository.save(u);
	}
}

// sudo lsof -i :8080
//sudo kill -9 PID
