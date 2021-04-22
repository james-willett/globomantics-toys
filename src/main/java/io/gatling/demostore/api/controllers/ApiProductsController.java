package io.gatling.demostore.api.controllers;

import io.gatling.demostore.api.payloads.ProductRequest;
import io.gatling.demostore.models.CategoryRepository;
import io.gatling.demostore.models.ProductRepository;
import io.gatling.demostore.models.data.Product;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

// @Tag(name = "products", description = "Products") // Should be fixed in springfox 3.0.1, disabled for now...
@RestController
@RequestMapping("/api/product")
public class ApiProductsController {

    public ApiProductsController(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Operation(summary = "List all products")
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<Product> listProducts(@RequestParam(value = "category", required = false) String categoryId) {
        if (categoryId == null) {
            return productRepository.findAll();
        } else {
            return productRepository.findAllByCategoryId(categoryId, Pageable.unpaged());
        }
    }

    @Operation(summary = "Get a product")
    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public Product getProduct(@PathVariable Integer id) {
        return productRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Create a product")
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Product create(
            @Valid @RequestBody ProductRequest request,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        String slug = request.getName().toLowerCase().replace(" ", "-");
        boolean productExists = productRepository.findBySlug(slug) != null;
        boolean categoryExists = categoryRepository.findById(request.getCategoryId()).isPresent();
        if (productExists || !categoryExists) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Product product = new Product();
        product.setName(request.getName());
        product.setSlug(slug);
        product.setDescription(request.getDescription());
        product.setImage(request.getImage());
        product.setPrice(request.getPrice());
        product.setCategoryId(request.getCategoryId().toString());
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());

        // DO NO SAVE (readonly)
        return product;
    }

    @Operation(summary = "Update a product")
    @PutMapping(path = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Product update(
            @PathVariable Integer id,
            @Valid @RequestBody ProductRequest request,
            BindingResult bindingResult
    ) {
        Product product = productRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        boolean categoryExists = categoryRepository.findById(request.getCategoryId()).isPresent();
        if (!categoryExists) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        String slug = request.getName().toLowerCase().replace(" ", "-");
        product.setName(request.getName());
        product.setSlug(slug);
        product.setDescription(request.getDescription());
        product.setImage(request.getImage());
        product.setPrice(request.getPrice());
        product.setCategoryId(request.getCategoryId().toString());
        product.setUpdatedAt(LocalDateTime.now());

        // DO NO SAVE (readonly)
        return product;
    }
}
