package com.learning.productmanagementsystem.controllers;

import com.learning.productmanagementsystem.dtos.ProductDTO;
import com.learning.productmanagementsystem.dtos.Response;
import com.learning.productmanagementsystem.entites.Product;
import com.learning.productmanagementsystem.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private Response<ProductDTO> productDTOResponse;

    @Autowired
    public ProductController(ProductService productService, Response<ProductDTO> productDTOResponse) {
        this.productService = productService;
        this.productDTOResponse = productDTOResponse;
    }

    @PostMapping
    public Response<ProductDTO> create(@RequestBody ProductDTO product) {
        try {
            ProductDTO productDTO = productService.create(product);
            productDTOResponse.setSuccessResponse(productDTO);
        } catch (Exception exception) {
            productDTOResponse.setFailureResponse(500, "Unable to create the product.");
        }
       return productDTOResponse;
    }
}
