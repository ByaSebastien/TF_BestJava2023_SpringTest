package be.bstorm.services.impl;

import be.bstorm.exceptions.TitleBookException;
import be.bstorm.models.entities.Book;
import be.bstorm.repositories.BookRepositoryMock;
import be.bstorm.services.BookService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class BookServiceImplTest {


    //Sans mock
//    @Autowired
//    private BookService bookService;

    //Avec MOCK
    private BookService bookService = new BookServiceImpl(new BookRepositoryMock());

    @Test
    void ShouldAddBookWithSuccess() {
        Book bookToAdd = new Book("Toto à la playa", "Qu'elle est belle ta plage");
        Book insertedBook = bookService.add(bookToAdd);
        assertNotNull(insertedBook);
        assertNotNull(insertedBook.getId());
        assertEquals(bookToAdd.getTitle(), insertedBook.getTitle());
        assertEquals(bookToAdd.getDescription(), insertedBook.getDescription());
    }

    @Test
    void ShouldAddBookWithFailure() {
        Book bookToAdd = new Book("Toto au bowling", "Qu'elle est belle ta boule");
        Book insertedBook = bookService.add(bookToAdd);
        Book book = new Book("Toto au bowling", "Qu'elle est belle ta plage dit!");
        TitleBookException exception = assertThrows(TitleBookException.class, () -> bookService.add(book));
        assertEquals("Title already taken", exception.getMessage());
    }

    @Test
    void ShouldGetOneBookWithSuccess() {
        Book bookToAdd = new Book("Toto à la peche", "Qu'elle est belle ta canne");
        Book insertedBook = bookService.add(bookToAdd);
        Book actualBook = bookService.findById(insertedBook.getId());

        assertEquals(insertedBook, actualBook);
        assertEquals(insertedBook.getId(), actualBook.getId());
        assertEquals(insertedBook.getTitle(), actualBook.getTitle());
        assertEquals(insertedBook.getDescription(), actualBook.getDescription());
        assertNotNull(actualBook);
    }

    @Test
    void ShouldGetOneBookWithFailure() {
        assertThrows(NoSuchElementException.class, () -> bookService.findById(0L));
    }

    @Test
    void ShouldUpdateOneBookWithSuccess(){
        Book bookToAdd = new Book("Toto au billard", "Qu'elle est belle ta queue");
        Book insertedBook = bookService.add(bookToAdd);
        Book existingBook = bookService.findById(insertedBook.getId());
        Book bookToUpdate = new Book("Toto a plus d'idée", "Qu'elle est belle ta tete");
        boolean isOk = bookService.update(existingBook.getId(), bookToUpdate);
        bookToUpdate = bookService.findById(existingBook.getId());

        assertTrue(isOk);
        assertEquals(existingBook,bookToUpdate);
        assertEquals(existingBook.getId(), bookToUpdate.getId());
        assertEquals(existingBook.getTitle(), bookToUpdate.getTitle());
        assertEquals(existingBook.getDescription(), bookToUpdate.getDescription());
    }
}