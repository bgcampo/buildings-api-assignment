package com.msr.usetype;

import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class UseType {
    @Id
    private int id;

    private String name;

}
