package com.learning.productmanagementsystem.services;

import com.learning.productmanagementsystem.constants.Category;
import com.learning.productmanagementsystem.constants.Country;
import com.learning.productmanagementsystem.dtos.ProductDTO;
import com.learning.productmanagementsystem.entites.Product;
import com.learning.productmanagementsystem.exceptions.PageNotFoundException;
import com.learning.productmanagementsystem.exceptions.ProductNotFoundException;
import com.learning.productmanagementsystem.mappers.ProductMapper;
import com.learning.productmanagementsystem.repositories.ProductRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

//    @PostConstruct
    public void createProducts() {
        Random random = new Random();
        for (int i = 1; i <= 20; i++) {
            Product product = Product.builder()
                    .name("Product" + i)
                    .price(random.nextDouble(1000))
                    .category(Category.ELECTRONICS)
                    .manufacturedBy("Samsung")
                    .origin(Country.INDIA)
                    .quantity(10)
                    .manufacturedDate(LocalDate.now())
                    .build();
            productRepository.save(product);
        }
    }

    @Override
    public ProductDTO create(ProductDTO productDTO) {
        Optional<Product> productOptional = productMapper.map(productDTO);
        if (productOptional.isEmpty()) {
            throw new IllegalArgumentException("Product can not be empty or null. Please provide a valid product.");
        }
        Product savedProduct = productRepository.save(
                productOptional.get()
        );
        productDTO.setId(savedProduct.getId());
        return productDTO;
    }

    @Override
    public ProductDTO get(int productId) {
        if (productId < 1) {
            throw new IllegalArgumentException("Product Id can not be less then 1. Please provide a valid product id");
        }
        Optional<Product> productOpt = productRepository.findById(productId);
        productOpt.orElseThrow(() -> new ProductNotFoundException(String.format("Unable to find the product with id %s", productId)));
        Optional<ProductDTO> productDTOOptional = productMapper.map(productOpt.get());
        return productDTOOptional.orElseThrow(() -> new ProductNotFoundException(String.format("Unable to find the product with id %s", productId)));
    }

    @Override
    public List<ProductDTO> getByPage(int page, int size) {
        Page<Product> products = productRepository.findAll(PageRequest.of(page, size));
        Optional<List<ProductDTO>> productDTOS = productMapper.map(products);
        return productDTOS.orElseThrow(() -> new PageNotFoundException("Page, you are requesting can not be found!."));
    }

    @Override
    public List<ProductDTO> get(Category category) {
        return null;
    }

    @Override
    public ProductDTO update(ProductDTO product) {
        return null;
    }

    @Override
    public boolean remove(int productId) {
        if (productId < 1) {
            throw new IllegalArgumentException("Product Id can not be less then 1. Please provide a valid product id");
        }
        if (!productRepository.existsById(productId)) {
            throw new ProductNotFoundException("Product with id " + productId + " does not found");
        }
        productRepository.deleteById(productId);
        return !productRepository.existsById(productId);
    }

    @Override
    public int remove(Category category) {
        return productRepository.deleteByCategory(category);
    }
}
