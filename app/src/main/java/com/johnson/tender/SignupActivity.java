package com.johnson.tender;

import android.app.Activity;
import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.johnson.tender.api.RestApi;
import com.johnson.tender.databinding.ActivitySignupBinding;
import com.johnson.tender.entity.ObjectResponse;
import com.johnson.tender.entity.User;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by Johnson on 2017/4/30.
 */

public class SignupActivity extends BaseActivity {
  ActivitySignupBinding binding;

  @Inject
  RestApi restApi;

  @Inject
  Preferences preferences;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    ((App) getApplication()).getNetworkComponent().inject(this);
    super.onCreate(savedInstanceState);
    binding = DataBindingUtil.setContentView(this, R.layout.activity_signup);

    setSupportActionBar(binding.toolbar);
    getSupportActionBar().setTitle(R.string.signup_title);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setDisplayShowHomeEnabled(true);

    if (BuildConfig.DEBUG) {
      binding.email.setText(BuildConfig.EMAIL);
      binding.password.setText(BuildConfig.PASSWORD);
    }

    binding.emailSignUpButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String email = binding.email.getText().toString();
        String password = binding.password.getText().toString();
        String verification = binding.verification.getText().toString();
        ProgressDialog dialog = ProgressDialog.show(SignupActivity.this, "Signup", "Waiting...");
        subscribe(restApi.signup(email, password, verification), new Consumer<ObjectResponse<User>>() {
          @Override
          public void accept(@NonNull ObjectResponse<User> userObjectResponse) throws Exception {
            User user = userObjectResponse.getContent();
            if (user != null) {
              preferences.setUser(user);
              Toast.makeText(SignupActivity.this, "Signup succeeded!", Toast.LENGTH_SHORT).show();
              setResult(Activity.RESULT_OK);
              finish();
            } else {
              preferences.setUser(user);
              Toast.makeText(SignupActivity.this, "Signup failed!", Toast.LENGTH_SHORT).show();
            }
          }
        }, dialog);
      }
    });
  }

  final boolean isValidEmail(CharSequence target) {
    if (TextUtils.isEmpty(target)) {
      return false;
    } else {
      return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
  }

  boolean isValidPhone(String str) {
    return TextUtils.isDigitsOnly(str) && str.length() == 11;
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.signup, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        finish();
      case R.id.verification:
        String email = binding.email.getText().toString();
        if (isValidEmail(email)) {
          subscribe(restApi.verifyEmail(email), null, null);
          Toast.makeText(this, "Verification email has been sent", Toast.LENGTH_SHORT).show();
        } else if (isValidPhone(email)) {
          Toast.makeText(this, "Phone registration is not supported", Toast.LENGTH_SHORT).show();
//          subscribe(restApi.verifyPhone(email), null, null);
//          Toast.makeText(this, "Verification SMS has been sent", Toast.LENGTH_SHORT).show();
        } else {
          Toast.makeText(this, "Invalid email address nor phone number", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
