package com.api.food_delivery_api.dto;

import com.api.food_delivery_api.entity.MenuItem;
import com.api.food_delivery_api.entity.MenuItemPhoto;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class MenuItemPhotoRequest {

    private Long id;
    private String fileType;
    private String fileFormat;
    private String fileName;
    private String fileSize;
    private String smallUrl;
    private String mediumUrl;
    private String largeUrl;
    private String uploadedBy;
    private String status;

}
