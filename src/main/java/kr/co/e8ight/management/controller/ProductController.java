package kr.co.e8ight.management.controller;

import java.util.List;
import kr.co.e8ight.management.domain.entity.Product;
import kr.co.e8ight.management.dto.ProductDto;
import kr.co.e8ight.management.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"api"})
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping({"/product"})
    public Product createProduct(@RequestBody Product product) {
        return this.service.addProduct(product);
    }

    @GetMapping({"/products"})
    public List<ProductDto> findProducts() {
        return this.service.getProducts();
    }
}
