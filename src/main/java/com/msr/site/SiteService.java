package com.msr.site;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class SiteService
{
    @Autowired
    private SiteRepository repository;

    public List<Site> getAllSites()
    {
        List<Site> sites = new ArrayList<>();
        repository.findAll().forEach(sites::add);
        return sites;
    }

    public void addSite(Site newSite)
    {
        repository.save(newSite);
    }

    public Site getSite(int siteId)
    {
        return repository.findById(siteId).get();
    }

    public List<Site> getSitesForZipcode(String zipcode)
    {
        return repository.findSiteByZipcode(zipcode);
    }

    public void deleteSite(int siteId)
    {
        repository.deleteById(siteId);
    }

    public void updateSite(Site site)
    {
        repository.save(site);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doInitialization()
    {
        System.out.println("=============== INITIALIZING SITE DATA");
        // Parse Site objects from JSON file and place into the database
        BufferedReader reader;
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Site>> typeReference = new TypeReference<List<Site>>(){};
        try
        {
            reader = new BufferedReader(new FileReader("src/main/resources/data/sites.json"));
            List<Site> siteList = mapper.readValue(reader, typeReference);
            siteList.forEach(this::addSite);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


}
