# README.md

## Halim ILTAS

### Project Title: Marvel UI + API Exercise

---

### Overview
This project consists of two parts: Part One focuses on API automation testing for the Marvel Comics API, while Part Two emphasizes UI automation testing for the Marvel website. The goal is to ensure the quality and reliability of the Marvel API and its user interface.

---

### Part One: API Testing

#### Task Description
Create a test case for the `/characters` endpoint of the Marvel API that ensures every character record has all the required JSON properties:

```json
{
  "id": ...,
  "name": ...,
  "description": ...,
  "modified": ...,
  "resourceURI": ...,
  "thumbnail": ...,
  "comics": ...,
  "stories": ...,
  "events": ...,
  "urls": ...
}
```

The testing framework iterates through all paginated results to validate the presence of these properties.

#### Dependencies
To run the API tests, you will need the following dependencies:

- **RestAssured** for API calls.
- **JUnit** for assertions.

Add the following to your `pom.xml`:

```xml
<dependency>
    <groupId>io.rest-assured</groupId>
    <artifactId>rest-assured</artifactId>
    <version>5.4.0</version>
</dependency>
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>7.3.2</version>
    <scope>test</scope>
</dependency>
```

#### Configuration
Store sensitive information such as your API key in a `configuration.properties` file. Ensure this file is not shared publicly on GitHub.

---

### Part Two: UI Testing

#### Task Description
Perform UI automation testing on the Marvel website to verify the following:

1. Navigate to the Search page.
2. Search for “Avengers” in the “COMICS” section.
3. Verify that the number of results is **3583**.
4. Verify that the number of pages is **180**.

#### Dependencies
To run the UI tests, you will need:

- **Selenium** for browser automation.

Add Selenium to your `pom.xml`:

```xml
<dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-java</artifactId>
    <version>4.22.0</version>
</dependency>
```

---

### How to Build and Run

1. Clone this repository.
2. Navigate to the project directory.
3. Ensure you have Maven installed on your machine.
4. Run the following command to build and execute your tests:

```bash
mvn test
```

5. For failed test cases, use:

```bash
mvn -Dtest=FailedRunner test
```

---

### Conclusion
This testing framework aims to provide ongoing confidence in the quality of both the Marvel API and its user interface, ensuring that critical functionality remains intact as changes are made.

---

**Current Date**: Wednesday, January 15, 2025, 1 PM +03

Citations:
[1] https://github.com/bitmakerlabs/react-marvel-api
[2] https://www.raymondcamden.com/2018/11/04/using-azure-functions-and-the-marvel-api-to-visualize-character-history
[3] https://github.com/michaelfsilva/marvel-api
[4] https://developer.marvel.com/docs
[5] https://www.postman.com/alex-postman-workspace/marvel-api-workspace/documentation/989e351/marvel-api
[6] https://developer.marvel.com
[7] https://www.postman.com/alex-postman-workspace/marvel-api-workspace/overview