package com.example.artisan_finds.product;


import com.example.artisan_finds.common.service.GenericDtoMapper;
import com.example.artisan_finds.product.dto.ProductCreateDto;
import com.example.artisan_finds.product.dto.ProductResponseDto;
import com.example.artisan_finds.product.dto.ProductUpdateDto;
import com.example.artisan_finds.product.entity.Product;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductDtoMapper extends GenericDtoMapper<Product, ProductCreateDto, ProductUpdateDto, ProductResponseDto> {

    private final ModelMapper modelMapper;
    @Override
    public Product toEntity(ProductCreateDto productCreateDto) {
        return modelMapper.map(productCreateDto,Product.class) ;
    }

    @Override
    public ProductResponseDto toResponseDto(Product product) {
        return modelMapper.map(product, ProductResponseDto.class);
    }

    @Override
    public void update(ProductUpdateDto productUpdateDto, Product product) {
        modelMapper.map(productUpdateDto,product);
    }

    @Override
    public ProductCreateDto toCreateDto(Product product) {
        return modelMapper.map(product, ProductCreateDto.class);
    }
}
