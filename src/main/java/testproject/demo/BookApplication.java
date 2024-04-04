package testproject.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import testproject.demo.entity.Book;
import testproject.demo.repository.BookRepository;

@SpringBootApplication
public class BookApplication
        //extends SpringBootServletInitializer
        implements CommandLineRunner {


    public static void main(String[] args) {
        //SpringApplication.run(BookApplication.class,args);
        SpringApplicationBuilder builder = new SpringApplicationBuilder(BookApplication.class);
        builder.headless(false).run(args);
    }

    @Autowired
    private BookRepository bookRepository;


    @Override
    public void run(String... args) throws Exception {

    }



}
