package com.johnson.tender;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;

import com.johnson.tender.databinding.ActivityCompanySearchBinding;

/**
 * Created by Johnson on 2017/5/4.
 */

public class CompanySearchActivity extends BaseActivity {
  ActivityCompanySearchBinding binding;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = DataBindingUtil.setContentView(this, R.layout.activity_company_search);

    setSupportActionBar(binding.toolbar);
    getSupportActionBar().setTitle(R.string.company_search_title);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setDisplayShowHomeEnabled(true);
  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.search, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        finish();
      case R.id.confirm:
        finish();
    }
    return super.onOptionsItemSelected(item);
  }
}
