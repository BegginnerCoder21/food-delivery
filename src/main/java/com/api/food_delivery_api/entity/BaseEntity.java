package com.api.food_delivery_api.entity;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@ToString
public class BaseEntity {

    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "created_at", insertable = true, updatable = false)
    private Date createdAt;
    @Column(name = "updated_by")
    private String updatedBy;
    @Column(name = "updated_at", insertable = false, updatable = true)
    private Date updatedAt;
}
