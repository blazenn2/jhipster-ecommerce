package com.blazenn.ecommerce.service;

import com.blazenn.ecommerce.domain.Category;
import com.blazenn.ecommerce.domain.Product;
import com.blazenn.ecommerce.repository.ProductRepository;
import com.blazenn.ecommerce.service.dto.CategoryDTO;
import com.blazenn.ecommerce.service.dto.ProductDTO;
import com.blazenn.ecommerce.service.mapper.CategoryMapper;
import com.blazenn.ecommerce.service.mapper.ProductMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Product}.
 */
@Service
@Transactional
public class ProductService {

    private final Logger log = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    private final CategoryService categoryService;

    private final CategoryMapper categoryMapper;


    public ProductService(ProductRepository productRepository, ProductMapper productMapper, CategoryService categoryService, CategoryMapper categoryMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    private static class CategoryResourceException extends RuntimeException {
        private CategoryResourceException(String message) {
            super(message);
        }
    }

    /**
     * Save a product.
     *
     * @param productDTO the entity to save.
     * @return the persisted entity.
     */
    public ProductDTO save(ProductDTO productDTO) {
        log.debug("Request to save Product : {}", productDTO);
        if (productDTO.getCategoryId() != null) {
            CategoryDTO categoryDTO = categoryService.findOne(productDTO.getCategoryId()).orElseThrow(() -> new CategoryResourceException("Category could not be found"));
            Category category = categoryMapper.toEntity(categoryDTO);
            productDTO.setCategory(category);
        }
        Product product = productMapper.toEntity(productDTO);
        product = productRepository.save(product);
        return productMapper.toDto(product);
    }

    /**
     * Get all the products.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Products");
        return productRepository.findAll(pageable)
            .map(productMapper::toDto);
    }


    /**
     * Get one product by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ProductDTO> findOne(Long id) {
        log.debug("Request to get Product : {}", id);
        return productRepository.findById(id)
            .map(productMapper::toDto);
    }

    /**
     * Delete the product by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Product : {}", id);
        productRepository.deleteById(id);
    }
}
