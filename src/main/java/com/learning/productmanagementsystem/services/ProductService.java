package com.learning.productmanagementsystem.services;

import com.learning.productmanagementsystem.constants.Category;
import com.learning.productmanagementsystem.dtos.ProductDTO;

import java.util.List;

public interface ProductService {
    public ProductDTO create(ProductDTO product);
    public ProductDTO get(int productId);
    public List<ProductDTO> getByPage(int page, int size);
    public List<ProductDTO> get(Category category);
    public ProductDTO update(ProductDTO product);
    public boolean remove(int productId);
    public int remove(Category category);
}
