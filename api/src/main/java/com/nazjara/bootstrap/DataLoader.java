package com.nazjara.bootstrap;

import com.nazjara.model.Category;
import com.nazjara.model.Customer;
import com.nazjara.model.Vendor;
import com.nazjara.repositories.CategoryRepository;
import com.nazjara.repositories.CustomerRepository;
import com.nazjara.repositories.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;
    private VendorRepository vendorRepository;

    @Autowired
    public DataLoader(CategoryRepository categoryRepository, CustomerRepository customerRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
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

        Vendor vendor1 = new Vendor();
        vendor1.setName("Vendor1");

        Vendor vendor2 = new Vendor();
        vendor2.setName("Vendor2");

        vendorRepository.save(vendor1);
        vendorRepository.save(vendor2);
    }
}