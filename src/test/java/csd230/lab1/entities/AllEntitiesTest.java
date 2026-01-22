package csd230.lab1.entities;

import com.github.javafaker.Faker;
import csd230.lab1.repositories.*;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AllEntitiesTest {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private MagazineRepository magazineRepository;

    @Autowired
    private DiscMagRepository discMagRepository;
    @Autowired
    private LaptopRepository laptopRepository;

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private EntityManager entityManager;

    private final Faker faker = new Faker();

    @Test
    void testBookCRUD() {
        BookEntity book = new BookEntity(
                faker.book().title(),
                Double.parseDouble(faker.commerce().price()),
                5,
                faker.code().isbn10(),
                faker.book().author()
        );

        bookRepository.save(book);
        Long id = book.getId();

        BookEntity found = bookRepository.findById(id).orElse(null);
        assertNotNull(found);

        found.setCopies(10);
        bookRepository.save(found);

        BookEntity updated = bookRepository.findById(id).orElse(null);
        assertEquals(10, updated.getCopies());

        bookRepository.delete(updated);
        assertTrue(bookRepository.findById(id).isEmpty());
    }

    @Test
    void testMagazineCRUD() {
        MagazineEntity mag = new MagazineEntity(
                faker.lorem().word() + " Magazine",
                12.99,
                20,
                50,
                LocalDateTime.now()
        );

        magazineRepository.save(mag);
        Long id = mag.getId();

        MagazineEntity found = magazineRepository.findById(id).orElse(null);
        assertNotNull(found);

        magazineRepository.delete(found);
        assertTrue(magazineRepository.findById(id).isEmpty());
    }

    @Test
    void testTicketCRUD() {
        TicketEntity ticket = new TicketEntity(
                "Event: " + faker.music().genre(),
                49.99
        );

        ticketRepository.save(ticket);
        Long id = ticket.getId();

        TicketEntity found = ticketRepository.findById(id).orElse(null);
        assertNotNull(found);

        ticketRepository.delete(found);
        assertTrue(ticketRepository.findById(id).isEmpty());
    }

    @Test
    void testDiscMagCRUD() {
        DiscMagEntity discMag = new DiscMagEntity(
                faker.lorem().word() + " Disc Magazine",
                14.99,
                15,
                30,
                LocalDateTime.now(),
                true
        );

        discMagRepository.save(discMag);
        Long id = discMag.getId();

        assertTrue(discMagRepository.findById(id).isPresent());

        discMagRepository.deleteById(id);
        assertTrue(discMagRepository.findById(id).isEmpty());
    }

    @Test
    void testLaptopCRUD() {
        LaptopEntity laptop = new LaptopEntity(
                faker.company().name(),
                Double.parseDouble(faker.commerce().price()),
                16
        );

        laptopRepository.save(laptop);
        Long id = laptop.getId();

        LaptopEntity found = laptopRepository.findById(id).orElse(null);
        assertNotNull(found);

        found.setRamSize(32);
        laptopRepository.save(found);

        LaptopEntity updated = laptopRepository.findById(id).orElse(null);
        assertEquals(32, updated.getRamSize());
    }

    @Test
    void testPhoneCRUD() {
        PhoneEntity phone = new PhoneEntity(
                faker.company().name(),
                Double.parseDouble(faker.commerce().price()),
                256
        );

        phoneRepository.save(phone);
        Long id = phone.getId();

        PhoneEntity found = phoneRepository.findById(id).orElse(null);
        assertNotNull(found);
        assertEquals(256, found.getStorage());
    }

    @Test
    void testCartWithProducts() {
        BookEntity book = new BookEntity(
                faker.book().title(),
                Double.parseDouble(faker.commerce().price()),
                3,
                faker.code().isbn10(),
                faker.book().author()
        );

        LaptopEntity laptop = new LaptopEntity(
                faker.company().name(),
                Double.parseDouble(faker.commerce().price()),
                8
        );

        BookEntity savedBook = bookRepository.save(book);
        LaptopEntity savedLaptop = laptopRepository.save(laptop);

        CartEntity cart = new CartEntity();

        cart.addProduct(savedBook);
        cart.addProduct(savedLaptop);

        CartEntity savedCart = cartRepository.save(cart);


        assertNotNull(savedCart.getId());

    }


}