package zest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookManagerTest {
    private BookManager bookManager;

    @Mock
    private BookRatingsFetcher bookRatingsFetcher;

    @BeforeEach
    public void setUp() {
        bookManager = new BookManager(bookRatingsFetcher);
    }


    @Test
    public void emptyInput(){
        List<Book> allBooks = new ArrayList<Book>();
        when(bookRatingsFetcher.all()).thenReturn(allBooks);
        assertEquals(0, bookManager.highRatedBooks().size());
    }

    @Test
    public void invalidInput(){
        List<Book> allBooks = new ArrayList<Book>();
        Book book = new Book("a", -1);
        when(bookRatingsFetcher.all()).thenReturn(allBooks);
        assertEquals(0, bookManager.highRatedBooks().size());
    }

    @Test
    public void oneBookInput(){
        List<Book> allBooks = new ArrayList<Book>();
        Book book = new Book("a", 4);
        allBooks.add(book);
        when(bookRatingsFetcher.all()).thenReturn(allBooks);
        assertEquals(allBooks, bookManager.highRatedBooks());
    }

    @Test
    public void oneBookInputLowRating(){
        List<Book> allBooks = new ArrayList<Book>();
        Book book = new Book("a", 2);
        allBooks.add(book);
        when(bookRatingsFetcher.all()).thenReturn(allBooks);
        assertEquals(0, bookManager.highRatedBooks().size());
    }

    @Test
    public void onlyOneHighRatedInput(){
        List<Book> allBooks = new ArrayList<Book>();
        Book book = new Book("a", 5);
        Book book2 = new Book("b", 3);
        allBooks.add(book);
        allBooks.add(book2);
        when(bookRatingsFetcher.all()).thenReturn(allBooks);
        assertTrue(bookManager.highRatedBooks().contains(book));
        assertEquals(1, bookManager.highRatedBooks().size());
    }

    @Test
    public void multipleHighRatedInputs(){
        List<Book> allBooks = new ArrayList<Book>();
        Book book = new Book("a", 5);
        Book book2 = new Book("b", 3);
        Book book3 = new Book("c", 4);
        allBooks.add(book);
        allBooks.add(book2);
        allBooks.add(book3);
        when(bookRatingsFetcher.all()).thenReturn(allBooks);
        assertTrue(bookManager.highRatedBooks().contains(book) && bookManager.highRatedBooks().contains(book3));
        assertEquals(2, bookManager.highRatedBooks().size());
    }

    @Test
    public void multipleEquallyHighRatedInputs(){
        List<Book> allBooks = new ArrayList<Book>();
        Book book = new Book("a", 5);
        Book book2 = new Book("b", 3);
        Book book3 = new Book("c", 4);
        Book book4 = new Book("d", 4);
        Book book5 = new Book("e", 4);
        allBooks.add(book);
        allBooks.add(book2);
        allBooks.add(book3);
        allBooks.add(book4);
        allBooks.add(book5);
        when(bookRatingsFetcher.all()).thenReturn(allBooks);
        assertTrue(bookManager.highRatedBooks().contains(book) && bookManager.highRatedBooks().contains(book3)
                && bookManager.highRatedBooks().contains(book4) && bookManager.highRatedBooks().contains(book5));
        assertEquals(4, bookManager.highRatedBooks().size());
    }

    @Test
    public void testUniqueAuthors_ReturnsUniqueAuthors() throws Exception {
        // Arrange (mock BookRatingsFetcher)
        BookRatingsFetcher mockFetcher = Mockito.mock(BookRatingsFetcher.class);
        List<Book> books = new ArrayList<>();
        books.add(new Book("Book A", 4, "Author 1"));
        books.add(new Book("Book B", 3, "Author 2"));
        books.add(new Book("Book C", 5, "Author 1")); // Duplicate author
    
        Mockito.when(mockFetcher.all()).thenReturn(books);
        BookManager bookManager = new BookManager(mockFetcher);
    
        // Act
        List<String> uniqueAuthors = bookManager.uniqueAuthors();
    
        // Assert
        assertEquals(2, uniqueAuthors.size()); // Expecting 2 unique authors
        assertTrue(uniqueAuthors.contains("Author 1"));
        assertTrue(uniqueAuthors.contains("Author 2"));
      }

    @Test
    public void testUniqueAuthors_EmptyBookListReturnsEmptyList() throws Exception {
    // Arrange (mock BookRatingsFetcher to return an empty list)
    BookRatingsFetcher mockFetcher = Mockito.mock(BookRatingsFetcher.class);
    Mockito.when(mockFetcher.all()).thenReturn(Collections.emptyList());
    BookManager bookManager = new BookManager(mockFetcher);

    // Act
    List<String> uniqueAuthors = bookManager.uniqueAuthors();

    // Assert
    assertTrue(uniqueAuthors.isEmpty());
}


}
