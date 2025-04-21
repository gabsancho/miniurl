# miniurl

**miniurl** is a lightweight URL shortener built with Java and Spring Boot. It enables you to convert long URLs into concise, easily shareable links.

This project is intended for learning purposes only!

## Features

- Shorten long URLs into compact links
- Built with Java and Spring Boot
- Lightweight and easy to deploy

## Getting Started

### Prerequisites

- Java 21
- Gradle

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/gabsancho/miniurl.git
   cd miniurl
   ```
2. Build the project:
    ```bash
    ./gradlew build
    ```

3. Run the application:
    ```bash
    ./gradlew bootRun
    ``` 
    or
    ```bash
   ./gradlew build
    java -jar ./build/libs/<miniurl-VERSION-SNAPSHOT.jar>
    ```
    
The application will start on http://localhost:8080.

## Usage
To shorten a URL, send a POST request to `/shorten` with the original URL in the request body.

An alternative is to send a JSON with containing a `target` key, such as:
```json
    {"target":  "my-long-and-boring-url-to-be-shortened.com"}
```

The server responds with a `code`, that can be used in the browser (or a GET request) to access the original URL.
```
http://localhost:8080/{code}
``` 

## Limits
Currently, the chosen strategy can generate, optimally, `62^6=56800235584` different 6-chars urls. 

## Upcoming Features

The following features are planned for future releases:

- **Conflict Resolution**  
  Improved handling of hash collisions to ensure unique short URLs.

- **User Interface (UI)**  
  A simple and clean web interface to allow users to shorten and manage URLs without using an API client.

- **URL Expiration**  
  Ability to set expiration dates for shortened URLs, automatically removing them after a specified time.

## License
See [LICENSE](https://github.com/gabsancho/miniurl/blob/main/LICENSE).