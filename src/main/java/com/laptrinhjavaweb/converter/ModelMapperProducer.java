package com.laptrinhjavaweb.converter;

import org.modelmapper.ModelMapper;

import javax.enterprise.inject.Produces;

public class ModelMapperProducer {
    @Produces
    public ModelMapper createModelMapper() {
        return new ModelMapper();
    }
}
