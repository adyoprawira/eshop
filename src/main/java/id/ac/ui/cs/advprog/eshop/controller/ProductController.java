package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.CarServiceImpl;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Setter
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/create")
    public String createProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "CreateProduct";
    }

    @PostMapping("/create")
    public String createProductPost(@ModelAttribute Product product) {
        service.create(product);
        return "redirect:list";
    }

    @GetMapping("/list")
    public String productListPage(Model model) {
        List<Product> allProducts = service.findAll();
        model.addAttribute("products", allProducts);
        return "ProductList";
    }

    @GetMapping("/edit/{id}")
    public String editProductPage(@PathVariable("id") String id, Model model) {
        Product product = service.findById(id);
        model.addAttribute("product", product);
        return "EditProduct";
    }

    @PostMapping("/edit/{id}")
    public String editProductPost(@PathVariable("id") String id, @ModelAttribute Product product) {
        product.setId(id);
        service.update(product);
        return "redirect:/product/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") String id) {
        Product product = service.findById(id);
        service.delete(product);
        return "redirect:/product/list";
    }

    @Controller
    @RequestMapping("/car")
    class CarController extends ProductController {

        @Autowired
        private CarServiceImpl carservice;

        @GetMapping("/createCar")
        public String createCarPage(Model model) {
            Car car = new Car();
            model.addAttribute("car", car);
            return "createCar";
        }

        @PostMapping("/createCar")
        public String createCarPost(@ModelAttribute Car car, Model model) {
            carservice.create(car);
            return "redirect:listCar";
        }

        @GetMapping("/listCar")
        public String carListPage(Model model) {
            List<Car> allCars = carservice.findAll();
            model.addAttribute("cars", allCars);
            return "carList";
        }

        @GetMapping("/editCar/{carId}")
        public String editCarPage(@PathVariable String carId, Model model) {
            Car car = carservice.findById(carId);
            model.addAttribute("car", car);
            return "editCar";
        }

        @PostMapping("/editCar")
        public String editCarPost(@ModelAttribute Car car, Model model) {
            System.out.println(car.getCarId());
            carservice.update(car.getCarId(), car);
            return "redirect:listCar";
        }

        @PostMapping("/deleteCar")
        public String deleteCar(@RequestParam("carId") String carId) {
            carservice.deleteCarById(carId);
            return "redirect:listCar";
        }
    }

}