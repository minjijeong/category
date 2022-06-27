package com.api.category.model.response;

public interface CommonJson<T> {
    Boolean isError();

    String getMessage();

    T getResult();
}