package be.bstorm.services;

import be.bstorm.models.entities.Book;

import java.util.List;

public interface BookService {

    Book add(Book b);
    Book findById(Long id);
    Book findByTitle(String title);
    List<Book> findAll();
    boolean update(Long id,Book book);
    boolean delete(Long id);
}
