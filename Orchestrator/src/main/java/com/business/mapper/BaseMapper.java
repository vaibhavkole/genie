package com.business.mapper;

/**
 * Created by vaibhav.janardhan on 25/02/17.
 */
public interface BaseMapper<Entity, Dto> {
    Dto convertToDto(Entity entityName);

    Entity convertToEntity(Dto dtoName);
}
