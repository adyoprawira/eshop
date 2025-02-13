###### Adyo Arkan Prawira / 2306173750
# AdPro Tutorial & Assignment 

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
