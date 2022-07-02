package me.cosmi.mad.practical.data.db;

import static me.cosmi.mad.practical.data.db.UserContract.Entry.DATABASE_NAME;
import static me.cosmi.mad.practical.data.db.UserContract.Entry.DATABASE_VERSION;
import static me.cosmi.mad.practical.data.db.UserContract.Entry.TABLE_USER;
import static me.cosmi.mad.practical.data.db.UserContract.Entry.USER_DESCRIPTION;
import static me.cosmi.mad.practical.data.db.UserContract.Entry.USER_ID;
import static me.cosmi.mad.practical.data.db.UserContract.Entry.USER_IS_FOLLOWED;
import static me.cosmi.mad.practical.data.db.UserContract.Entry.USER_NAME;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import me.cosmi.mad.practical.data.db.converter.UserConverter;
import me.cosmi.mad.practical.data.domain.User;

// TODO: Do consider use a Single::GetInstance if possible
public class UserContractImpl extends SQLiteOpenHelper implements UserContract {

    private final UserConverter converter = new UserConverter();

    public UserContractImpl(final Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(final SQLiteDatabase database) {
        final String CREATE_USER_TABLE =
                " CREATE TABLE " + TABLE_USER + " ( "
                        + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + USER_NAME + " TEXT, "
                        + USER_DESCRIPTION + " TEXT, "
                        + USER_IS_FOLLOWED + " INTEGER "
                        + " ) ";

        database.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(final SQLiteDatabase database, final int oldVersion, final int newVersion) {
        final String DROP_USER_TABLE = "DROP TABLE IF EXIST " + TABLE_USER;

        database.execSQL(DROP_USER_TABLE);
        this.onCreate(database);
    }

    @Override
    public void save(final User user, final Response<Number> response) {
        final int id = user.getId();

        if (id > 0)
            this.update(user, response);
        else this.insert(user, response);
    }

    @Override
    public void insert(final User user, final Response<Number> response) {
        final SQLiteDatabase database = this.getWritableDatabase();
        final ContentValues content = this.converter.toContent(user);

        final long id = database.insert(TABLE_USER, null, content);

        if (id > 0) {
            response.onSuccess(id);
        } else response.onFailure("Query execution failed when inserting a new User");

        database.close();
    }

    @Override
    public void insert(final ObjectArrayList<User> userList, final Response<ObjectArrayList<Number>> response) {
        final ObjectArrayList<Number> idList = new ObjectArrayList<>();
        final SQLiteDatabase database = this.getWritableDatabase();

        database.beginTransaction();

        for (final User user : userList) {
            final ContentValues content = this.converter.toContent(user);
            final long id = database.insert(TABLE_USER, null, content);

            idList.add(id);
        }

        response.onSuccess(idList);
        database.setTransactionSuccessful();
        database.endTransaction();
    }

    @Override
    public void update(final User user, final Response<Number> response) {
        final SQLiteDatabase database = this.getWritableDatabase();
        final ContentValues content = this.converter.toContent(user);

        final String clause = USER_ID + " = ? ";
        final String[] argArr = new String[]{String.valueOf(user.getId())};

        final int rowAffected = database.update(TABLE_USER, content, clause, argArr);

        if (rowAffected > 0)
            response.onSuccess(rowAffected);
        else response.onFailure("There are no user to be update");

        database.close();
    }

    @Override
    @SuppressLint("Range")
    public void findAll(final Response<ObjectArrayList<User>> response) {
        final SQLiteDatabase database = this.getReadableDatabase();
        final ObjectArrayList<User> userList = new ObjectArrayList<>();
        final Cursor cursor = database.query(TABLE_USER, null, null, null, null, null, null);

        if (null != cursor) {
            cursor.moveToFirst();

            while (cursor.moveToNext()) {
                userList.add(this.converter.fromCursor(cursor));
            }

            response.onSuccess(userList);
        } else response.onFailure("There are no user in the database");

        database.close();
        if (null != cursor) cursor.close();
    }
}
