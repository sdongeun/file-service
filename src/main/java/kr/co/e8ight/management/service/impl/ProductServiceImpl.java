package kr.co.e8ight.management.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kr.co.e8ight.management.domain.entity.Product;
import kr.co.e8ight.management.domain.repository.ProductRepository;
import kr.co.e8ight.management.dto.ProductDto;
import kr.co.e8ight.management.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    public ProductServiceImpl() {
    }

    @Transactional
    public Product addProduct(Product product) {
        return (Product)this.productRepository.save(product);
    }

    @Transactional(
            readOnly = true
    )
    public List<ProductDto> getProducts() {
        List<Product> products = this.productRepository.findAll();
        List<ProductDto> productDtoList = new ArrayList();
        Iterator var3 = products.iterator();

        while(var3.hasNext()) {
            Product product = (Product)var3.next();
            ProductDto productDto = new ProductDto();
            productDto.setId(product.getId());
            productDto.setName(product.getName());
            productDto.setDescription(product.getDescription());
            productDtoList.add(productDto);
        }

        return productDtoList;
    }
}
