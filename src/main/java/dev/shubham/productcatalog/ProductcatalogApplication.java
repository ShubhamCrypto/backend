package dev.shubham.productcatalog;

import dev.shubham.productcatalog.inheritancedemo.tableperclass.Mentor;
import dev.shubham.productcatalog.inheritancedemo.tableperclass.User;
import dev.shubham.productcatalog.inheritancedemo.tableperclass.repository.MentorRepository;
import dev.shubham.productcatalog.inheritancedemo.tableperclass.repository.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
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

// this code is for creating SINGLE TALE of inheritance  with 2 rows
//import dev.shubham.productcatalog.inheritancedemo.singletable.Mentor;
//import dev.shubham.productcatalog.inheritancedemo.singletable.User;
//import dev.shubham.productcatalog.inheritancedemo.singletable.repository.MentorRepository;
//import dev.shubham.productcatalog.inheritancedemo.singletable.repository.UserRepository;
//@SpringBootApplication
//public class ProductcatalogApplication implements CommandLineRunner {
//	private MentorRepository mentorRepository;
//	private UserRepository userRepository;
//	public ProductcatalogApplication(@Qualifier(value = "singleTable_mentorRepo") MentorRepository mentorRepository,@Qualifier(value = "singleTable_userRepo") UserRepository userRepository){
//		this.mentorRepository=mentorRepository;
//		this.userRepository=userRepository;
//	}
//
//	public static void main(String[] args) {
//		SpringApplication.run(ProductcatalogApplication.class, args);
//	}
//
//	@Override
//	public void run(String... args) throws Exception {
//		Mentor mentor=new Mentor();
//		mentor.setName("shubham");
//		mentor.setEmail("@.com");
//		mentor.setAverageRating(9.99);
//		mentorRepository.save(mentor);
//		User u=new User();
//		u.setEmail(".com");
//		u.setName("shubh");
//		userRepository.save(u);
//	}
//}



// this code is for creating TABLE PER CLASS of inheritance  with 2 rows
@SpringBootApplication
public class ProductcatalogApplication implements CommandLineRunner {
	private MentorRepository mentorRepository;
	private UserRepository userRepository;
	public ProductcatalogApplication(@Qualifier(value = "tablePerClass_mentorRepo") MentorRepository mentorRepository,@Qualifier(value = "tablePerClass_userRepo") UserRepository userRepository){
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
