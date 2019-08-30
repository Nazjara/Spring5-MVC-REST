package com.nazjara.bootstrap;

import com.nazjara.model.Category;
import com.nazjara.model.Customer;
import com.nazjara.repositories.CategoryRepository;
import com.nazjara.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;

    @Autowired
    public DataLoader(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        Customer customer1 = new Customer();
        customer1.setFirstName("Joe");
        customer1.setLastName("Newman");

        Customer customer2 = new Customer();
        customer2.setFirstName("Michael");
        customer2.setLastName("Lachappele");

        customerRepository.save(customer1);
        customerRepository.save(customer2);
    }
}