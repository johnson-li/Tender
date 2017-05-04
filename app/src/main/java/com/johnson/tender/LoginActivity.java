package com.johnson.tender;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.johnson.tender.api.RestApi;
import com.johnson.tender.databinding.ActivityLoginBinding;
import com.johnson.tender.entity.ObjectResponse;
import com.johnson.tender.entity.User;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by Johnson on 2017/4/30.
 */

public class LoginActivity extends BaseActivity {

  ActivityLoginBinding binding;

  @Inject
  RestApi restApi;

  @Inject
  Preferences preferences;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    ((App) getApplication()).getNetworkComponent().inject(this);
    super.onCreate(savedInstanceState);
    binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

    setSupportActionBar(binding.toolbar);
    getSupportActionBar().setTitle(R.string.login_title);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setDisplayShowHomeEnabled(true);

    if (BuildConfig.DEBUG) {
      binding.email.setText(BuildConfig.EMAIL);
      binding.password.setText(BuildConfig.PASSWORD);
    }

    binding.emailSignInButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String email = binding.email.getText().toString();
        String password = binding.password.getText().toString();
        ProgressDialog dialog = ProgressDialog.show(LoginActivity.this, "Login", "Waiting...");
        subscribe(restApi.login(email, password), new Consumer<ObjectResponse<User>>() {
          @Override
          public void accept(@NonNull ObjectResponse<User> userObjectResponse) throws Exception {
            User user = userObjectResponse.getContent();
            preferences.setUser(user);
            Toast.makeText(LoginActivity.this, "Login succeeded!", Toast.LENGTH_SHORT).show();
            finish();
          }
        }, dialog);
      }
    });
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
