package com.hj.autogridviewdemo.widget;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 实体类
 */
public class ModelBean implements Parcelable {


    private String Name;
    private String finishTime;
    private int status;

    public ModelBean(String name, String finishTime, int status) {
        Name = name;
        this.finishTime = finishTime;
        this.status = status;
    }

    protected ModelBean(Parcel in) {
        Name = in.readString();
        finishTime = in.readString();
        status = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Name);
        dest.writeString(finishTime);
        dest.writeInt(status);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ModelBean> CREATOR = new Creator<ModelBean>() {
        @Override
        public ModelBean createFromParcel(Parcel in) {
            return new ModelBean(in);
        }

        @Override
        public ModelBean[] newArray(int size) {
            return new ModelBean[size];
        }
    };

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
