package com.fizz.naver_api_test.naver.repository;

import com.fizz.naver_api_test.naver.entity.Folder;
import com.fizz.naver_api_test.naver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FolderRepository extends JpaRepository<Folder, Long> {
    List<Folder> findAllByUserAndNameIn(User user, List<String> folders);

    List<Folder> findAllByUser(User user);

}
