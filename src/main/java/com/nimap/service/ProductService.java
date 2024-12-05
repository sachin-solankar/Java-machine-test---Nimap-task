package com.nimap.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nimap.entity.Product;
import com.nimap.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public Page<Product> getAllCategories(int page) {
		return productRepository.findAll(PageRequest.of(page - 1, 10));
	}

	public Product createProduct(Product Product) {
		return productRepository.save(Product);
	}

	public ResponseEntity<Product> getProductById(Long id) {
		Optional<Product> Product = productRepository.findById(id);

		return Product.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

	}

	public ResponseEntity<Product> updateProduct(Long id, Product Product) {
		return productRepository.findById(id).map(existingProduct -> {
			Product.setId(id);
			return ResponseEntity.ok(productRepository.save(Product));
		}).orElse(ResponseEntity.notFound().build());
	}

	public ResponseEntity<Product> deleteProduct(Long id) {
		productRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
