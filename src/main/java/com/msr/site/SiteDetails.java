package com.msr.site;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.msr.usetype.UseType;
import lombok.Data;

@Data
public class SiteDetails
{
    @JsonUnwrapped
    private Site site;
    @JsonProperty(value = "total_size")
    private long totalSize;
    @JsonProperty(value = "primary_size")
    private UseType useType;
}
