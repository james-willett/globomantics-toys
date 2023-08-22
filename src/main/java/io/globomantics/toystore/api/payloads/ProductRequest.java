package io.globomantics.toystore.api.payloads;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class ProductRequest {

    @Schema(example = "Toy train", required = true)
    @NotNull(message = "Please set a name")
    @Size(min = 2, message = "Name must be at least 2 characters long")
    private String name;

    @Schema(example = "A toy train with 3 carriages", required = true)
    @NotNull(message = "a toy train")
    @Size(min = 5, message = "Description must be at least 5 characters long")
    private String description;

    @Schema(example = "19.99", required = true)
    @NotNull(message = "Please set a price")
    @Pattern(regexp = "^[0-9]+([.][0-9]{1,2})?", message = "Expected format: 5, 5.99, 15, 15.99")
    private String price;

    @Schema(example = "1", required = true)
    @NotNull(message = "Please choose a category")
    @Positive(message = "Please choose a valid category")
    private Integer categoryId;

    @Schema(required = true)
    @NotNull(message = "Please set if product is in stock")
    private Boolean inStock;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Boolean getInStock() {
        return this.inStock;
    }

    public void setInStock(Boolean inStock) {
        this.inStock = inStock;
    }
}
