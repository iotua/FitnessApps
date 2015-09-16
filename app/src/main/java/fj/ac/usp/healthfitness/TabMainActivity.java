package fj.ac.usp.healthfitness;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

import com.facebook.login.LoginManager;

public class TabMainActivity extends TabActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_main);

        Resources ressources = getResources();
        TabHost tabHost = getTabHost();

        // Android tab
        Intent intentAndroid = new Intent().setClass(this, Tab1Activity.class);
        TabHost.TabSpec tabSpecAndroid = tabHost
                .newTabSpec("Android")
                .setIndicator("", ressources.getDrawable(R.drawable.icon_tab1))
                .setContent(intentAndroid);

        // Apple tab
        Intent intentApple = new Intent().setClass(this, Tab2Activity.class);
        TabHost.TabSpec tabSpecApple = tabHost
                .newTabSpec("Apple")
                .setIndicator("", ressources.getDrawable(R.drawable.icon_tab2))
                .setContent(intentApple);

        // Windows tab
        Intent intentWindows = new Intent().setClass(this, Tab3Activity.class);
        TabHost.TabSpec tabSpecWindows = tabHost
                .newTabSpec("Windows")
                .setIndicator("", ressources.getDrawable(R.drawable.icon_tab3))
                .setContent(intentWindows);

        // Blackberry tab
      //  Intent intentBerry = new Intent().setClass(this, BlackBerryActivity.class);
       // TabHost.TabSpec tabSpecBerry = tabHost
         //       .newTabSpec("Berry")
           //     .setIndicator("", ressources.getDrawable(R.drawable.icon_blackberry_config))
          //      .setContent(intentBerry);

        // add all tabs
        tabHost.addTab(tabSpecAndroid);
        tabHost.addTab(tabSpecApple);
        tabHost.addTab(tabSpecWindows);
       // tabHost.addTab(tabSpecBerry);

        //set Windows tab as default (zero based)
        tabHost.setCurrentTab(0);

        receiveInfo();
    }

    public void receiveInfo(){

        /*Intent i = getIntent();
        UserProfile myParcelableObject = (UserProfile) i.getParcelableExtra("object");
        TextView display = (TextView) findViewById(R.id.textView3);
        String firstname = myParcelableObject.getFirstName();
        display.setText(firstname);*/

        //UserProfile object = getIntent().getSerializableExtra("object");
        //Model modelObject = getIntent().getSerializableExtra("ModelObject");

        Bundle b = getIntent().getExtras();
        if(b != null) {
            UserProfile profile = b.getParcelable("Profile");

           //profile.writeToParcel();
            // now you have user profile object, and do what you want with this object...
        }
    }

    @Override
    public void onBackPressed() {

        //overwrite the default Backpress
        LoginManager.getInstance().logOut();
        super.onBackPressed();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

    }

}