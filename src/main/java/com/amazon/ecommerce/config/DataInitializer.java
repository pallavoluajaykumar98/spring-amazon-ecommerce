package com.amazon.ecommerce.config;

import com.amazon.ecommerce.model.Product;
import com.amazon.ecommerce.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner seedProducts(ProductRepository productRepository) {
        return args -> {
            if (productRepository.count() > 0) {
                return;
            }

            productRepository.saveAll(List.of(
                    new Product(
                            "Voltas 1.5 Ton Split AC",
                            "Appliances",
                            "Copper condenser, energy efficient cooling.",
                            "https://m.media-amazon.com/images/I/61D%2BQY1qg-L._SX679_.jpg",
                            33999.0
                    ),
                    new Product(
                            "LG 260L Double Door Refrigerator",
                            "Appliances",
                            "Smart inverter compressor, fast ice making.",
                            "https://m.media-amazon.com/images/I/71Gxqf1YkTL._SX679_.jpg",
                            26990.0
                    ),
                    new Product(
                            "boAt Rockerz 450 Headphones",
                            "Electronics",
                            "Wireless, 15H playback, immersive bass.",
                            "https://m.media-amazon.com/images/I/61u1VALn6JL._SX679_.jpg",
                            1499.0
                    ),
                    new Product(
                            "Fire TV Stick (HD)",
                            "Electronics",
                            "Stream in HD with Alexa Voice Remote.",
                            "https://m.media-amazon.com/images/I/51CgKGfMelL._SX679_.jpg",
                            3299.0
                    ),
                    new Product(
                            "Men's Running Shoes",
                            "Fashion",
                            "Lightweight, breathable mesh upper.",
                            "https://m.media-amazon.com/images/I/71iYxgL7VtL._SY695_.jpg",
                            1799.0
                    ),
                    new Product(
                            "Women's Casual Top",
                            "Fashion",
                            "Soft cotton blend, everyday comfort fit.",
                            "https://m.media-amazon.com/images/I/71kTt3s7z-L._SY741_.jpg",
                            599.0
                    ),
                    new Product(
                            "Non-stick Cookware Set",
                            "Home & Kitchen",
                            "Durable non-stick coating, easy to clean.",
                            "https://m.media-amazon.com/images/I/61oF2uM1YyL._SX679_.jpg",
                            2199.0
                    ),
                    new Product(
                            "Decorative Table Lamp",
                            "Home & Kitchen",
                            "Warm ambient light, modern bedside lamp.",
                            "https://m.media-amazon.com/images/I/61uE3qg4jYL._SX679_.jpg",
                            1299.0
                    )
            ));
        };
    }
}

