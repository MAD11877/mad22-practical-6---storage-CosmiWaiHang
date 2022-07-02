package me.cosmi.mad.practical.data.db;

public interface Response<T> {

    void onSuccess(T t);

    default void onFailure(final String message) {
    }
}
