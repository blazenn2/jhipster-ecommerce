package com.blazenn.ecommerce.service.dto;

import javax.validation.constraints.*;

import com.blazenn.ecommerce.domain.Category;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DTO for the {@link com.blazenn.ecommerce.domain.Product} entity.
 */
public class ProductDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    private String description;

    @NotNull
    private BigDecimal price;

    private Category category;

    // private Long categoryId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    // public Long getCategoryId() {
    //     return categoryId;
    // }

    // public void setCategoryId(Long categoryId) {
    //     this.categoryId = categoryId;
    // }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductDTO)) {
            return false;
        }

        return id != null && id.equals(((ProductDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProductDTO{" +
                "id=" + getId() +
                ", name='" + getName() + "'" +
                ", description='" + getDescription() + "'" +
                ", price=" + getPrice() +
                // ", categoryId=" + getCategoryId() +
                "}";
    }
}
