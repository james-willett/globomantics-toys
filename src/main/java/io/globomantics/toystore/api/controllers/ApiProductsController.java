package io.globomantics.toystore.api.controllers;

import io.globomantics.toystore.api.payloads.ProductRequest;
import io.globomantics.toystore.models.CategoryRepository;
import io.globomantics.toystore.models.ProductRepository;
import io.globomantics.toystore.models.data.Product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
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

import static io.globomantics.toystore.api.SwaggerConfig.SECURITY_SCHEME;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "products", description = "Products")
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
    public List<Product> listProducts(
            @RequestParam(value = "category", required = false)
            @Parameter(description = "Filter by category ID")
                    String categoryId
    ) {
        if (categoryId == null) {
            return productRepository.findAll();
        } else {
            return productRepository.findAllByCategoryId(categoryId, Pageable.unpaged());
        }
    }

    @Operation(summary = "Get a product")
    @ApiResponses(
            @ApiResponse(responseCode = "404", description = "Product not found")
    )
    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public Product getProduct(
            @PathVariable @Parameter(description = "Product ID") Integer id
    ) {
        return productRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Error: Product not found"));
    }

    @Operation(summary = "Create a product", security = @SecurityRequirement(name = SECURITY_SCHEME))
    @ApiResponses(
            @ApiResponse(responseCode = "400", description = "Invalid request content or duplicate of an existing product")
    )
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Product create(
            @Valid @RequestBody ProductRequest request,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error: Product JSON payload is invalid");
        }

        String slug = request.getName().toLowerCase().replace(" ", "-");
        boolean productExists = productRepository.findBySlug(slug) != null;
        if (productExists) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error: Product already exists");
        }

        boolean categoryExists = categoryRepository.findById(request.getCategoryId()).isPresent();
        if (!categoryExists) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error: Category does not exist");
        }

        Product product = new Product();
        product.setName(request.getName());
        product.setSlug(slug);
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setCategoryId(request.getCategoryId().toString());
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());

        productRepository.save(product);
        return product;
    }

    @Operation(summary = "Update a product", security = @SecurityRequirement(name = SECURITY_SCHEME))
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Invalid request content"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    @PutMapping(path = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Product update(
            @PathVariable @Parameter(description = "Product ID") Integer id,
            @Valid @RequestBody ProductRequest request,
            BindingResult bindingResult
    ) {
        Product product = productRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Error: Product not found"));

        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error: Product JSON payload is invalid");
        }

        boolean categoryExists = categoryRepository.findById(request.getCategoryId()).isPresent();
        if (!categoryExists) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error: Category does not exist");
        }

        String slug = request.getName().toLowerCase().replace(" ", "-");
        boolean productExists = productRepository.findBySlug(slug) != null;
        if (productExists) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error: Product already exists with this name");
        }

        product.setName(request.getName());
        product.setSlug(slug);
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setCategoryId(request.getCategoryId().toString());
        product.setUpdatedAt(LocalDateTime.now());

        productRepository.save(product);
        return product;
    }

    @Operation(summary = "Delete a product", security = @SecurityRequirement(name = SECURITY_SCHEME))
    @ApiResponses(
            @ApiResponse(responseCode = "404", description = "Product not found")
    )
    @DeleteMapping(path = "/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String deleteProduct(
            @PathVariable @Parameter(description = "Product ID") Integer id
    ) {
        Product product = productRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Error: Product not found"));

        productRepository.delete(product);
        return "Product: " + product.getName() + " deleted successfully";


    }
}
