package net.sparkeek.instatest;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import net.sparkeek.instatest.Utils.ToolBox;
import net.sparkeek.instatest.fragment.HomeFragment;
import net.sparkeek.instatest.fragment.ProfileFragment;
import net.sparkeek.instatest.interfaces.IFragmentToActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity implements IFragmentToActivity {
    FragmentManager fragmentManager;
    private Unbinder unBinder;
    SharedPreferences mPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unBinder = ButterKnife.bind(this);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/HouschkaHead-Medium.otf")
                .setFontAttrId(R.attr.fontPath)
                .build());

        mPref = ToolBox.getSharedPref(getApplicationContext());

        if (mPref.getString("insta_key", "error").length() > 20) {
            setFragment(2);
        } else {
            setFragment(1);
        }

    }


    @Override
    protected void onStop() {
        super.onStop();
        unBinder.unbind();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void setFragment(int id) {
        //TODO: Implement the reset method
        Fragment fragment = null;
        switch (id) {
            case 1:
                //Home
                fragment = new HomeFragment();
                break;
            case 2:
                //Progile
                fragment = new ProfileFragment();

                break;
        }

        fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() == 0) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
        } else {
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack("backMenu").commit();
        }
    }
}
