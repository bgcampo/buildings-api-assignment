package com.msr.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class UseType {
    @Id
    private int id;

    private String name;
}
