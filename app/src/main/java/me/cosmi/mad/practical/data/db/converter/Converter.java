package me.cosmi.mad.practical.data.db.converter;

import android.content.ContentValues;
import android.database.Cursor;

public interface Converter<T> {

    ContentValues toContent(T t);

    T fromCursor(final Cursor cursor);
}
