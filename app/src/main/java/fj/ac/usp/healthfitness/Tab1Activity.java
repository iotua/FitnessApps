package fj.ac.usp.healthfitness;

/**
 * Created by Iotua' on 9/8/2015.
 */
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.Profile;
import com.facebook.login.widget.ProfilePictureView;

import java.io.InputStream;
import java.net.URL;

public class Tab1Activity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab1);



        Profile profile = Profile.getCurrentProfile();

        if (profile != null) {
            //display picture
            String userId = profile.getId();
            ProfilePictureView profilePictureView;
            profilePictureView = (ProfilePictureView) findViewById(R.id.faceImage);
            profilePictureView.setProfileId(userId);


            //display Welcome Message
            TextView displayName = (TextView) findViewById(R.id.displayProfile);
            displayName.setText("Welcome " + profile.getName());
        }
    }



}