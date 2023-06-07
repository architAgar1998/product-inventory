package com.learning.productmanagementsystem.services;

import com.learning.productmanagementsystem.constants.Category;
import com.learning.productmanagementsystem.dtos.ProductDTO;
import com.learning.productmanagementsystem.entites.Product;
import com.learning.productmanagementsystem.exceptions.ProductNotFoundException;
import com.learning.productmanagementsystem.mappers.ProductMapper;
import com.learning.productmanagementsystem.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
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
    public List<ProductDTO> get() {
        return null;
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
        productRepository.deleteById(productId);
        return true;
    }

    @Override
    public boolean remove(Category category) {
        return false;
    }
}
