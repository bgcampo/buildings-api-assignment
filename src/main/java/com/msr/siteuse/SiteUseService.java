package com.msr.siteuse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.msr.site.Site;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

@Service
public class SiteUseService
{
    @Autowired
    SiteUseRepository repository;

    @EventListener(ApplicationReadyEvent.class)
    public void doInitialization()
    {
        System.out.println("=============== INITIALIZING SITE USE DATA");
        // Parse SiteUse objects from JSON file and place into the database
        BufferedReader reader;
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<SiteUse>> typeReference = new TypeReference<List<SiteUse>>(){};
        try
        {
            reader = new BufferedReader(new FileReader("src/main/resources/data/site_uses.json"));
            List<SiteUse> siteUseList = mapper.readValue(reader, typeReference);
            siteUseList.forEach(repository::save);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public List<SiteUse> getSiteUses(int siteId)
    {
        return repository.findBySiteId(siteId);
    }
}
