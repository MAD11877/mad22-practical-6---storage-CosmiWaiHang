package me.cosmi.mad.practical.data.db.converter;

import static me.cosmi.mad.practical.data.db.UserContract.Entry.USER_DESCRIPTION;
import static me.cosmi.mad.practical.data.db.UserContract.Entry.USER_ID;
import static me.cosmi.mad.practical.data.db.UserContract.Entry.USER_IS_FOLLOWED;
import static me.cosmi.mad.practical.data.db.UserContract.Entry.USER_NAME;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;

import me.cosmi.mad.practical.data.domain.User;

public class UserConverter implements Converter<User> {

    @Override
    public ContentValues toContent(final User user) {
        final ContentValues content = new ContentValues();

        content.put(USER_ID, user.getId());
        content.put(USER_NAME, user.getName());
        content.put(USER_DESCRIPTION, user.getDescription());
        content.put(USER_IS_FOLLOWED, user.isFollowed());

        return content;
    }

    @Override
    @SuppressLint("Range")
    public User fromCursor(final Cursor cursor) {
        final int id = cursor.getInt(cursor.getColumnIndex(USER_ID));
        final String name = cursor.getString(cursor.getColumnIndex(USER_NAME));
        final String description = cursor.getString(cursor.getColumnIndex(USER_DESCRIPTION));
        final boolean isFollowed = 1 == cursor.getInt(cursor.getColumnIndex(USER_IS_FOLLOWED));

        return new User(name, description, id, isFollowed);
    }
}
