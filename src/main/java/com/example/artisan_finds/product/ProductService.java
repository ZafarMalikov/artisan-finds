package com.example.artisan_finds.product;

import com.example.artisan_finds.category.entity.CategoryType;
import com.example.artisan_finds.category.entity.SubCategories;
import com.example.artisan_finds.common.service.GenericCrudService;
import com.example.artisan_finds.product.dto.ProductCreateDto;
import com.example.artisan_finds.product.dto.ProductPatchDto;
import com.example.artisan_finds.product.dto.ProductResponseDto;
import com.example.artisan_finds.product.dto.ProductUpdateDto;
import com.example.artisan_finds.product.entity.Product;
import com.example.artisan_finds.user.UserRepository;
import com.example.artisan_finds.user.entity.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Getter
@RequiredArgsConstructor
public class ProductService extends GenericCrudService<Product, Integer, ProductCreateDto, ProductUpdateDto, ProductPatchDto, ProductResponseDto> {

    private final ProductRepository repository;
    private final ProductDtoMapper mapper;
    private final Class<Product>entityClass=Product.class;
    private final UserRepository userRepository;


    @Override
    protected Product save(ProductCreateDto productCreateDto) {

        User user = userRepository.findUserByPhoneNumber(
                SecurityContextHolder.getContext().getAuthentication().getName()
                )
                .orElseThrow(EntityNotFoundException::new);

        Product product = mapper.toEntity(productCreateDto);

        product.setUser(user);


        return repository.save(product);

    }

    @Override
    protected Product updateEntity(ProductUpdateDto productUpdateDto, Product product) {
         mapper.update(productUpdateDto,product);
         return repository.save(product);
    }



    public List<ProductResponseDto> getAllByType(CategoryType categoryType, Pageable pageable, String predicate) {
       return repository.findAll()
               .stream()
               .filter(product -> product.getCategoryType()==categoryType)
               .map(mapper::toResponseDto)
               .toList();
    }

    public List<ProductResponseDto> getAllBySubCategories(SubCategories subCategories) {
        return repository.findAll()
                .stream()
                .filter(product -> product.getSubCategories()==subCategories)
                .map(mapper::toResponseDto)
                .toList();
    }
}
