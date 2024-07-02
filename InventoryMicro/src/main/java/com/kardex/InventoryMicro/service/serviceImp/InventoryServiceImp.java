package com.kardex.InventoryMicro.service.serviceImp;


import com.kardex.InventoryMicro.client.ConsumeApi;
import com.kardex.InventoryMicro.client.ProductDto;
import com.kardex.InventoryMicro.dto.InventoryDto;
import com.kardex.InventoryMicro.dto.errors.SystemException;
import com.kardex.InventoryMicro.entity.InventoryEntity;
import com.kardex.InventoryMicro.mapper.InventoryMapper;
import com.kardex.InventoryMicro.repository.InventoryRepository;
import com.kardex.InventoryMicro.response.ResponseWrapper;
import com.kardex.InventoryMicro.service.InventoryService;
import com.kardex.InventoryMicro.utils.Constans;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Date;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class InventoryServiceImp implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final InventoryMapper inventoryMapper;
    private final ConsumeApi consumeMockApiUtil;

    public Optional<InventoryEntity> findByInventoryId(String id){
        Optional<InventoryEntity> getProduct = inventoryRepository.findById( new BigInteger(id));

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
    public ResponseEntity<InventoryDto> getInventoryId(String id) {
        Optional<InventoryEntity> getInventoryId = this.findByInventoryId(id);

        return ResponseEntity.ok(inventoryMapper.toInventoryDto(getInventoryId.get()));

    }

    @Override
    public ResponseWrapper<InventoryDto> createInventory(InventoryDto inventoryDto) {

        ProductDto productClient = consumeMockApiUtil.consumeMockApi(inventoryDto.getProductId().toString());
        this.validateProductClientNull(productClient);

        Date currentDate = new Date();

        Integer lastId = inventoryRepository.findTopByOrderByIdDesc().map(InventoryEntity::getId).orElse(0);
        Integer newId = lastId + 1;

        InventoryEntity inventoryCreate = InventoryEntity.builder()
                .id (newId)
                .productId(inventoryDto.getProductId())
                .quantity(inventoryDto.getQuantity())
                .updated_at(currentDate)
                .build();

        InventoryEntity inventoryEntity = inventoryRepository.save(inventoryCreate);



        return new ResponseWrapper<>(
                "Product created with success", inventoryMapper.toInventoryDto(inventoryEntity));
    }

    @Override
    public ResponseWrapper<InventoryDto> updateInventory(InventoryDto inventory) {

        Optional<InventoryEntity> optionalProduct = this.findByInventoryId(inventory.getIdInventory().toString());
        InventoryEntity inventoryEntity = optionalProduct.get();
        inventoryEntity.setProductId(inventory.getProductId());
        inventoryEntity.setUpdated_at(inventory.getUpdated_at());
        inventoryEntity.setQuantity(inventory.getQuantity());
        InventoryEntity updatedProductEntity = inventoryRepository.save(inventoryEntity);

        return new ResponseWrapper<>("Product updated with success",
                inventoryMapper.toInventoryDto(updatedProductEntity));
    }

    public void validateProductClientNull(ProductDto productClientDto){
        if (productClientDto == null){
            throw SystemException.builder()
                    .code(Constans.LOG_ERROR_002)
                    .description("Product "+Constans.LOG_ERROR_002_DESCRIPTION)
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .tecError(Constans.VALIDATION_ERROR)
                    .build();
        }
    }
}
