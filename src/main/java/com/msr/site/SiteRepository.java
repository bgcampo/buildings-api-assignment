package com.msr.site;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SiteRepository extends PagingAndSortingRepository<Site, Integer>
{
    List<Site> findSiteByZipcode(String zipcode);
}