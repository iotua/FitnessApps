package fj.ac.usp.healthfitness;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;



/**
 * Created by Iotua' on 9/7/2015.
 */
public class MainFragment extends Fragment {

    private CallbackManager callbackManager;
    private TextView textView;

    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;

    private boolean isMainLobbyStarted = false;



    private FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {


        @Override
        public void onSuccess(LoginResult loginResult) {

           // Session session = Session.getActiveSession();

            Log.d("VIVZ", "onSuccess");
            AccessToken accessToken = loginResult.getAccessToken();
            Profile profile = Profile.getCurrentProfile();
            String username = profile.getName();

            //Intent i = new Intent(getActivity().getApplicationContext(), TabMainActivity.class);
            //Drawable d = getResources().getDrawable(profile.getProfilePictureUri(23,23));
            Drawable d = getResources().getDrawable(R.drawable.tab1);
            UserProfile userProfile = new UserProfile(username, 23, d);

           // Bundle b = new Bundle();
           // b.putParcelable("Profile", userProfile);
           // startActivity(new Intent(getActivity().getApplicationContext(), TabMainActivity.class).putExtras(b));

            Intent i = new Intent(getActivity().getApplicationContext(), TabMainActivity.class);
            if (!isMainLobbyStarted) {
                startActivity(i);
                isMainLobbyStarted = true;
                displayMessage(profile);
            }
        }

        @Override
        public void onCancel() {
            Log.d("VIVZ", "onCancel");
        }

        @Override
        public void onError(FacebookException e) {
            Log.d("VIVZ", "onError " + e);
        }
    };

    public MainFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        Log.d("VIVZ", "onCreate");
        callbackManager = CallbackManager.Factory.create();

        accessTokenTracker= new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldToken, AccessToken newToken) {

            }
        };

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile newProfile) {
                displayMessage(newProfile);
            }
        };



        accessTokenTracker.startTracking();
        profileTracker.startTracking();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("VIVZ", "onCreateView");
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LoginButton loginButton = (LoginButton) view.findViewById(R.id.login_button);
        textView = (TextView) view.findViewById(R.id.textView);
        Log.d("VIVZ", "onViewCreated");
        loginButton.setReadPermissions("user_friends");
        loginButton.setFragment(this);
        loginButton.registerCallback(callbackManager, callback);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("VIVZ", "onActivityResult");
        callbackManager.onActivityResult(requestCode, resultCode, data);



    }

    private void displayMessage(Profile profile){
        if(profile != null){
            textView.setText("Welcome "+profile.getName());


        }
    }

    @Override
    public void onStop() {
        super.onStop();
        accessTokenTracker.stopTracking();
        profileTracker.stopTracking();
    }

    @Override
    public void onResume() {
        super.onResume();
        Profile profile = Profile.getCurrentProfile();
        displayMessage(profile);
    }





}
