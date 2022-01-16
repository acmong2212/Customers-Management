package controller;

import model.Categories;
import model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import service.CategoriesService;
import service.CustomerService;

import java.io.File;
import java.io.IOException;

@Controller
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @Autowired
    CategoriesService categoriesService;

    @GetMapping("")
    public ModelAndView show() {
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("customers", customerService.getAllCustomer());
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreate() {
        ModelAndView modelAndView = new ModelAndView("createCustomer");
        modelAndView.addObject("customer", new Customer());
        modelAndView.addObject("categories", categoriesService.getAllCategories());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView create(@RequestParam MultipartFile uppFile, @ModelAttribute("customer") Customer customer, @RequestParam int idCategories) {
        String nameImg = uppFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(uppFile.getBytes(), new File("D:\\Module 4\\Customers-Management\\src\\main\\webapp\\WEB-INF\\image\\" + nameImg));
            String urlImg = "/img/" + nameImg;
            customer.setImg(urlImg);
        } catch (IOException e) {
            System.err.println("chưa uppload file");
        }

        ModelAndView modelAndView = new ModelAndView("redirect:/");

        Categories categories = new Categories();
        categories.setIdCategories(idCategories);
        customer.setCategories(categories);

        customerService.createCustomer(customer);
        return modelAndView;
    }

    @GetMapping("/edit")
    public ModelAndView showEdit(@RequestParam int id) {
        ModelAndView modelAndView = new ModelAndView("editCustomer");
        modelAndView.addObject("customer", customerService.findById(id));
        modelAndView.addObject("categories", categoriesService.getAllCategories());
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView edit(@RequestParam MultipartFile uppFile, @ModelAttribute("customer") Customer customer, @RequestParam int idCategories) {
        String nameImg = uppFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(uppFile.getBytes(), new File("D:\\Module 4\\Customers-Management\\src\\main\\webapp\\WEB-INF\\image\\" + nameImg));
            String urlImg = "/img/" + nameImg;
            customer.setImg(urlImg);
        } catch (IOException e) {
            System.err.println("chưa uppload file");
        }

        ModelAndView modelAndView = new ModelAndView("redirect:/");

        Categories categories = new Categories();
        categories.setIdCategories(idCategories);
        customer.setCategories(categories);

        customerService.editCustomer(customer);
        return modelAndView;
    }

    @PostMapping("/uppFile")
    public String uppFile(@RequestParam MultipartFile uppFile) {
        String fileName = uppFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(uppFile.getBytes(), new File("D:\\Module 4\\Customers-Management\\src\\main\\webapp\\WEB-INF\\image\\" + fileName));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "redirect:/";
    }

    @GetMapping("/delete")
    public ModelAndView delete(@RequestParam int id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        customerService.deleteCustomer(id);
        return modelAndView;
    }
}
