Deployment Link: [https://upset-tamma-firstaqila-156582f7.koyeb.app/](https://upset-tamma-firstaqila-156582f7.koyeb.app/)

<details>
<summary>Modul 1</summary>

# Reflection 1

## 1. Clean Code Principles Applied
- **Meaningful Naming**: I have applied meaningful naming for variables, methods, and classes. I choose this because it makes the code "self-documenting," allowing other developers (or my future self) to understand the logic without reading deep comments.
- **Small and Focused Functions**: I split the application logic into four focused packages. By keeping these roles separate, I ensure each function has a small scope. I chose this because it prevents logic from getting tangled, making the application much easier to test and maintain.
  - **Model**: Represents the data.
  - **Controller**: Only manages requests/responses and forwards them to the service.
  - **Service**: Implements the business logic.
  - **Repository**: Handles database access. 

## 2. Secure Coding Practices Applied
- **UUID for Product Identification**: I implemented UUID (Universally Unique Identifier) to give each product a unique ID. This makes it harder for anyone to guess or access other product data by simply changing a number in the URL, keeping the information more secure.

## 3. Areas for Improvement
- **Weak Form Validation (Quantity)**: I noticed that the quantity field still accepts text. This is a mistake because it could break the business logic or cause errors in calculations. After this, I can improve the code by changing the data type to an integer and adding the `@Min(0)` annotation to ensure we only store non-negative values.
- **Handling Empty Inputs**: Currently, the form accepts empty values, which could lead to a `NullPointerException` when the application tries to process the data. It is a risk for application stability. After this, I can implement validation using `@NotBlank` or `@NotNull` to reject empty submissions.

# Reflection 2
## 1. Unit Testing
After writing unit tests, I feel much more secure knowing that my code has a baseline of protection against errors. Regarding the number of tests, I believe there is no fixed number of test cases required for a class; instead, the focus should be on the quality and variety of scenarios. It is not enough to only test the "happy path" or successful scenarios; I must also account for negative cases and edge cases to ensure the program can handle unexpected inputs gracefully. While I use Code Coverage as a helpful metric to see which parts of my code have been executed, I am aware that 100% coverage does not guarantee that the code is 100% bug-free. For example, maybe we forgot to include certain logic scenarios that could still lead to errors in production.

## 2. Functional Testing and Code Quality
- **Potential Clean Code Issues**: If I create a new test suite by copying the same setup procedures and instance variables from a previous class, I am creating code duplication. This violates the DRY (Don’t Repeat Yourself) principle.
- **Why it reduces code quality**: Duplicated setup code makes the test suite harder to maintain. If I ever need to change the browser type (e.g., from Chrome to Firefox) or modify the server port, I would have to update it in multiple files. This redundancy increases the risk of errors.
- **Suggested Improvements**: To make the code cleaner, I should create a Base Class for my functional tests. This base class would contain the common setup (`@BeforeEach`) and teardown (`@AfterEach`) logic. I can simply extend this base class. This way, I only write the configuration once, keeping my test suites focused only on their unique testing logic.

</details>

<details>
<summary>Modul 2</summary>

# Reflection
## Code Quality Issues and Fix Strategy
I used **SonarCloud** to help analyze various issues in my codebase. Here are the specific issues detected and how I solved them:

1. **Dissimilar Types in Assertions**: I accidentally comparing incompatible data types in a unit test TvT. I fixed this by ensuring both arguments in the `assertEquals` and `assertNotEquals` functions share the same data type.
2. **Field Injection**: In `ProductController.java` and `ProductServiceImpl.java`, I found that `@Autowired` was used directly on fields, which allows objects to be created in an invalid state and makes testing more difficult. I fixed this by replacing field injection with constructor injection, declaring dependencies as `final` fields, making them explicit, immutable, and the class more testable.
3. **Unnecessary `throws Exception` Declaration**: I noticed this in functional tests where the exception was never actually thrown. I removed them from the method signatures to improve code clarity.
4. **Unused Imports**: There were several imports, such as `MockBean`, that were no longer being used but were still left in the test files. I fixed this by removing all unreferenced imports to keep the file structure clean.
5. ** Ungrouped Dependencies in `build.gradle.kts`**: Dependencies for different purposes were mixed together, making it hard to read. I fixed this by reorganizing the dependency declarations so that dependencies are grouped logically by their scope (e.g., `implementation`, `testImplementation`).

## Has the Current Implementation Met the Definition of CI and CD?
Yes, the current implementation has met the definition of both Continuous Integration and Continuous Deployment. On the CI side, tests suites and SonarCloud analysis are automatically run on every `push` and `pull request` across all branches, ensuring code changes are continuously validated and helping detect code quality issues early in the development process. In addition, Scorecard is also integrated to perform security checks on the codebase. On the CD side, the application is automatically deployed to Koyeb whenever changes are pushed to the `main` branch, ensuring the latest version is always delivered to production without any manual intervention.

</details>