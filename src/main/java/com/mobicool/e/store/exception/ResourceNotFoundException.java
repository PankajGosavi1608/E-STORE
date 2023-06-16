package com.mobicool.e.store.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String resourceName;
    private String fieldName;
    private String fieldvalue;

    public ResourceNotFoundException(String resourceName, String fieldName, String fieldvalue) {
        super(String.format("%s not found with %s :%s ", resourceName, fieldName, fieldvalue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldvalue = fieldvalue;

    }

    /*public ResourceNotFoundException(String categoryId) {
    }*/
}
