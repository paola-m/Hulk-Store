package com.kardex.ProductMicro.service.serviceImp;

import com.kardex.ProductMicro.dto.ProductDto;
import com.kardex.ProductMicro.dto.errors.SystemException;
import com.kardex.ProductMicro.entity.ProductEntity;
import com.kardex.ProductMicro.mapper.ProductMapper;
import com.kardex.ProductMicro.repository.ProductRepository;
import com.kardex.ProductMicro.response.ResponseWrapper;
import com.kardex.ProductMicro.service.ProductService;
import com.kardex.ProductMicro.utils.Constans;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;

import java.math.BigInteger;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public Optional<ProductEntity> findByProductId(String id){
        Optional<ProductEntity>getProduct = productRepository.findById( new BigInteger(id));

        if (getProduct.isPresent()){
            return getProduct;
        }else {
            throw SystemException.builder()
                    .code(Constans.LOG_ERROR_002)
                    .description("Product "+Constans.LOG_ERROR_002_DESCRIPTION)
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .tecError(Constans.VALIDATION_ERROR)
                    .build();
        }
    }

    @Override
    public ResponseEntity<ProductDto> getProductId(String id) {
        Optional<ProductEntity> getProduct = this.findByProductId(id);

        return ResponseEntity.ok(productMapper.toProductDto(getProduct.get()));

    }

    @Override
    public ResponseWrapper<ProductDto> createProduct(ProductDto product) {

        Date currentDate = new Date();

        Integer lastId = productRepository.findTopByOrderByIdProductDesc().map(ProductEntity::getIdProduct).orElse(0);
        Integer newId = lastId + 1;

        ProductEntity productCreate = ProductEntity.builder()
                .idProduct(newId)
                .name(product.getName())
                .categoryProduct(product.getCategoryProduct())
                .description(product.getDescription())
                .created_at(currentDate)
                .build();

        ProductEntity productEntity = productRepository.save(productCreate);



        return new ResponseWrapper<>(
                "Product created with success", productMapper.toProductDto(productEntity));
    }

    @Override
    public ResponseWrapper<ProductDto> updateProduct(ProductDto product) {

        Optional<ProductEntity> optionalProduct = this.findByProductId(product.getIdProduct().toString());
        ProductEntity productEntity = optionalProduct.get();
        productEntity.setName(product.getName());
        productEntity.setCategoryProduct(product.getCategoryProduct());
        productEntity.setDescription(product.getDescription());
        ProductEntity updatedProductEntity = productRepository.save(productEntity);

        return new ResponseWrapper<>("Product updated with success", productMapper.toProductDto(updatedProductEntity));
    }
}
