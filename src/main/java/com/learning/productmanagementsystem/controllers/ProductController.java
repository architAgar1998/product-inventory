package com.learning.productmanagementsystem.controllers;

import com.learning.productmanagementsystem.constants.Category;
import com.learning.productmanagementsystem.dtos.ProductDTO;
import com.learning.productmanagementsystem.dtos.Response;
import com.learning.productmanagementsystem.entites.Product;
import com.learning.productmanagementsystem.exceptions.PageNotFoundException;
import com.learning.productmanagementsystem.exceptions.ProductNotFoundException;
import com.learning.productmanagementsystem.services.ProductService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Response<ProductDTO> create(@Valid @RequestBody ProductDTO product) {
        Response<ProductDTO> productDTOResponse = new Response<>();
        try {
            ProductDTO productDTO = productService.create(product);
            productDTOResponse.setSuccessResponse(productDTO);
        } catch (Exception exception) {
           productDTOResponse.setFailureResponse(500, "Unable to create the product.");
        }
       return productDTOResponse;
    }

    @GetMapping("/{productId}")
    public Response<ProductDTO> get(@PathVariable(name = "productId") int productId) {
        Response<ProductDTO> productDTOResponse = new Response<>();
        try {
            ProductDTO productDTO = productService.get(productId);
            productDTOResponse.setSuccessResponse(productDTO);
        } catch (IllegalArgumentException | ProductNotFoundException exception) {
            productDTOResponse.setFailureResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage());
        } catch (Exception exception) {
            productDTOResponse.setFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Something went wrong while fetching product with product id: " + productId);
        }
        return productDTOResponse;
    }

    @DeleteMapping
    public Response<ProductDTO> remove(@RequestParam("productId") int productId) {
        Response<ProductDTO> productDTOResponse = new Response<>();
        try {
            boolean removed = productService.remove(productId);
            if (removed) {
                productDTOResponse.setSuccessResponse(HttpStatus.OK.value(), "Product " + productId + " deleted", null);
            } else {
                productDTOResponse.setFailureResponse(404, "Product with Id " + productId + " not found");
            }
        } catch (IllegalArgumentException | ProductNotFoundException exception) {
            productDTOResponse.setFailureResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage());
        } catch (Exception exception) {
            productDTOResponse.setFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Something went wrong while deleting product with product id: " + productId);
        }
        return productDTOResponse;
    }

    @DeleteMapping("/{category}")
    public Response<ProductDTO> remove(@PathVariable Category category) {
        Response<ProductDTO> productDTOResponse = new Response<>();
        try {
            boolean removed = productService.remove(category);
            if (removed) {
                productDTOResponse.setSuccessResponse(null);
            }
        } catch (Exception exception) {
            productDTOResponse.setFailureResponse(500, "can not delete " + category + " products");
            exception.printStackTrace();
        }
        return productDTOResponse;
    }

    @GetMapping("page")
    public Response<List<ProductDTO>> getByPage(@RequestParam(name = "page") int page, @RequestParam("size") int size) {
        Response<List<ProductDTO>> response = new Response<>();
        try {
            List<ProductDTO> productDTOS = productService.getByPage(page, size);
            response.setSuccessResponse(productDTOS);
        } catch (PageNotFoundException exception) {
            response.setFailureResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage());
        } catch (Exception exception) {
            response.setFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Unable to find out the request page. Due to technical error. " + exception.getMessage());
        }
        return response;
    }
}
