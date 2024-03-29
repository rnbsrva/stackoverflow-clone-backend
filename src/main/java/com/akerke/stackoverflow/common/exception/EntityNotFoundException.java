package com.akerke.stackoverflow.common.exception;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(Class<?> c , Object id){
        super("%s with id: %s not found".formatted(c.getSimpleName(), id.toString()));
    }

    public EntityNotFoundException(Class<?> c, String details) {
        super("%s with param %s not found".formatted(c.getSimpleName(), details));
    }
}
