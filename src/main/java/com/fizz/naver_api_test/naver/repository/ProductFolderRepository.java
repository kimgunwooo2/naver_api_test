package com.fizz.naver_api_test.naver.repository;

import com.fizz.naver_api_test.naver.entity.Folder;
import com.fizz.naver_api_test.naver.entity.Product;
import com.fizz.naver_api_test.naver.entity.ProductFolder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductFolderRepository extends JpaRepository<ProductFolder, Long> {
    Optional<ProductFolder> findByProductAndFolder(Product product, Folder folder);
}
