package org.test.koltintest.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.format.DateUtils;

import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class User implements Parcelable {

    private long id;

    @NonNull
    private String name;

    @NonNull
    private String profession;

    @Nullable
    private Date birthDate;

    public User(long id, String name, String profession, Date birthDate) {
        this.id = id;
        this.name = name;
        this.profession = profession;
        this.birthDate = birthDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getAge() {
        return birthDate == null ? null :
                Math.toIntExact((System.currentTimeMillis() - birthDate.getTime()) / DateUtils.YEAR_IN_MILLIS);
    }

    // ************* parcel implementation *************

    protected User(Parcel in) {
        id = in.readLong();
        name = in.readString();
        profession = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeString(profession);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {

        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }

    };

}
