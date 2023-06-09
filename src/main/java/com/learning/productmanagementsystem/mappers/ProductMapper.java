package com.learning.productmanagementsystem.mappers;

import com.learning.productmanagementsystem.dtos.ProductDTO;
import com.learning.productmanagementsystem.entites.Product;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    public Optional<Product> map(ProductDTO productDTO) {
        if (Objects.isNull(productDTO)) {
            return Optional.empty();
        }
        Product product = Product
                .builder()
                .name(productDTO.getName())
                .origin(productDTO.getOrigin())
                .category(productDTO.getCategory())
                .quantity(productDTO.getQuantity())
                .manufacturedDate(productDTO.getManufacturedDate())
                .manufacturedBy(productDTO.getManufacturedBy())
                .price(productDTO.getPrice())
                .build();
        return Optional.of(product);
    }

    public Optional<ProductDTO> map(Product product) {
        if (Objects.isNull(product)) {
            return Optional.empty();
        }
       ProductDTO productDTO = ProductDTO
               .builder()
               .id(product.getId())
               .category(product.getCategory())
               .manufacturedBy(product.getManufacturedBy())
               .manufacturedDate(product.getManufacturedDate())
               .name(product.getName())
               .origin(product.getOrigin())
               .price(product.getPrice())
               .quantity(product.getQuantity())
               .build();
        return Optional.of(productDTO);
    }

    public Optional<List<ProductDTO>> map(Page<Product> productPage) {
        if (Objects.isNull(productPage) || productPage.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(productPage
                .getContent()
                .stream().map(product -> ProductDTO
                        .builder()
                        .id(product.getId())
                        .name(product.getName())
                        .manufacturedBy(product.getManufacturedBy())
                        .manufacturedDate(product.getManufacturedDate())
                        .category(product.getCategory())
                        .origin(product.getOrigin())
                        .price(product.getPrice())
                        .quantity(product.getQuantity())
                        .build())
                .collect(Collectors.toList()));
    }
}
