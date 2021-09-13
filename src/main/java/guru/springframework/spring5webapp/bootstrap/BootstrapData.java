package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.entity.Author;
import guru.springframework.spring5webapp.entity.Book;
import guru.springframework.spring5webapp.entity.Publisher;
import guru.springframework.spring5webapp.repository.AuthorRepository;
import guru.springframework.spring5webapp.repository.BookRepository;
import guru.springframework.spring5webapp.repository.PublisherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(BootstrapData.class);

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    @Autowired
    public BootstrapData(BookRepository bookRepository, AuthorRepository authorRepository,
                         PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Publisher samePublisher = new Publisher().setAddress("here").setName("the only one");
        publisherRepository.save(samePublisher);

        Author eric = new Author().setFirstName("Eric").setLastName("Evans");
        Book book1 = new Book().setTitle("Design").setIsbn("3423ssd");
        eric.getBooks().add(book1);
        book1.getAuthors().add(eric);

        book1.setPublisher(samePublisher);
        samePublisher.getBooks().add(book1);

        Author savedEric = authorRepository.save(eric);
        Book savedBook1 = bookRepository.save(book1);

        Author daniel = new Author().setFirstName("Daniel").setLastName("Johnson");
        Book book2 = new Book().setTitle("Cook").setIsbn("1234");
        daniel.getBooks().add(book2);
        book2.getAuthors().add(daniel);

        book2.setPublisher(samePublisher);
        samePublisher.getBooks().add(book2);

        Author savedDaniel = authorRepository.save(daniel);
        Book savedBook2 = bookRepository.save(book2);

        Publisher savedPublisher = publisherRepository.save(samePublisher);

        LOGGER.info("Start");
        LOGGER.info("First book {} - {}", savedBook1, savedEric);
        LOGGER.info("Second book {} - {}", savedBook2, savedDaniel);
        LOGGER.info("Publisher {}", savedPublisher);
        LOGGER.info("All authors {}", authorRepository.count());
        LOGGER.info("All books {}", bookRepository.count());
        LOGGER.info("All publishers {}", publisherRepository.count());
    }
}
