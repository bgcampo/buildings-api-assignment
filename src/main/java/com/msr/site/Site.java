package com.msr.site;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.msr.siteuse.SiteUse;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
public class Site
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private String address;

    private String city;

    private String state;

    private String zipcode;

    @Transient
    @JsonIgnore
    private List<SiteUse> siteUses;
}
