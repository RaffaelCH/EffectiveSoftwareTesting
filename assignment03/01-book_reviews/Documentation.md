1. the only external dependency would be the one on the database. 
in testing we can remove that dependency by not setting up a database connection and instead creating the necessary input lists 
manually rather than having it returned/fetched from the database.
2. dependency is passed in the form of a constructor where the bookRatingsFetcher object is passed. it is then used in the ratings method
as opposed to doing it with a database connection. this involves storing the bookRatingsFetcher object in the class as a field.
3. we do not know what it would be like in the proper integration and set up with a database as we bypass it. hence, the database connection 
is not tested and it is not clear if bugs would occur from working with the fetched data rather than a specific, constructed one.