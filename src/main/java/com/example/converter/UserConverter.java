package com.example.converter;

import com.example.entity.User;
import com.example.model.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements Converter<User, UserDto> {
    @Override
    public User convertToEntity(UserDto model, User entity) {
        entity.setId(model.getId());
        entity.setName(model.getName());
        entity.setEmail(model.getEmail());
        entity.setPassword(model.getPassword());
        return entity;
    }

    @Override
    public UserDto convertToModel(User entity, UserDto model) {
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setEmail(entity.getEmail());
        model.setPassword(entity.getPassword());
        return model;
    }
}
