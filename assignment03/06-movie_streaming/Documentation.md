# Movie Streaming

## Use of Doubles for FileStreamService and CacheService
As the `FileStreamService` and `CacheService` interfaces are external dependencies of the `MovieStreamingManager` class, we to created test doubles for these services. 
These doubles allowed us to isolate the unit tests for the `MovieStreamingManager` from external systems.
In our unit tests, we use Mockito to create mock instances of these services. 
Like that, we were able to specify and verify interactions with these dependencies, such that the `MovieStreamingManager` interacts correctly with the dependencies.

## Mocking File System and Cache Interactions
Mocking is important for simulating the behaviour of external systems which do file operations (read/write etc.) and caching:

### FileStreamService
- **Mocking Movie Retrieving and Token Receiving**:
We simulate the getting of movies and the getting of tokens. 
We "mock" the `FileStreamService` to specify the return values, verifying that the correct methods are called, e.g., `retrieveMovie()` and `generateToken()`.

### CacheService
- **Mocking Cache Operations**: 
A cache is important to improve the performance of a system, such that a database does not need to be accessed for every request.
We change the return objects of `getDetails()` and `cacheDetails()`.
This helps to verify that the `MovieStreamingManager` correctly checks the cache before accessing the file system and caches the data.

## Handling of Failures 
Our tests simulate these failure scenarios to ensure robustness:

- **File System Failures**: We use the `thenThrow` method provided by Mockito, to simulate exceptions that could occur during file system operations. For instance, if the file system service fails when attempting to retrieve movie metadata, we assert that the system handles these exceptions correctly. This can be for example by logging the error or by rethrowing an exception with a user-friendly message.

- **Cache Service Unavailability**: In a similar way, we test how the system behaves if the cache service is not available the moment. Our tests ensure that the failures do not crash the system completely.