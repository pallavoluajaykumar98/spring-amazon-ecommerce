package com.amazon.ecommerce.controller;

import com.amazon.ecommerce.controller.view.CategoryCard;
import com.amazon.ecommerce.model.Product;
import com.amazon.ecommerce.service.CartService;
import com.amazon.ecommerce.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    private final ProductService productService;
    private final CartService cartService;

    public HomeController(ProductService productService, CartService cartService) {
        this.productService = productService;
        this.cartService = cartService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        model.addAttribute("cartCount", cartService.getCartItems().size()); // ✅ important for header
        model.addAttribute("categoryCards", buildCategoryCards(products));
        return "home";
    }

    @GetMapping("/product/{id}")
    public String productDetail(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("cartCount", cartService.getCartItems().size()); // ✅ for header
        return "product";
    }

    private static List<CategoryCard> buildCategoryCards(List<Product> products) {
        Map<String, List<Product>> byCategory = new LinkedHashMap<>();
        for (Product p : products) {
            String category = (p.getCategory() == null || p.getCategory().isBlank()) ? "Featured" : p.getCategory();
            byCategory.computeIfAbsent(category, k -> new ArrayList<>()).add(p);
        }

        List<CategoryCard> cards = new ArrayList<>();
        for (Map.Entry<String, List<Product>> entry : byCategory.entrySet()) {
            List<Product> items = entry.getValue();
            cards.add(new CategoryCard(entry.getKey(), items.size() > 2 ? items.subList(0, 2) : items));
            if (cards.size() >= 4) break;
        }
        return cards;
    }
}