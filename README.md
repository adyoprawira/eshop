
###### Adyo Arkan Prawira / 2306173750
# AdPro Tutorial & Assignment

### Module 1

#####  Reflection 1
> You already implemented two new features using Spring Boot. Check again your source code and evaluate the coding standards that you have learned in this module. Write clean code principles and secure coding practices that have been applied to your code.  If you find any mistake in your source code, please explain how to improve your code.

I applied several clean code principles:
1.   Meaningful names; I named classes and methods that clearly describe their purposes. For example `createProductPage`, `editProductPage`.
2.  Do One Thing principle; Each class has a single responsibility. For example, `ProductController` handles HTTP requests, `ProductService` provides business logics, etc.

For secure coding practices, I applied input validation in my code using the `@ModelAttribute` decorator from the Spring framework. It helps binding form data to the model.

What I can improve in my code is error handling. My code does not yet have error handling implementation if a product is not found. To improve this, I should add error messages or views whenever error arises.

#### Reflection 2

> After writing the unit test, how do you feel? How many unit tests should be made in a class? How to make sure that our unit tests are enough to verify our program? It would be good if you learned about code coverage. Code coverage is a metric that can help you understand how much of your source is tested. If you have 100% code coverage, does that mean your code has no bugs or errors? 

I feel more confident of the reliability and correctness of my code. By making unit tests, I ensure that my code runs as expected, even when changes are made in the future. 

There are no numerical limits on how many test cases should be made. Test cases are considered enough when they cover all public methods and edge cases. It should also handle errors.

If I have 100% code coverage, it does not necessarily mean that my code has no bugs or errors. It only means that during the tests, all lines of code were executed.  But bugs can still exist due to incorrect test cases, untested edge cases, and so on.

> Suppose that after writing the CreateProductFunctionalTest.java along with the corresponding test case, you were asked to create another functional test suite that verifies the number of items in the product list. You decided to create a new Java class similar to the prior functional test suites with the same setup procedures and instance variables. 

> What do you think about the cleanliness of the code of the new functional test suite? Will the new code reduce the code quality? Identify the potential clean code issues, explain the reasons, and suggest possible improvements to make the code cleaner! Please write your reflection inside the repository's README.md file.

Reusing the same setup procedures and instance variables might seem convenient, but it could lead to code duplication, which violates the DRY (Dont Repeat Yourself) principle. Code duplication will reduce code quality. These are some issues;
1.  Having the same setup code will increase the risk of errors and harder to maintain.
2.  Repeated code makes it hard to identify the unique behaviour of each test classes.

I think having a base test class would improve the code cleanliness. This base test class would have the commonly used setups and methods. When I create new test classes, I would simply extend/implement the base test class. This would reduce code duplication. Another possible improvement is having helper methods which follows the Single Resposibility Principle. Method that is commonly used like `createProduct` could be used on other test classes without having to rewrite the code.

### Module 2

#### Reflection 3

> List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them.

These were the issues i encountered and how i fixed them;
1.  Unnecessary 'public' in functions: Simply by removing them. This will improve maintainability.
2. Unused imports:  Simply by removing them. This will improve maintainability.
3. Exception throws in function body:  Simply by removing them. This will improve reliability.
4. Lack of documentation: Added comments. This will improve maintainability when code gets complex.
5. Values not passed directly: Pass them directly. This will improve maintainability.

> Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current implementation has met the definition of Continuous Integration and Continuous Deployment? Explain the reasons (minimum 3 sentences)!

The workflows which I have implemented have Continuous Integration (CI) setup. The testing was automatically done by ci.yml and the code analysis was automatically done by scorecard.yml and sonarcloud.yml. Although I have tried implementing CD workflow by deploying to Koyeb using deploy-to-koyeb.yml (as seen on previous commits), i decided to use auto-deployment on the Koyeb web for easier use.

### Module 3

#### Reflection 4

> 1) Explain what principles you apply to your project!

1. **SRP (Single Responsibility Principle)**
Originally in `before-solid`,  the `CarController` class was  extending inside `ProductController.java` which does not adhere to the SRP. To fix this, I moved the `CarController` class into a new Java file `CarController.java`. This way, each class has one responsibility.

2. **OCP (Open Closed Principle)**
	Originally in `before-solid`,  the `CarRepository` class was implemented directly using a concrete repository class, which does not adhere to the OCP. To fix this, I added a separate interface of `CarRepository.java`, which then `CarRepositoryImpl.java` will implement. This ensures code flexibility since we don't need to modify the existing code when implementing a new feature/function.

3. **ISP (Interface Segregation Principle)**
	I have made sure to separate the interfaces that is used. `ProductService` is the interface for products and `CarService` is the interface for Cars.

4. **DIP (Dependency Inversion Principle)**
	I used @Autowired injection so that the code depends on interfaces, not concrete classes. Controllers implement services instead of needing to create objects.

> 2) Explain the advantages of applying SOLID principles to your project with examples.
-   **Improved Maintainability:** By separating controllers and services, like moving `CarController` out of `ProductController`, the code will be much easier to manage. Now, I can change car-related logic without affecting product handling, which really simplifies future updates.
-   **Enhanced Flexibility:** The `CarRepository` interface allows me to swap out database implementations without having to rewrite large chunks of code. That kind of adaptability is crucial for long-term projects.
-   **Increased Testability:** Using dependency injection with `@Autowired` made testing easier. I can easily inject mocks and stubs, which allows me to test controllers in isolation and catch bugs early on.
-   **Reduced Coupling:** I've noticed a significant reduction in coupling. My code is more modular, meaning that changes in one part of the system are less likely to have unintended consequences elsewhere. 
-   **Enhanced Clarity:** These principles have made the codebase much cleaner and more understandable.
> 
> 3) Explain the disadvantages of not applying SOLID principles to your project with examples.

-   **Increased Chance of Bugs:** If I hadn't separated `CarController` from `ProductController`, any change to product logic could potentially break car-related features. 
-   **Reduced Flexibility and Increased Rigidity:** Without the `CarRepository` interface, if I wanted to switch databases, I'd have to rewrite significant portions of the data access layer. This would be a massive undertaking, making the application inflexible and resistant to change.
-   **Difficult and Unreliable Testing:** If I hadn't used dependency injection, testing controllers would be a harder. I'd have to rely on actual database connections and external dependencies, making tests slow and difficult to isolate. This would make it much harder to find bugs and ensure code quality.
-   **Tight Coupling and Fragile Code:** Without adhering to principles like DIP and ISP, my code would be tightly coupled. Changes in one module would likely ruin the entire system, leading to unexpected errors. This would make the code difficult to maintain.
-   **Decreased Code Readability and Increased Complexity:** Ignoring SOLID principles leads to messy, disorganized code. Without clear responsibilities and abstractions, the codebase becomes difficult to understand and maintain. 
