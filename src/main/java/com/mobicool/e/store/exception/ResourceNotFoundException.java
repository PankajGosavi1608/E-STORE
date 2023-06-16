package com.mobicool.e.store.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String categoryNotFoundException) {
      super(categoryNotFoundException);
    }
}
