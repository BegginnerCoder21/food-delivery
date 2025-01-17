package com.api.food_delivery_api.service.impl;

import com.api.food_delivery_api.constante.Constant;
import com.api.food_delivery_api.dto.MenuItemPhotoRequest;
import com.api.food_delivery_api.dto.MenuItemPhotoResponse;
import com.api.food_delivery_api.dto.MenuItemRequest;
import com.api.food_delivery_api.entity.MenuItem;
import com.api.food_delivery_api.entity.MenuItemPhoto;
import com.api.food_delivery_api.repository.MenuItemPhotoRepository;
import com.api.food_delivery_api.service.MenuItemPhotoService;
import com.api.food_delivery_api.service.handler.menuitemphoto.MenuItemPhotoHandler;
import jakarta.servlet.ServletContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class MenuItemPhotoServiceImpl implements MenuItemPhotoService {


    @Autowired
    private MenuItemPhotoHandler menuItemPhotoHandler;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private MenuItemPhotoRepository menuItemPhotoRepository;
    @Autowired
    private ServletContext context;

    @Override
    public List<MenuItemPhotoResponse> upload(MultipartFile[] files, MenuItemPhotoRequest menuItemPhotoRequest) throws IOException {

        String filePath = System.getProperty("user.dir") + "/images/";
        log.info("url fileName: {}", filePath);

        this.menuItemPhotoHandler.validateFileUpload(files);
        this.menuItemPhotoHandler.validateValidFileUpload(files);

       List<MenuItemPhotoResponse> menuItemPhotoResponses = new ArrayList<>();

       try {

       } catch (RuntimeException e) {
           log.warn("Erreur de telechargement des images");
       }
        for (MultipartFile file: files)
        {

            String fileName = file.getOriginalFilename();
            String name = FilenameUtils.removeExtension(fileName);
            String extensionName = FilenameUtils.getExtension(fileName);
            log.info("fileName: {}", fileName);

            File imageFile = new File(filePath + fileName);
            file.transferTo(imageFile);

            menuItemPhotoRequest.setFileFormat(extensionName);
            menuItemPhotoRequest.setFileSize(String.valueOf(file.getSize()));
            menuItemPhotoRequest.setFileName(name);
            menuItemPhotoRequest.setUploadedBy(Constant.SYSTEM);
            menuItemPhotoRequest.setLargeUrl(filePath);
            menuItemPhotoRequest.setSmallUrl(filePath);
            menuItemPhotoRequest.setMediumUrl(filePath);

            MenuItemPhoto menuItemPhoto = this.modelMapper.map(menuItemPhotoRequest, MenuItemPhoto.class);
            menuItemPhoto.setMenuItem(null);

            this.menuItemPhotoRepository.save(menuItemPhoto);
            log.info("Enregistrement de la photo {}", name);

            MenuItemPhotoResponse menuItemPhotoResponse = this.modelMapper.map(menuItemPhoto, MenuItemPhotoResponse.class);

            menuItemPhotoResponses.add(menuItemPhotoResponse);
        }

        log.info("reception de menu item photo {}", menuItemPhotoRequest);

        
        return menuItemPhotoResponses;
    }

    @Override
    public MenuItemPhotoResponse update(Long id, MenuItemPhotoRequest menuItemPhotoRequest)
    {

        boolean menuItemPhotoExist = this.menuItemPhotoRepository.existsById(id);

        if(!menuItemPhotoExist)
        {
            log.info("Aucune photo de menu n'a été trouvé !");
            return MenuItemPhotoResponse.builder().build();
        }

        MenuItemPhoto menuItemPhoto = this.menuItemPhotoRepository.findById(id).get();

        menuItemPhoto.setFileFormat(menuItemPhotoRequest.getFileFormat());
        menuItemPhoto.setFileSize(menuItemPhoto.getFileSize());
        menuItemPhoto.setFileName(menuItemPhoto.getFileName());
        menuItemPhoto.setStatus(menuItemPhoto.getStatus());
        menuItemPhoto.setLargeUrl(menuItemPhoto.getLargeUrl());
        menuItemPhoto.setFileType(menuItemPhotoRequest.getFileType());
        menuItemPhoto.setMediumUrl(menuItemPhotoRequest.getMediumUrl());
        menuItemPhoto.setSmallUrl(menuItemPhotoRequest.getSmallUrl());
        menuItemPhoto.setUploadedBy(menuItemPhotoRequest.getUploadedBy());

        this.menuItemPhotoRepository.save(menuItemPhoto);

        return this.modelMapper.map(menuItemPhoto, MenuItemPhotoResponse.class);
    }

    @Override
    public MenuItemPhotoResponse getById(Long id) {

        boolean menuItemPhotoExist = this.menuItemPhotoRepository.existsById(id);

        if(!menuItemPhotoExist)
        {
            log.info("Aucun photo de menu n'a été trouvé !");
            return MenuItemPhotoResponse.builder().build();
        }

        MenuItemPhoto menuItemPhoto = this.menuItemPhotoRepository.findById(id).get();

        return this.modelMapper.map(menuItemPhoto, MenuItemPhotoResponse.class);
    }

    @Override
    public List<MenuItemPhotoResponse> getAll() {

        List<MenuItemPhoto> menuItemPhotos = this.menuItemPhotoRepository.findAll();

        if(menuItemPhotos.isEmpty())
        {
            return List.of();
        }

        return menuItemPhotos.stream().map(menuItemPhoto -> this.modelMapper.map(menuItemPhoto, MenuItemPhotoResponse.class)).toList();

    }

    @Override
    public void delete(Long id) {

        boolean menuItemPhotoExist = this.menuItemPhotoRepository.existsById(id);

        if (!menuItemPhotoExist)
        {
            log.info("Aucun photo de menu pour ce identifiant {}", id);
            return;
        }

        this.menuItemPhotoRepository.deleteById(id);

    }


}
