package com.amazon.ecommerce.controller.view;

import com.amazon.ecommerce.model.Product;

import java.util.List;

public record CategoryCard(String title, List<Product> products) {}

