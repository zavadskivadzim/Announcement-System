package com.zavadski.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Data
@Table(name = "category")
public class Category {

    @Id
    @Column(name = "id")
    private UUID id = UUID.randomUUID();
}
