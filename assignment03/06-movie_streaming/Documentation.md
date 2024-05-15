# Movie Streaming

## Use of Doubles for FileStreamService and CacheService
As the `FileStreamService` and `CacheService` interfaces are external dependencies of the `MovieStreamingManager` class, we to created test doubles for these services. 
These doubles allowed us to isolate the unit tests for the `MovieStreamingManager` from external systems.
In our unit tests, we use Mockito to create mock instances of these services. 
Like that, we were able to specify and verify interactions with these dependencies, such that that the `MovieStreamingManager` interacts correctly with the dependencies.

## Mocking File System and Cache Interactions
Mocking is important for simulating the behavior of external systems associated with file operations and caching mechanisms:

### FileStreamService
- **Mocking Movie Retrieving and Token Generation**:
We simulate the retrieval of movie metadata and generation of streaming tokens without actual file system access. 
By mocking `FileStreamService`, we can specify the return values and verify that the correct methods are called, e.g., `retrieveMovie()` and `generateToken()` to ensure that movie details are fetched and tokens are generated correctly.

### CacheService
- **Mocking Cache Operations**: 
The cache is an important aspect of the system that improves performance by reducing the need to fetch frequently accessed data repeatedly. 
In our tests, we mock methods like `getDetails()` and `cacheDetails()` to test both cache hits and misses.
This helps to verify that the `MovieStreamingManager` properly checks the cache before accessing the file system and correctly caches new data when necessary.

## Handling of Failures
Testing how the system handles failures is as important as testing its successful operation. 
Our tests simulate different failure scenarios to ensure robustness:

- **File System Failures**: By using Mockito's `thenThrow` method, we simulate exceptions that could occur during file system operations. For instance, if the file system service fails when attempting to retrieve movie metadata, our tests can assert that the system handles these exceptions gracefully, perhaps by logging the error or by rethrowing an exception with a user-friendly message.

- **Cache Service Unavailability**: Similarly, we test how the system behaves if the cache service is temporarily unavailable. Our tests ensure that such failures do not crash the system and that appropriate fallbacks or error messages are implemented.

Each of these sections in the testing documentation helps ensure that the `MovieStreamingManager` is thoroughly evaluated under both normal and exceptional conditions, leading to a more reliable and maintainable system.