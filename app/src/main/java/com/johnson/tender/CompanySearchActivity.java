package com.johnson.tender;

import android.support.v7.widget.Toolbar;
import android.text.TextUtils;

import com.johnson.tender.databinding.ActivityCompanySearchBinding;

import java.util.ArrayList;

/**
 * Created by Johnson on 2017/5/4.
 */

public class CompanySearchActivity extends SearchActivity<ActivityCompanySearchBinding> {
  @Override
  int getLayoutId() {
    return R.layout.activity_company_search;
  }

  @Override
  int getTitleId() {
    return R.string.company_search_title;
  }

  @Override
  Toolbar getToolbar() {
    return binding.toolbar;
  }

  @Override
  ArrayList<String> getQueries() {
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
}
