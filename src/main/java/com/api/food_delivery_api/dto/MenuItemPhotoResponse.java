package com.api.food_delivery_api.dto;

import com.api.food_delivery_api.entity.MenuItem;
import lombok.*;

@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemPhotoResponse {

    private Long id;
    private String fileType;
    private String fileFormat;
    private String fileSize;
    private String fileName;
    private String smallUrl;
    private String mediumUrl;
    private String largeUrl;
    private String uploadedBy;
    private String status;
}
