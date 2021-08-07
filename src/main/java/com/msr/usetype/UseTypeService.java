package com.msr.usetype;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

@Service
public class UseTypeService
{
    @Autowired
    UseTypeRepository repository;

    @EventListener(ApplicationReadyEvent.class)
    public void doInitialization()
    {
        System.out.println("=============== INITIALIZING USE TYPE DATA");
        // Parse UseType objects from JSON file and place into the database
        BufferedReader reader;
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<UseType>> typeReference = new TypeReference<List<UseType>>(){};
        try
        {
            reader = new BufferedReader(new FileReader("src/main/resources/data/use_types.json"));
            List<UseType> useTypeList = mapper.readValue(reader, typeReference);
            useTypeList.forEach(repository::save);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public UseType getUseType(int useTypeId)
    {
        return repository.findById(useTypeId).get();
    }

}
