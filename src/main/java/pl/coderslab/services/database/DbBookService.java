package pl.coderslab.services.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pl.coderslab.models.Book;
import pl.coderslab.services.BookService;
import pl.coderslab.utils.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class DbBookService implements BookService {

    public final String INSERT_BOOK_QUERY = "INSERT INTO BookAPI.books (isbn, title, author, publisher, type) VALUES (?,?,?,?,?);";
    public final String DELETE_BOOK_BY_ID_QUERY = "DELETE FROM BookAPI.books WHERE id=?;";
    public final String SELECT_BOOKS_QUERY = "SELECT id, isbn, title, author, publisher, type FROM BookAPI.books;";
    public final String SELECT_BOOK_BY_ID_QUERY = "SELECT id, isbn, title, author, publisher, type FROM BookAPI.books WHERE id=?;";
    public final String UPDATE_BOOK_QUERY = "UPDATE BookAPI.books SET isbn=?, title=?, author=?, publisher=?, type=? WHERE id=?;";


    private final DbUtil dbUtil;

    @Autowired
    public DbBookService (DbUtil dbUtil) {
        this.dbUtil = dbUtil;
        this.addBook(new Book(1L, "9788324631766", "Thinking in Java",
                "Bruce Eckel", "Helion", "programming"));
        this.addBook(new Book(2L, "9780007129713", "The Two Towers", "J.R.R. Tolkien", "Harpercollins", "Fantasy"));
        System.out.println("Inital books database loaded..");
    }

    @Override
    public List<Book> getBooksList() {
        List<Book> books = new ArrayList<>();

        try (Connection connection = dbUtil.connect()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_BOOKS_QUERY);
            while (resultSet.next()) {
                Book currentBook = new Book();
                currentBook.setId(resultSet.getLong("id"));
                currentBook.setIsbn(resultSet.getString("isbn"));
                currentBook.setTitle(resultSet.getString("title"));
                currentBook.setAuthor(resultSet.getString("author"));
                currentBook.setPublisher(resultSet.getString("publisher"));
                currentBook.setType(resultSet.getString("type"));
                books.add(currentBook);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } return books;
    }

    @Override
    public Book getBookById (Long id) {
        try (Connection connection = dbUtil.connect()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_BOOK_BY_ID_QUERY);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getLong("id"));
                book.setIsbn(resultSet.getString("isbn"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setPublisher(resultSet.getString("publisher"));
                book.setType(resultSet.getString("type"));
                return book;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } return null;
    }

    @Override
    public void updateBookById(Book book) {
        try (Connection connect = dbUtil.connect()){
            PreparedStatement statement = connect.prepareStatement(UPDATE_BOOK_QUERY);
            statement.setString(1, book.getIsbn());
            statement.setString(2, book.getTitle());
            statement.setString(3, book.getAuthor());
            statement.setString(4, book.getPublisher());
            statement.setString(5, book.getType());
            statement.setLong(6, book.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBookById(Long id) {
        try (Connection connect = dbUtil.connect()){
            PreparedStatement statement = connect.prepareStatement(DELETE_BOOK_BY_ID_QUERY);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addBook(Book book) {
        try (Connection connect = dbUtil.connect()){
            PreparedStatement statement = connect.prepareStatement(INSERT_BOOK_QUERY);
            statement.setString(1, book.getIsbn());
            statement.setString(2, book.getTitle());
            statement.setString(3, book.getAuthor());
            statement.setString(4, book.getPublisher());
            statement.setString(5, book.getType());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
