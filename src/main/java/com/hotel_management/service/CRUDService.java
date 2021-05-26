package com.hotel_management.service;

public interface CRUDService<T, I> {

    default T getById(I id) {
        throw new UnsupportedOperationException("get by id is not supported yet");
    }

    default T save(T t) {
        throw new UnsupportedOperationException("save is not supported yet");
    }

    default T update(T t) {
        throw new UnsupportedOperationException("update is not supported yet");
    }

    default void delete(I id) {
        throw new UnsupportedOperationException("delete is not supported yet");
    }
}
