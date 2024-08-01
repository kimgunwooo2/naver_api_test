package com.fizz.naver_api_test.naver.controller;

import com.fizz.naver_api_test.naver.dto.ProductMypriceRequestDto;
import com.fizz.naver_api_test.naver.dto.ProductRequestDto;
import com.fizz.naver_api_test.naver.dto.ProductResponseDto;
import com.fizz.naver_api_test.naver.entity.UserDetailsImpl;
import com.fizz.naver_api_test.naver.service.ProductService;
import com.fizz.naver_api_test.naver.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/products")
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto requestDto,
                                            @AuthenticationPrincipal UserDetailsImpl user) {
        // 응답 보내기
        return productService.createProduct(requestDto, user.getUser());
    }

    @PutMapping("/products/{id}")
    public ProductResponseDto updateProduct(@PathVariable Long id,
                                            @RequestBody ProductMypriceRequestDto requestDto) {
        // 응답 보내기
        return productService.updateProduct(id, requestDto);
    }

    @GetMapping("/products")
    public Page<ProductResponseDto> getProducts(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sortBy") String sortBy,
            @RequestParam("isAsc") boolean isAsc,
            @AuthenticationPrincipal UserDetailsImpl user) {
        // 응답 보내기
        return productService.getProducts(user.getUser(),
                page-1, size, sortBy, isAsc);
    }

    @GetMapping("/admin/products")
    public List<ProductResponseDto> getAllProducts(@AuthenticationPrincipal UserDetailsImpl user) {
        // 응답 보내기
        return productService.getAllProducts();
    }

    @PostMapping("/products/{productId}/folder")
    public void addFolder(@PathVariable Long productId,
                          @RequestParam Long folderId,
                          @AuthenticationPrincipal UserDetailsImpl user) {
        productService.addFolder(productId, folderId, user.getUser());
    }
}
