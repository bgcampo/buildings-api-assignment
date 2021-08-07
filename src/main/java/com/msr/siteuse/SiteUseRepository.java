package com.msr.siteuse;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SiteUseRepository extends PagingAndSortingRepository<SiteUse, Integer>
{
    List<SiteUse> findBySiteId(int siteId);
}