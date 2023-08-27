package com.akerke.stackoverflow.exception;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(Class<?> c , Number id){
        super("%s with id: %s not found".formatted(c.getSimpleName(), id.toString()));
    }
}
