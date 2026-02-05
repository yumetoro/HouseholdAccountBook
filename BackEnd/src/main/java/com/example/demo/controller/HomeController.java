package com.example.demo.controller;

import com.example.demo.model.Category;
import com.example.demo.model.Transaction;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.TransactionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class HomeController {

    private final CategoryRepository categoryRepository;
    private final TransactionRepository transactionRepository;

    public HomeController(CategoryRepository categoryRepository, TransactionRepository transactionRepository) {
        this.categoryRepository = categoryRepository;
        this.transactionRepository = transactionRepository;
    }

    @GetMapping("/")
    public String dashboard(Model model) {
        LocalDate now = LocalDate.now();
        int year = now.getYear();
        int month = now.getMonthValue();

        Integer income = transactionRepository.getTotalByTypeAndMonth("income", year, month);
        Integer expense = transactionRepository.getTotalByTypeAndMonth("expense", year, month);

        model.addAttribute("year", year);
        model.addAttribute("month", month);
        model.addAttribute("income", income != null ? income : 0);
        model.addAttribute("expense", expense != null ? expense : 0);
        model.addAttribute("balance", (income != null ? income : 0) - (expense != null ? expense : 0));
        model.addAttribute("currentPage", "dashboard");

        return "dashboard";
    }

    @GetMapping("/income")
    public String income(Model model) {
        List<Transaction> transactions = transactionRepository.findByTypeOrderByTransactionDateDesc("income");
        List<Category> categories = categoryRepository.findByType("income");

        model.addAttribute("transactions", transactions);
        model.addAttribute("categories", categories);
        model.addAttribute("type", "income");
        model.addAttribute("title", "収入");
        model.addAttribute("currentPage", "income");

        return "transactions";
    }

    @GetMapping("/expense")
    public String expense(Model model) {
        List<Transaction> transactions = transactionRepository.findByTypeOrderByTransactionDateDesc("expense");
        List<Category> categories = categoryRepository.findByType("expense");

        model.addAttribute("transactions", transactions);
        model.addAttribute("categories", categories);
        model.addAttribute("type", "expense");
        model.addAttribute("title", "支出");
        model.addAttribute("currentPage", "expense");

        return "transactions";
    }

    @PostMapping("/transaction/add")
    public String addTransaction(@RequestParam Integer amount,
                                  @RequestParam Long categoryId,
                                  @RequestParam String description,
                                  @RequestParam String date,
                                  @RequestParam String type) {
        Transaction t = new Transaction();
        t.setAmount(amount);
        t.setCategory(categoryRepository.findById(categoryId).orElse(null));
        t.setDescription(description);
        t.setTransactionDate(LocalDate.parse(date));
        t.setType(type);
        transactionRepository.save(t);

        return "redirect:/" + type;
    }

    @PostMapping("/transaction/delete/{id}")
    public String deleteTransaction(@PathVariable Long id, @RequestParam String type) {
        transactionRepository.deleteById(id);
        return "redirect:/" + type;
    }

    @GetMapping("/category")
    public String category(Model model) {
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("currentPage", "category");
        return "category";
    }

    @PostMapping("/category/add")
    public String addCategory(@RequestParam String name, @RequestParam String type) {
        Category c = new Category(name, type);
        categoryRepository.save(c);
        return "redirect:/category";
    }

    @PostMapping("/category/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryRepository.deleteById(id);
        return "redirect:/category";
    }
}
