package com.pembo.store.config;

import org.mapstruct.factory.Mappers;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.pembo.store.mapper.ProductMapper;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

   @Bean
    public ProductMapper productMapper(){
         return Mappers.getMapper(ProductMapper.class);
    }
}
