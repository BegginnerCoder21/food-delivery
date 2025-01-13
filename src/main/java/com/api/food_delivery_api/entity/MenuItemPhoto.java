package com.api.food_delivery_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "menu_item_photo")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemPhoto extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileType;
    private String fileFormat;
    private String fileSize;
    private String smallUrl;
    private String mediumUrl;
    private String largeUrl;
    private String uploadedBy;
    private String status;
    @ManyToOne(fetch = FetchType.LAZY)
    private MenuItem menuItem;
}
