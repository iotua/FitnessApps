package fj.ac.usp.healthfitness;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;

import com.facebook.login.LoginManager;

/**
 * Created by Iotua' on 9/8/2015.
 */
public class MainApplication extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_application);
    }


    @Override
    public void onBackPressed() {

        LoginManager.getInstance().logOut();
        super.onBackPressed();

    }
}
