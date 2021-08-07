package com.msr.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.msr.siteuse.SiteUse;
import org.springframework.data.repository.CrudRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

public class TableInitializer
{
    String path;
    CrudRepository repository;

    public TableInitializer(String path, CrudRepository repository)
    {
        this.path = path;
        this.repository = repository;
    }

    public void initRepository(Class<?> theClass)
    {
        BufferedReader reader;
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<?>> typeReference = new TypeReference<List<?>>(){};
        try
        {
            reader = new BufferedReader(new FileReader(this.path));
            List<?> siteUseList = mapper.readValue(reader, typeReference);
            siteUseList.forEach(repository::save);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
