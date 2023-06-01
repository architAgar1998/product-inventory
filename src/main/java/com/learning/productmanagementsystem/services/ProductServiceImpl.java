package com.learning.productmanagementsystem.services;

import com.learning.productmanagementsystem.constants.Category;
import com.learning.productmanagementsystem.dtos.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public ProductDTO create(ProductDTO product) {
        return null;
    }

    @Override
    public ProductDTO get(int productId) {
        return null;
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
        return false;
    }

    @Override
    public boolean remove(Category category) {
        return false;
    }
}
