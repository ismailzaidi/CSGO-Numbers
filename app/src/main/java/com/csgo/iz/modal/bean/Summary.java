package com.csgo.iz.modal.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Summary implements Parcelable {

    public static final Creator<Summary> CREATOR = new Creator<Summary>() {
        @Override
        public Summary createFromParcel(Parcel in) {
            return new Summary(in);
        }

        @Override
        public Summary[] newArray(int size) {
            return new Summary[size];
        }
    };
    public final Profile userProfile;
    public final GeneralStats generalStats;
    public final LastMatchStats lastMatchStats;
    public final OtherStats otherStats;

    public Summary(Profile userProfile, GeneralStats generalStats, LastMatchStats lastMatchStats,
                   OtherStats otherStats) {
        this.userProfile = userProfile;
        this.generalStats = generalStats;
        this.lastMatchStats = lastMatchStats;
        this.otherStats = otherStats;
    }

    public Summary(GeneralStats generalStats, LastMatchStats lastMatchStats,
                   OtherStats otherStats) {
        this.userProfile = null;
        this.generalStats = generalStats;
        this.lastMatchStats = lastMatchStats;
        this.otherStats = otherStats;
    }

    protected Summary(Parcel in) {
        userProfile = in.readParcelable(Profile.class.getClassLoader());
        generalStats = in.readParcelable(GeneralStats.class.getClassLoader());
        lastMatchStats = in.readParcelable(LastMatchStats.class.getClassLoader());
        otherStats = in.readParcelable(OtherStats.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(userProfile, flags);
        dest.writeParcelable(generalStats, flags);
        dest.writeParcelable(lastMatchStats, flags);
        dest.writeParcelable(otherStats, flags);
    }
}
