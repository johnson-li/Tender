package com.johnson.tender;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.johnson.tender.databinding.ActivityMainBinding;
import com.johnson.tender.entity.User;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {

  @Inject
  Preferences preferences;
  ActivityMainBinding binding;
  List<String> permissions = new ArrayList<>();
  private SearchEngine searchEngine;
  private Menu menu;

  {
    permissions.add(Manifest.permission.INTERNET);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    ((App) getApplication()).getNetworkComponent().inject(this);
    super.onCreate(savedInstanceState);
    binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();

    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);
    navigationView.getMenu().getItem(0).setChecked(true);
    onNavigationItemSelected(navigationView.getMenu().getItem(0));

    User user = preferences.getUser();
    if (user != null) {
      ((TextView) navigationView.getHeaderView(0).findViewById(R.id.user_name)).setText(user.getName());
      ((TextView) navigationView.getHeaderView(0).findViewById(R.id.user_info)).setText(user.getEmail());
      ((ImageView) navigationView.getHeaderView(0).findViewById(R.id.user_icon)).setImageResource(R.drawable.psyduck);
    } else {
      navigationView.getHeaderView(0).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }
      });
    }

    requestPermissions();
  }

  private void requestPermission(String permission, int callbackId) {
    int permissionCheck = ContextCompat.checkSelfPermission(getApplicationContext(), permission);
    if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
      ActivityCompat.requestPermissions(this, new String[]{permission}, callbackId);
    }
  }

  private void requestPermissions() {
    for (int i = 0; i < permissions.size(); i++) {
      requestPermission(permissions.get(i), i);
    }
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    for (int res : grantResults) {
      if (res != PackageManager.PERMISSION_DENIED) {
        Toast.makeText(this, "You can not continue without permissions!", Toast.LENGTH_SHORT).show();
        finish();
      }
    }
  }

  @Override
  public void onBackPressed() {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @Override
  public boolean onPrepareOptionsMenu(Menu menu) {
    return super.onPrepareOptionsMenu(menu);
  }

  public void setSearchEngine(SearchEngine searchEngine) {
    this.searchEngine = searchEngine;
  }

  public void collapseSearch() {
    MenuItem menuItem = menu.getItem(0);
    if (menuItem != null) {
      menuItem.collapseActionView();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      @Override
      public boolean onQueryTextSubmit(String query) {
        if (searchEngine != null) {
          searchEngine.doSearch(query);
        }
        return false;
      }

      @Override
      public boolean onQueryTextChange(String newText) {
        return false;
      }
    });
    this.menu = menu;
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_search) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    // Handle navigation view item clicks here.
    int id = item.getItemId();

    if (id == R.id.nav_main) {
      getSupportFragmentManager().beginTransaction()
          .replace(R.id.placeHolder, new MainFragment()).commit();
    } else if (id == R.id.nav_company) {
      getSupportFragmentManager().beginTransaction()
          .replace(R.id.placeHolder, new CompanyFragment()).commit();
    } else if (id == R.id.nav_staff) {
      getSupportFragmentManager().beginTransaction()
          .replace(R.id.placeHolder, new StaffFragment()).commit();
    } else if (id == R.id.nav_projects) {
      getSupportFragmentManager().beginTransaction()
          .replace(R.id.placeHolder, new ProjectFragment()).commit();
    } else if (id == R.id.nav_links) {

    } else if (id == R.id.nav_help) {

    } else if (id == R.id.nav_about) {

    }

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }
}
