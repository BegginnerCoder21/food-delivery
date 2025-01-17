package com.api.food_delivery_api.service.handler.menuitemphoto;

import com.api.food_delivery_api.dto.MenuItemPhotoRequest;
import com.api.food_delivery_api.entity.MenuItem;
import com.api.food_delivery_api.entity.MenuItemPhoto;
import com.api.food_delivery_api.repository.MenuItemPhotoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.api.food_delivery_api.constante.Constant.FILE_EXTENSION;
import static com.api.food_delivery_api.constante.Constant.SUCCESS;

@Slf4j
@RequiredArgsConstructor
public class MenuItemPhotoHandler {

    @Autowired
    private MenuItemPhotoRepository menuItemPhotoRepository;


    public void validateFileUpload(MultipartFile[] files)
    {
        if(files.length == 0)
        {
            log.info("Aucun fichier n'été selectionné !");
            throw new IllegalArgumentException("Veuillez selectionner au moins un fichier !");
        }

    }

    public void validateValidFileUpload(MultipartFile[] files)
    {
        for (MultipartFile file: files)
        {
            String fileName = file.getOriginalFilename();
            String extension = FilenameUtils.getExtension(fileName);

            if(!FILE_EXTENSION.contains(extension))
            {
                log.warn("Les fichiers de type {} ne sont pas accepté !", extension);
                throw new IllegalArgumentException("Type de fichier incorrect");
            }

        }
    }

    public void uploadFileByMenuItem(MenuItem menuItem, List<MenuItemPhotoRequest> menuItemPhotoRequests)
    {
        Set<Long> menuItemPhotoIds = menuItemPhotoRequests.stream().map(MenuItemPhotoRequest::getId).collect(Collectors.toSet());

        List< MenuItemPhoto> menuItemPhotos = this.menuItemPhotoRepository.findAllByIdIn(menuItemPhotoIds);

        for (MenuItemPhoto menuItemPhoto: menuItemPhotos)
        {
            menuItemPhoto.setMenuItem(menuItem);
            this.menuItemPhotoRepository.save(menuItemPhoto);
        }

    }
}
