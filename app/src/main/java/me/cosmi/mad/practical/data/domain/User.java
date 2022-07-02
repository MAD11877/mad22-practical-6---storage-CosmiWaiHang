package me.cosmi.mad.practical.data.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(final Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(final int size) {
            return new User[size];
        }
    };

    private String name;
    private String description;
    private int id;
    private boolean isFollowed;

    public User() {
        this.name = "";
        this.description = "";
        this.id = 0;
        this.isFollowed = false;
    }

    public User(
            final String name,
            final String description,
            final int id,
            final boolean isFollowed) {
        this.name = name;
        this.description = description;
        this.id = id;
        this.isFollowed = isFollowed;
    }

    protected User(final Parcel in) {
        this.name = in.readString();
        this.description = in.readString();
        this.id = in.readInt();
        this.isFollowed = in.readByte() != 0;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public int getId() {
        return this.id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public boolean isFollowed() {
        return this.isFollowed;
    }

    public void setIsFollowed(final boolean isFollowed) {
        this.isFollowed = isFollowed;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel parcel, final int i) {
        parcel.writeString(this.name);
        parcel.writeString(this.description);
        parcel.writeInt(this.id);
        parcel.writeByte((byte) (this.isFollowed ? 1 : 0));
    }
}
