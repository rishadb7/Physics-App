package com.mydomain.physics.physicsapps;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.multidex.MultiDex;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import java.util.ArrayList;
import android.support.v7.app.ActionBarDrawerToggle;

import com.mydomain.physics.physicsapps.fragments.HomeFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    Context context;
    DrawerLayout dLayout;
    RelativeLayout layout;
    FrameLayout frameLayout;
    Toolbar toolbar;
    Fragment fragment;
    FragmentManager fragmentManager;
    SQLiteDatabase db;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    ImageView cart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (isNetworkConnected()) {



            toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            toolbar.setTitle("Physics Apps");
            toolbar.setTitleTextColor(getResources().getColor(R.color.white));

            drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close);

            drawer.addDrawerListener(toggle);
            toggle.syncState();


                getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            layout=(RelativeLayout)findViewById(R.id.layout);
            frameLayout=(FrameLayout)findViewById(R.id.fragmentHolder);


            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);
            navigationView.getMenu().getItem(0).setChecked(true);

            Menu menu=navigationView.getMenu();
            MenuItem home=menu.findItem(R.id.nav_home);
            MenuItem index=menu.findItem(R.id.nav_index);
            MenuItem bookMark=menu.findItem(R.id.nav_bookmark);
            MenuItem aboutUs=menu.findItem(R.id.aboutUs);

            applyFontToMenuItem(home);
            applyFontToMenuItem(index);
            applyFontToMenuItem(bookMark);
            applyFontToMenuItem(aboutUs);

            SpannableString s1 = new SpannableString(home.getTitle());
            s1.setSpan(new ForegroundColorSpan(Color.WHITE), 0, s1.length(), 0);
            home.setTitle(s1);

            SpannableString s2 = new SpannableString(index.getTitle());
            s2.setSpan(new ForegroundColorSpan(Color.WHITE), 0, s2.length(), 0);
            index.setTitle(s2);

            SpannableString s3 = new SpannableString(bookMark.getTitle());
            s3.setSpan(new ForegroundColorSpan(Color.WHITE), 0, s3.length(), 0);
            bookMark.setTitle(s3);

            SpannableString s4 = new SpannableString(aboutUs.getTitle());
            s4.setSpan(new ForegroundColorSpan(Color.WHITE), 0, s4.length(), 0);
            aboutUs.setTitle(s4);


            fragmentManager = getSupportFragmentManager();

            fragment =new HomeFragment();
            final FragmentTransaction transaction0 = fragmentManager.beginTransaction();
            transaction0.replace(R.id.fragmentHolder, fragment).commit();

            ImageView facebook=(ImageView)findViewById(R.id.facebook1);
            ImageView instagram=(ImageView)findViewById(R.id.instagram1);
            ImageView shareApp=(ImageView)findViewById(R.id.shareApp1);
            ImageView backArrow=(ImageView)findViewById(R.id.backArrow);
            backArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawer.closeDrawers();

                }
            });
            shareApp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");
                    String shareBody = "https://play.google.com/store/apps/details?id=com.mydomain.physics.physicsapps";

                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                    startActivity(Intent.createChooser(sharingIntent, "Share via"));
                }
            });

            facebook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String url = "https://m.facebook.com/";

                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);

                }
            });

            instagram.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = "http://www.thepictaram.club/instagram/";

                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }
            });


            SharedPreferences pref = this.getPreferences(Context.MODE_PRIVATE);
            //  id = pref.getString("fbData", "");
          /*  name2=pref.getString("personName", "");
            email2=pref.getString("email_id", "");
            imageUrl2=pref.getString("ImageUrl", "");*/




        }
        else {

            toolbar = (Toolbar) findViewById(R.id.toolbar);
          //  setSupportActionBar(toolbar);
            toolbar.setTitle("Physics Apps");
            toolbar.setTitleTextColor(getResources().getColor(R.color.white));

            drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close);
            drawer.addDrawerListener(toggle);
            toggle.syncState();


            getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);



            new AlertDialog.Builder(this)
                    .setTitle("No Internet Connection")
                    .setMessage("It looks like your internet connection is off. Please turn it " +
                            "on and try again")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            if(isNetworkConnected())
                            {
                                startActivity(new Intent(MainActivity.this,MainActivity.class));
                            }
                            else
                            {
                                System.exit(0);
                            }


                        }
                    }).setIcon(android.R.drawable.ic_dialog_alert).show();

        }



    }

    private boolean isNetworkConnected() {
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE); // 1
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo(); // 2
        return networkInfo != null && networkInfo.isConnected(); // 3
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.actionbar_menu, menu);
        MenuItem item = menu.findItem(R.id.action_drawer_cart);
        MenuItemCompat.setActionView(item, R.layout.actionbar_badge_layout);

        final RelativeLayout notifCount = (RelativeLayout)   MenuItemCompat.getActionView(item);

        cart = (ImageView) notifCount.findViewById(R.id.imageView2);

        Drawable mIcon= ContextCompat.getDrawable(getApplicationContext(), R.drawable.baggg);
        mIcon.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.white), PorterDuff.Mode.MULTIPLY);
        cart.setImageDrawable(mIcon);



        cart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                /*fragment =new CartFragment();
                final FragmentTransaction transaction0 = fragmentManager.beginTransaction();
                transaction0.replace(R.id.fragmentHolder, fragment).commit();*/


            }
        });



        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(toggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;


        }
        return super.onOptionsItemSelected(item);
    }

    private void displaySelectedScreen(int itemId) {

        //creating fragment object
        // Fragment fragment = null;


        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragmentHolder, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        //calling the method displayselectedscreen and passing the id of selected menu
        displaySelectedScreen(item.getItemId());

        int id = item.getItemId();

        if (id == R.id.nav_home)
        {
            fragment =new HomeFragment();

            final FragmentTransaction transaction0 = fragmentManager.beginTransaction();

            transaction0.replace(R.id.fragmentHolder, fragment).commit();

        }

        else if(id==R.id.aboutUs)
        {
            /*Intent i =new Intent(MainActivity.this,HomeActivity.class);
            startActivity(i);
*/
           /* fragment =new AboutFragment();
            final FragmentTransaction transaction0 = fragmentManager.beginTransaction();
            transaction0.replace(R.id.fragmentHolder, fragment).commit();*/
        }


        else
        {
            // itemList.setVisibility(View.INVISIBLE);
            // displaySelectedScreen(R.id.nav_home);
            displaySelectedScreen(R.id.nav_home);
        }

        return true;
    }








    private void applyFontToMenuItem(MenuItem mi) {
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/NotoSerif-Regular.ttf");
        SpannableString mNewTitle = new SpannableString(mi.getTitle());
        mNewTitle.setSpan(new CustomTypefaceSpan("" , font), 0 , mNewTitle.length(),  Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mi.setTitle(mNewTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }





    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (fragmentManager.getBackStackEntryCount() > 0) {
            super.onBackPressed();

        } else {

            finish();
        }
    }



}
