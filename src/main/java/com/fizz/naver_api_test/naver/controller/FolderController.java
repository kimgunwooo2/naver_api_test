package com.fizz.naver_api_test.naver.controller;

import com.fizz.naver_api_test.naver.dto.FolderRequestDto;
import com.fizz.naver_api_test.naver.dto.FolderResponseDto;
import com.fizz.naver_api_test.naver.dto.ProductResponseDto;
import com.fizz.naver_api_test.naver.entity.UserDetailsImpl;
import com.fizz.naver_api_test.naver.service.FolderService;
import com.fizz.naver_api_test.naver.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class FolderController {

    private final FolderService folderService;
    private final ProductService productService;

    @PostMapping("/folders")
    public void addFolders(@RequestBody FolderRequestDto requestDto,
                           @AuthenticationPrincipal UserDetailsImpl user) {
        List<String> folders = requestDto.getFolderNames();
        // 응답 보내기
        folderService.addFolders(folders, user.getUser());
    }

    @GetMapping("/folders")
    public List<FolderResponseDto> getFolders(@AuthenticationPrincipal UserDetailsImpl user) {
        return folderService.getFolders(user.getUser());
    }

    // 회원이 등록한 폴더 내 모든 상품 조회
    @GetMapping("/folders/{folderId}/products")
    public Page<ProductResponseDto> getProductsInFolder(
            @PathVariable Long folderId,
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy,
            @RequestParam boolean isAsc,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        return productService.getProductsInFolder(
                folderId,
                page-1,
                size,
                sortBy,
                isAsc,
                userDetails.getUser()
        );
    }
}
