package com.kh618.navigationdrawer;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
//import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NavigationDrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

        //Declare text view to use as badge
        TextView inboxCounter;

  /*
   * declare navigationView , the first take header and menu1
   * the second take menu2
   */
    NavigationView navigationView,navigationView2;

    //declare tool bar
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);

        //initialize toolbar
         toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");

        //initialize drawer layout
        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        //initialize actionbar drawer toggle
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //initialize navigation view1
         navigationView =  findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //initialize navigation view2
        navigationView2 =  findViewById(R.id.nav_view2);
        navigationView2.setNavigationItemSelectedListener(this);

        //called initialize badge method
        InitializeInboxMailCount();
        setMailNumber(1);

    }

    //initialize badge of inbox mail
    public void InitializeInboxMailCount(){
        //margin and dimens of badge
        LinearLayout.LayoutParams p = new LinearLayout.
                LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
       p.setMargins(0,50,0,0);

        //linear layout action view  to content badge
        LinearLayout layout = (LinearLayout) navigationView.getMenu().findItem(R.id.nav_inbox).getActionView();

        //initialize badge
        inboxCounter = new TextView(this);
        inboxCounter.setGravity(Gravity.CENTER);
        inboxCounter.setTypeface(null, Typeface.BOLD);
        inboxCounter.setBackground(getResources().getDrawable(R.drawable.circle_badge));
        inboxCounter.setTextColor(getResources().getColor(R.color.white));
        inboxCounter.setTextSize(8);
        inboxCounter.setLayoutParams(p);
        layout.addView(inboxCounter);
    }

    //set number of unread mails
    public void setMailNumber(int mailNumber){
        inboxCounter.setText(String.valueOf(mailNumber));
    }

    //on click method of back arrow header
    public void back(View v){
        onBackPressed();
    }

    //handle on back pressed event
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //check if navigation drawer is open close it
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    //handle on select item from navigation drawer
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_home:
                toolbar.setTitle("Home");
                break;
            case R.id.nav_myProfile:
                toolbar.setTitle("Profile");
                break;
            case R.id.nav_events:
                toolbar.setTitle("Events");
                break;
            case R.id.nav_favorites:
                toolbar.setTitle("Favorites");
                break;
            case R.id.nav_inbox:
                toolbar.setTitle("Inbox");
                break;
            case R.id.nav_contact_us:
                toolbar.setTitle("Contact Us");
                break;
            case R.id.nav_feedback:
                toolbar.setTitle("Feedback");
                break;
            case R.id.nav_settings:
                toolbar.setTitle("Settings");
                break;
        }

        toolbar.setTitleMarginStart(300);

        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
