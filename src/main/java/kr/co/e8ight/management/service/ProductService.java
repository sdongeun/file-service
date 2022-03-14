package kr.co.e8ight.management.service;

import java.util.List;
import kr.co.e8ight.management.domain.entity.Product;
import kr.co.e8ight.management.dto.ProductDto;

public interface ProductService {
    Product addProduct(Product product);

    List<ProductDto> getProducts();
}
