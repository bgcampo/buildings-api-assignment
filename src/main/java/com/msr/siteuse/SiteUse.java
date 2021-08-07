package com.msr.siteuse;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.msr.usetype.UseType;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Data
@Entity
public class SiteUse {
    @Id
    private int id;

    @JsonProperty(value = "site_id")
    private int siteId;

    private String description;

    @JsonProperty(value = "size_sqft")
    private long sizeSqft;

    @JsonProperty(value = "use_type_id")
    private int useTypeId;

    @Transient
    private UseType useType;
}
