package csd230.lab1;

import com.github.javafaker.Commerce;
import com.github.javafaker.Faker;
import csd230.lab1.entities.*;
import csd230.lab1.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final CartRepository cartRepository;

    public Application(ProductRepository productRepository,
                       CartRepository cartRepository) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) {

        Faker faker = new Faker();
        Commerce cm = faker.commerce();
        com.github.javafaker.Book fakeBook = faker.book();
        String priceString = faker.commerce().price();


        BookEntity book = new BookEntity(
                fakeBook.title(),
                Double.parseDouble(priceString),
                10,
                faker.code().isbn10(),
                fakeBook.author()
        );


        MagazineEntity magazine = new MagazineEntity(
                faker.lorem().word() + " Magazine",
                12.99,
                20,
                50,
                LocalDateTime.now()
        );

        DiscMagEntity discMag = new DiscMagEntity(
                faker.lorem().word() + " Disc Magazine",
                14.99,
                15,
                30,
                LocalDateTime.now(),
                true
        );

        TicketEntity ticket = new TicketEntity(
                "Event: " + faker.music().genre(),
                49.99
        );

        LaptopEntity laptop = new LaptopEntity(
                "Dell",
                999.99,
                16
        );


        PhoneEntity phone = new PhoneEntity(
                "Samsung",
                799.99,
                128
        );

        productRepository.save(book);
        productRepository.save(magazine);
        productRepository.save(discMag);
        productRepository.save(ticket);
        productRepository.save(laptop);
        productRepository.save(phone);

        CartEntity cart = new CartEntity();
        cartRepository.save(cart);

        cart.addProduct(book);
        cart.addProduct(magazine);
        cart.addProduct(discMag);
        cart.addProduct(ticket);
        cartRepository.save(cart);

        CartEntity cart2 = new CartEntity();
        cartRepository.save(cart2);
        cart2.addProduct(laptop);
        cart2.addProduct(phone);
        cartRepository.save(cart2);


        System.out.println("\n--- ALL PRODUCTS ---");
        List<ProductEntity> allProducts = productRepository.findAll();
        allProducts.forEach(System.out::println);

        System.out.println("\n--- CART CONTENTS ---");
        List<CartEntity> allCarts = cartRepository.findAll();
        for (CartEntity c : allCarts) {
            System.out.println("Cart ID: " + c.getId());
            for (ProductEntity p : c.getProducts()) {
                System.out.println("  " + p);
            }
        }
    }
}