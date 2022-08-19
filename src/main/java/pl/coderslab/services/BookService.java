package pl.coderslab.services;

import pl.coderslab.models.Book;

import java.util.List;

public interface BookService {

    List<Book> getBooksList();
    Book getBookById(Long id);
    void updateBookById (Book book);
    void deleteBookById (Long id);
    void addBook (Book book);
}
