package com.inventoryapp.services;

import com.inventoryapp.controllers.InventoryController;
import com.inventoryapp.dtos.InventoryDTO;
import com.inventoryapp.dtos.ProductDTO;
import com.inventoryapp.entities.InventoryMovement;
import com.inventoryapp.entities.Product;
import com.inventoryapp.entities.TypeEnum;
import com.inventoryapp.entities.User;
import com.inventoryapp.repositories.InventoryMovementRepository;
import com.inventoryapp.repositories.ProductRepository;
import com.inventoryapp.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryMovementServicesImpl implements InventoryMovementService{

    private ModelMapper modelMapper = new ModelMapper();

    private static final Logger logger = LoggerFactory.getLogger(InventoryMovementServicesImpl.class);

    @Autowired
    private InventoryMovementRepository inventoryMovementRepository;

    @Autowired
    private ProductServicesImpl productServices;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<InventoryDTO> findAll() {
        return inventoryMovementRepository.findAll().stream().map(i -> modelMapper.map(i, InventoryDTO.class)).collect(Collectors.toList());
    }

    @Override
    public InventoryDTO findById(Long id) {
        return inventoryMovementRepository.findById(id).map(i -> modelMapper.map(i, InventoryDTO.class)).orElse(null);
    }


    //Debemos factorizar este metodo o mejorarlo o optimizarlo
    @Override
    public InventoryDTO saveEntry(InventoryDTO inventoryDTO) {
        if(inventoryDTO != null){
            if("ENTRY".equalsIgnoreCase(inventoryDTO.getType())){
                if(inventoryDTO.getProductId() != null){

                    //encontramos el producto pror el ID
                    Product product = productRepository.findById(inventoryDTO.getProductId()).orElse(null);

                    int stock = updateProductStock(product.getStock(), inventoryDTO.getType(), inventoryDTO.getQuantity());

//                    Actualizamos el producto
                    ProductDTO productDTO = getProductDTO(product, stock);

                    productServices.update(product.getId(), productDTO);
                    User user = userRepository.findById(inventoryDTO.getUserId()).orElse(null);

                    //agregamos el inventario
                    InventoryMovement inventory = InventoryMovement.builder()
                            .user(user)
                            .product(product)
                            .quantity(inventoryDTO.getQuantity())
                            .type(TypeEnum.ENTRY)
                            .date(new Date())
                            .build();


                    inventoryMovementRepository.save(inventory);
                    return  modelMapper.map(inventory, InventoryDTO.class);
                }
            }
        }
        return null;
    }

    @Override
    public InventoryDTO updateEntry(Long id, InventoryDTO inventoryDTO) {
        if(inventoryDTO != null){
            if("ENTRY".equalsIgnoreCase(inventoryDTO.getType())){
                if(inventoryDTO.getProductId() != null){

                    InventoryDTO oldInventory = findById(id);

                    //encontramos el producto pror el ID
                    Product product = productRepository.findById(inventoryDTO.getProductId()).orElse(null);

                    int stock = updateProductStock(product.getStock()-oldInventory.getQuantity(), inventoryDTO.getType(), inventoryDTO.getQuantity());

//                    Actualizamos el producto
                    ProductDTO productDTO = getProductDTO(product, stock);

                    productServices.update(product.getId(), productDTO);
                    User user = userRepository.findById(inventoryDTO.getUserId()).orElse(null);

                    //agregamos el inventario
                    InventoryMovement inventory = InventoryMovement.builder()
                            .id(oldInventory.getId())
                            .user(user)
                            .product(product)
                            .quantity(inventoryDTO.getQuantity())
                            .type(TypeEnum.ENTRY)
                            .date(new Date())
                            .build();


                    inventoryMovementRepository.save(inventory);
                    return  modelMapper.map(inventory, InventoryDTO.class);
                }
            }
        }
        return null;
    }

    private int updateProductStock(int stock, String type, int quantity){
        if ("ENTRY".equalsIgnoreCase(type)) {
            stock = stock + quantity;
            return stock;
        } else if ("EXIT".equalsIgnoreCase(type)) {
            if (quantity <= stock) {
                stock =  stock - quantity;
                return stock;
            } else{
                throw new RuntimeException("quantity exceeds stock");
            }
        } else {
            throw new RuntimeException("type is not valid. please type ENTRY or EXIT");
        }
    }

    @Override
    public InventoryDTO saveExit(InventoryDTO inventoryMovement) {
        if(inventoryMovement != null){
            if("EXIT".equalsIgnoreCase(inventoryMovement.getType())){
                if(inventoryMovement.getProductId() != null){

                    //encontramos el producto por el ID, ya que le realizaremos cambios
                    Product product = productRepository.findById(inventoryMovement.getProductId()).orElse(null);

                    int stock = updateProductStock(product.getStock(), inventoryMovement.getType(), inventoryMovement.getQuantity());

//                    Actualizamos el producto
                    ProductDTO productDTO = getProductDTO(product, stock);

                    productServices.update(product.getId(), productDTO);

                    User user = userRepository.findById(inventoryMovement.getUserId()).orElse(null);

                    //agregamos el inventario
                    InventoryMovement inventory = InventoryMovement.builder()
                            .user(user)
                            .product(product)
                            .quantity(inventoryMovement.getQuantity())
                            .type(TypeEnum.EXIT)
                            .date(new Date())
                            .build();

                    inventoryMovementRepository.save(inventory);
                    return modelMapper.map(inventory, InventoryDTO.class);
                }
            }
        }
        return null;
    }

    @Override
    public InventoryDTO updateExit(Long id, InventoryDTO inventoryDTO) {
        if(inventoryDTO != null && id != null){
            if("EXIT".equalsIgnoreCase(inventoryDTO.getType())){
                if(inventoryDTO.getProductId() != null){

                    InventoryDTO oldInventory = findById(id);

                    //encontramos el producto por el ID, ya que le realizaremos cambios
                    Product product = productRepository.findById(inventoryDTO.getProductId()).orElse(null);

                    int stock = updateProductStock(product.getStock()+oldInventory.getQuantity(), inventoryDTO.getType(), inventoryDTO.getQuantity());

//                    Actualizamos el producto
                    ProductDTO productDTO = getProductDTO(product, stock);

                    productServices.update(product.getId(), productDTO);

                    User user = userRepository.findById(inventoryDTO.getUserId()).orElse(null);

                    //agregamos el inventario
                    InventoryMovement inventory = InventoryMovement.builder()
                            .id(oldInventory.getId())
                            .user(user)
                            .product(product)
                            .quantity(inventoryDTO.getQuantity())
                            .type(TypeEnum.EXIT)
                            .date(new Date())
                            .build();

                    inventoryMovementRepository.save(inventory);
                    return modelMapper.map(inventory, InventoryDTO.class);
                }
            }
        }
        return null;
    }

    private static ProductDTO getProductDTO(Product product, int stock) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setCode(product.getCode());
        productDTO.setCreatedAt(product.getCreatedAt());
        productDTO.setExpDate(product.getExpDate());
        productDTO.setCostPrice(product.getCostPrice());
        productDTO.setDescription(product.getDescription());
        productDTO.setCategoryId(product.getCategory().getId());
        productDTO.setWarehouseId(product.getWarehouse().getId());
        // agregamos los productos a los que se le dieron entrada y salida al stock
        productDTO.setStock(stock);
        productDTO.setAvailable(true);
        return productDTO;
    }

    @Override
    public String delete(Long id) {
        return "";
    }
}
