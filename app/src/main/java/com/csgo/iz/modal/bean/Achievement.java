package com.csgo.iz.modal.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Achievement implements Parcelable, Comparable<Achievement> {
	public final String achievementResID;
	public final String achievementName;
	public final String achievementDescription;
	public final int lockInfo;
	public final boolean isLocked;

	public Achievement(String achievementResID, String achievementName, String achievementDescription, int lockInfo) {
		this.achievementResID = achievementResID;
		this.achievementName = achievementName;
		this.achievementDescription = achievementDescription;
		this.lockInfo = lockInfo;
		isLocked = lockInfo == 0;
	}

	protected Achievement(Parcel in) {
		achievementResID = in.readString();
		achievementName = in.readString();
		achievementDescription = in.readString();
		lockInfo = in.readInt();
		isLocked = lockInfo == 0;
	}

	public static final Creator<Achievement> CREATOR = new Creator<Achievement>() {
		@Override
		public Achievement createFromParcel(Parcel in) {
			return new Achievement(in);
		}

		@Override
		public Achievement[] newArray(int size) {
			return new Achievement[size];
		}
	};

	@Override
	public int compareTo(Achievement another) {
		Integer lock1 = this.lockInfo;
		Integer lock2 = another.lockInfo;
		return lock2.compareTo(lock1);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(achievementResID);
		dest.writeString(achievementName);
		dest.writeString(achievementDescription);
		dest.writeInt(lockInfo);
	}
}
