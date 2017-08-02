package com.miymayster.coursetrail_recyclerview;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Olga on 02.08.2017.
 */

public class ItemInfo implements Parcelable {
    private int mImageResId;
    private String mHeader;
    private String mInfo;
    public static Parcelable.Creator<ItemInfo> CREATOR = new Creator<ItemInfo>() {
        @Override
        public ItemInfo createFromParcel(Parcel source) {
            return new ItemInfo(source);
        }

        @Override
        public ItemInfo[] newArray(int size) {
            return new ItemInfo[size];
        }
    };

    public ItemInfo(Parcel source){
        mImageResId = source.readInt();
        mHeader = source.readString();
        mInfo = source.readString();
    }

    public ItemInfo(int imageResId, String header, String info){
        mImageResId = imageResId;
        mHeader = header;
        mInfo = info;
    }

    public int getImageResId(){
        return mImageResId;
    }
    public String getHeader(){
        return mHeader;
    }
    public String getInfo(){
        return mInfo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mImageResId);
        dest.writeString(mHeader);
        dest.writeString(mInfo);
    }
}
