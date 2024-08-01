package com.fizz.naver_api_test.naver.service;

import com.fizz.naver_api_test.naver.dto.FolderResponseDto;
import com.fizz.naver_api_test.naver.entity.Folder;
import com.fizz.naver_api_test.naver.entity.User;
import com.fizz.naver_api_test.naver.repository.FolderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FolderService {

    private final FolderRepository folderRepository;

    public void addFolders(List<String> folders, User user) {

        List<Folder> existFolderList = folderRepository.findAllByUserAndNameIn(user, folders);

        List<Folder> newFolderList = new ArrayList<>();

        for(String folderName : folders) {
            if (!isExistFolerName(folderName, existFolderList)) {
                Folder folder = new Folder(folderName, user);
                newFolderList.add(folder);
            } else {
                throw new IllegalArgumentException("폴더명이 중복되었습니다.");
            }
        }
        folderRepository.saveAll(newFolderList);
    }

    public List<FolderResponseDto> getFolders(User user) {
        List<Folder> folderList = folderRepository.findAllByUser(user);
        List<FolderResponseDto> responseDtoList = new ArrayList<>();

        for (Folder folder : folderList) {
            responseDtoList.add(new FolderResponseDto(folder));
        }
        return responseDtoList;
    }

    private boolean isExistFolerName(String folderName, List<Folder> existFolderList) {
        for (Folder folder : existFolderList) {
            if (folderName.equals(folder.getName())) {
                return true;
            }
        }
        return false;
    }

}
