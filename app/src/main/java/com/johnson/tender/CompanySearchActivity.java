package com.johnson.tender;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;

import com.johnson.tender.databinding.ActivityCompanySearchBinding;

import java.util.ArrayList;

/**
 * Created by Johnson on 2017/5/4.
 */

public class CompanySearchActivity extends BaseActivity {
  public static final String QUERY_ATTR = "query";
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

  private ArrayList<String> getQueries() {
    ArrayList<String> list = new ArrayList<>();
    String name = binding.name.getText().toString();
    String people = binding.people.getText().toString();
    String address = binding.address.getText().toString();
    String constructor = binding.constructor.getText().toString();
    String executive = binding.executive.getText().toString();
    String manager = binding.manager.getText().toString();
    String supervisor = binding.supervisor.getText().toString();
    if (!TextUtils.isEmpty(name)) {
      list.add("name");
      list.add(name);
    }
    if (!TextUtils.isEmpty(people)) {
      list.add("people");
      list.add(people);
    }
    if (!TextUtils.isEmpty(address)) {
      list.add("address");
      list.add(address);
    }
    if (!TextUtils.isEmpty(constructor)) {
      list.add("constructor");
      list.add(constructor);
    }
    if (!TextUtils.isEmpty(executive)) {
      list.add("executive");
      list.add(executive);
    }
    if (!TextUtils.isEmpty(manager)) {
      list.add("manager");
      list.add(manager);
    }
    if (!TextUtils.isEmpty(supervisor)) {
      list.add("supervisor");
      list.add(supervisor);
    }
    return list;
  }

  private void searchAction() {
    Intent intent = new Intent();
    intent.putStringArrayListExtra(QUERY_ATTR, getQueries());
    setResult(Activity.RESULT_OK, intent);
    finish();
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        finish();
      case R.id.confirm:
        searchAction();
    }
    return super.onOptionsItemSelected(item);
  }
}
