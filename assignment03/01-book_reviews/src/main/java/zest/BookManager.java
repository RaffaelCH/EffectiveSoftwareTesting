package zest;

import java.util.List;
import static java.util.stream.Collectors.toList;

public class BookManager {

    private final BookRatingsFetcher bookRatingsFetcher;

    public BookManager(BookRatingsFetcher bookRatingsFetcher){
        this.bookRatingsFetcher = bookRatingsFetcher;
    }

    public List<Book> highRatedBooks() {

        try {
            List<Book> allBooks = bookRatingsFetcher.all();
            return allBooks.stream()
                    .filter(book -> book.getRating() >= 4)
                    .collect(toList());
        } finally {
            bookRatingsFetcher.close();
        }
    }

    public List<String> uniqueAuthors() throws Exception {
        List<Book> allBooks = bookRatingsFetcher.all();
        return allBooks.stream()
            .map(Book::getAuthor)   // Extract author names
            .distinct()             // Remove duplicates
            .collect(toList());     // Convert to a list
      }
      
}
