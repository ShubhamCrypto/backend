package dev.shubham.productcatalog;


import dev.shubham.productcatalog.models.Category;
import dev.shubham.productcatalog.models.Price;
import dev.shubham.productcatalog.models.Product;
import dev.shubham.productcatalog.repositories.CategoryRepository;
import dev.shubham.productcatalog.repositories.PriceRepository;
import dev.shubham.productcatalog.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
//
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
//********query on parent is also union with child classes ******


//import dev.shubham.productcatalog.inheritancedemo.tableperclass.Mentor;
//import dev.shubham.productcatalog.inheritancedemo.tableperclass.User;
//import dev.shubham.productcatalog.inheritancedemo.tableperclass.repository.MentorRepository;
//import dev.shubham.productcatalog.inheritancedemo.tableperclass.repository.UserRepository;
//@SpringBootApplication
//public class ProductcatalogApplication implements CommandLineRunner {
//	private MentorRepository mentorRepository;
//	private UserRepository userRepository;
//	public ProductcatalogApplication(@Qualifier(value = "tablePerClass_mentorRepo") MentorRepository mentorRepository,@Qualifier(value = "tablePerClass_userRepo") UserRepository userRepository){
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
//		List<User> userList=userRepository.findAll(); // we get both mentor and user (2 )
//		for(User user:userList){
//			System.out.println(user);
//			System.out.println("*********");
//		}
//	}
//}

//
//******** JOINED TABLE
//
//
//import dev.shubham.productcatalog.inheritancedemo.joinedtable.Mentor;
//import dev.shubham.productcatalog.inheritancedemo.joinedtable.User;
//import dev.shubham.productcatalog.inheritancedemo.joinedtable.repository.MentorRepository;
//import dev.shubham.productcatalog.inheritancedemo.joinedtable.repository.UserRepository;
//@SpringBootApplication
//public class ProductcatalogApplication implements CommandLineRunner {
//	private MentorRepository mentorRepository;
//	private UserRepository userRepository;
//	public ProductcatalogApplication(@Qualifier(value = "JoinedTable_mentorRepo") MentorRepository mentorRepository,@Qualifier(value = "JoinedTable_userRepo") UserRepository userRepository){
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
//		List<User> userList=userRepository.findAll(); // we get both mentor and user (2 )
//		for(User user:userList){
//			System.out.println(user);
//			System.out.println("*********");
//		}
//	}
//}




@SpringBootApplication
public class ProductcatalogApplication implements CommandLineRunner {

	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;
	private final PriceRepository priceRepository;

	public ProductcatalogApplication(ProductRepository productRepository,
									 CategoryRepository categoryRepository,
									 PriceRepository priceRepository) {
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
		this.priceRepository = priceRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(ProductcatalogApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Category category=new Category();
		category.setName("apple products");
//		categoryRepository.save(category);////before CascadeType.PERSIST

		Price price=new Price();
//		priceRepository.save(price); //before CascadeType.PERSIST

		Product product=new Product();
		product.setTitle("iphone 16");
		product.setDescription("best iphone ");
		product.setPrice(price);
		product.setCategory(category);
		//productRepository.save(product);
//		Thread.sleep(1000);
		String hexUuid = "10FF0536BA25439B9B6D3D3A10C31064";
		String formattedUuid = hexUuid.replaceAll(
				"(\\p{XDigit}{8})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}+)",
				"$1-$2-$3-$4-$5"
		);
		UUID uuid = UUID.fromString(formattedUuid);
		productRepository.deleteById(uuid);
//		Category category1=categoryRepository.findById(uuid).get();
//
//		System.out.println("category name is : "+category1.getName());
//		System.out.println("printing all products in category");
//		for(Product product1:category1.getProducts()){
//			System.out.println(product1.getTitle());
//		}


	}
}


// sudo lsof -i :8080
//sudo kill -9 PID
