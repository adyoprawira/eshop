###### Adyo Arkan Prawira / 2306173750
# AdPro Tutorial & Assignment 

#####  Reflection 1
> You already implemented two new features using Spring Boot. Check again your source code and evaluate the coding standards that you have learned in this module. Write clean code principles and secure coding practices that have been applied to your code.  If you find any mistake in your source code, please explain how to improve your code.

I applied several clean code principles:
1.   Meaningful names; I named classes and methods that clearly describe their purposes. For example `createProductPage`, `editProductPage`.
2.  Do One Thing principle; Each class has a single responsibility. For example, `ProductController` handles HTTP requests, `ProductService` provides business logics, etc.

For secure coding practices, I applied input validation in my code using the `@ModelAttribute` decorator from the Spring framework. It helps binding form data to the model.

What I can improve in my code is error handling. My code does not yet have error handling implementation if a product is not found. To improve this, I should add error messages or views whenever error arises.
