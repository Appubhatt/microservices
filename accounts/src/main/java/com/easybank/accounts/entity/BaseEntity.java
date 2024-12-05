package com.easybank.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


@MappedSuperclass
@Getter
@Setter
@ToString
public class BaseEntity {

    @Column(updatable = false)
    private Date createdAt;

    @Column(updatable = true)
    private String createdBy;

    @Column(insertable = false)
    private Date updatedAt;

    @Column(insertable = false)
    private String updatedBy;
}

