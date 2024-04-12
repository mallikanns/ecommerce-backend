package com.mallika.ecom.services.admin.adminproduct;

import com.mallika.ecom.dto.ProductDto;
import com.mallika.ecom.entity.Category;
import com.mallika.ecom.entity.Product;
import com.mallika.ecom.repository.CategoryRepository;
import com.mallika.ecom.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminProductServiceImpl implements AdminProductService {
    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    public ProductDto addProduct(ProductDto productDto) throws IOException {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImg(productDto.getImg().getBytes());

        Category category = categoryRepository.findById(productDto.getCategoryId()).orElseThrow();

        product.setCategory(category);
        return productRepository.save(product).getDto();
    }

    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
//        List<ProductDto> re = new ArrayList<ProductDto>();
//        for (Product product : products){
//            re.add(product.getDto());
//        }
//        return re;
        return products.stream().map(Product::getDto).collect(Collectors.toList());
//        return products.stream().map(product -> product.getDto()).collect(Collectors.toList());
    }
}
