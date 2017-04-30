package com.johnson.tender;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.johnson.tender.databinding.ActivityLoginBinding;

/**
 * Created by Johnson on 2017/4/30.
 */

public class LoginActivity extends AppCompatActivity {

  ActivityLoginBinding binding;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

    setSupportActionBar(binding.toolbar);
    getSupportActionBar().setTitle(R.string.login_title);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setDisplayShowHomeEnabled(true);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        finish();
    }
    return super.onOptionsItemSelected(item);
  }
}
