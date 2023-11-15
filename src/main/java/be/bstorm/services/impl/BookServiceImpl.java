package be.bstorm.services.impl;

import be.bstorm.exceptions.TitleBookException;
import be.bstorm.models.entities.Book;
import be.bstorm.repositories.BookRepository;
import be.bstorm.services.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book add(Book b) {
        if(bookRepository.existsByTitle(b.getTitle())){
            throw new TitleBookException();
        }
        return bookRepository.save(b);
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow();
    }

    @Override
    public Book findByTitle(String title) {
        return null;
    }

    @Override
    public List<Book> findAll() {
        return null;
    }

    @Override
    public boolean update(Long id, Book book) {

        Book existingBook;
        try{
            existingBook = findById(id);
            existingBook.setTitle(book.getTitle());
            existingBook.setDescription(book.getDescription());
            return true;
        }catch (NoSuchElementException e){
            return false;
        }
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
