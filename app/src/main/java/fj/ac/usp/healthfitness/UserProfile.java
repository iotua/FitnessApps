package fj.ac.usp.healthfitness;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Iotua' on 9/8/2015.
 */
public class UserProfile implements Parcelable {
    private String fullName;
    private Drawable profileImage;
    private int fitness;


    public UserProfile(Parcel source) {
        this.fullName = source.readString();
        this.fitness = source.readInt();
        this.profileImage = new BitmapDrawable(
                ( (Bitmap) source.readValue(Bitmap.class.getClassLoader()) )
        );
    }

    public UserProfile(String username, int fit, Drawable profilePicture) {
        this.fullName = username;
        this.fitness = fit;
        this.profileImage = profilePicture;
    }

    public static final Parcelable.Creator<UserProfile> CREATOR = new Parcelable.Creator<UserProfile>() {
        public UserProfile createFromParcel(Parcel source) {
            return new UserProfile(source);
        }

        public UserProfile[] newArray(int size) {
            return new UserProfile[size];
        }
    };

    /**
     * describe the kind of special object
     */
    @Override
    public int describeContents() {
        // hashCode() of this class
        return hashCode();
    }

    /**
     * write this object in to a Parcel
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(fullName);
        dest.writeInt(fitness);
        // hey, BitmapDrawable class implements Parcelable.
        // so we can write the object directly
        dest.writeValue(((BitmapDrawable)profileImage).getBitmap());
    }
}
