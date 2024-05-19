## Part A

1. the only external dependency would be the one on the database. 
in testing we can remove that dependency by not setting up a database connection and instead creating the necessary input lists 
manually rather than having it returned/fetched from the database.
2. dependency is passed in the form of a constructor where the bookRatingsFetcher object is passed. it is then used in the ratings method
as opposed to doing it with a database connection. this involves storing the bookRatingsFetcher object in the class as a field.
3. we do not know what it would be like in the proper integration and set up with a database as we bypass it. hence, the database connection 
is not tested and it is not clear if bugs would occur from working with the fetched data rather than a specific, constructed one.


## Part B

We import the necessary libraries, including org.junit.jupiter.api.Test for JUnit 5 and Mockito for mocking. The test method  estUniqueAuthors_ReturnsUniqueAuthors  emonstrates how to test uniqueAuthors. We create a mock BookRatingsFetcher using Mockito. We create a list of Book objects with different titles, ratings, and one duplicate author ("Author 1"). We configure the mock BookRatingsFetcher to return this list of books when its all() method is called.  We create a BookManager instance using the mock fetcher.
We call uniqueAuthors on the BookManager and store the result in uniqueAuthors. We assert that the size of uniqueAuthors is 2, indicating only two unique authors. We use assertTrue to verify that both "Author 1" and "Author 2" are present in the list, ensuring duplicates are removed.