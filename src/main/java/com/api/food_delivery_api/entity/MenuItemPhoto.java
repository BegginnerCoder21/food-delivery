package com.api.food_delivery_api.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "menu_item_photo")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class MenuItemPhoto extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileType;
    private String fileFormat;
    private String fileSize;
    private String smallUrl;
    private String fileName;
    private String mediumUrl;
    private String largeUrl;
    private String uploadedBy;
    private String status;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    private MenuItem menuItem;
}
