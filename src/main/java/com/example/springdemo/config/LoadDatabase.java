package com.example.springdemo.config;

import com.example.springdemo.product.Product;
import com.example.springdemo.product.ProductRepository;
import com.example.springdemo.customer.Customer;
import com.example.springdemo.customer.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.util.Random;

@Configuration
@Profile("!prod") // Don't run this when profile is prod
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    private final Random r = new Random();

    private int rInt(int high, int low) {
        return r.nextInt(high - low) + low;
    }

    @Bean
    CommandLineRunner initDatabase(ProductRepository productRepository, CustomerRepository userRepository) {
        return args -> {
            if (productRepository.findAll().size() < 1)
                createProducts(productRepository);
            if (userRepository.findAll().size() < 1)
                createUsers(userRepository);
            log.info("Preloading done");
        };
    }

    private void createUsers(CustomerRepository repository) {
        String[] emails = {"john@inc.com", "sara@comp.com", "kim@ab.com", "ken@lab.com", "ho@ent.com"};
        for (String email : emails) {
            log.info("Preloading database with user " + email);
            Customer user = new Customer();
            user.setBirthDate(LocalDate.of(rInt(1995, 1960), rInt(12, 1), rInt(28, 1)));
            user.setEmail(email);
            repository.save(user);
        }
    }

    private void createProducts(ProductRepository repository) {
        String[] products = {"monitor", "mouse", "keyboard", "chair", "table"};
        for (String name : products) {
            log.info("Preloading database with product " + name);
            Product product = new Product();
            product.setName(name);
            product.setAmount(rInt(150, 0));
            product.setPrice(rInt(100, 5) + 0.99);
            repository.save(product);
        }
    }
}
