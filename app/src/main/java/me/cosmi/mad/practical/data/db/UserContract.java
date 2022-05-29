package me.cosmi.mad.practical.data.db;

import android.provider.BaseColumns;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import me.cosmi.mad.practical.data.domain.User;

public interface UserContract {

    /**
     * If the "ID" of the {@link User} if provided (greater than 0),
     * {@link UserContract#update} will be execute
     * {@link UserContract#insert} will be execute
     *
     * @param user     A model to insert or update
     * @param response A response upon completing the query
     */
    void save(final User user, final Response<Number> response);

    /**
     * Insert the user into the database.
     * <p>
     * If the operation success, the id of the user will be included in the {@link Response#onSuccess}
     * else a message will be included in the {@link Response#onFailure}
     *
     * @param user     A model to insert
     * @param response A response upon completing the query
     */
    void insert(final User user, final Response<Number> response);

    /**
     * Insert a list of user into the database.
     *
     * @param userList A list of model to insert
     * @param response A response upon completing the query
     */
    void insert(final ObjectArrayList<User> userList, final Response<ObjectArrayList<Number>> response);

    /**
     * Update the user
     * <p>
     * If the operation success, the total number of row will be included in the {@link Response#onSuccess}
     * else a message will be included in the {@link Response#onFailure}
     *
     * @param user     A model to be update
     * @param response A response upon completing the query
     */
    void update(final User user, final Response<Number> response);

    /**
     * Get all the user from the database
     *
     * @param response A response upon completing the query
     */
    void findAll(final Response<ObjectArrayList<User>> response);

    final class Entry implements BaseColumns {
        // Database Properties
        public static final String DATABASE_NAME = "user.db";
        public static final int DATABASE_VERSION = 1;

        // Table
        public static final String TABLE_USER = "user";

        // Column
        public static final String USER_NAME = "name";
        public static final String USER_DESCRIPTION = "description";
        public static final String USER_ID = "id";
        public static final String USER_IS_FOLLOWED = "is_followed";
    }
}
