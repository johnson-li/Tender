package com.johnson.tender;

import android.support.v7.widget.Toolbar;
import android.text.TextUtils;

import com.johnson.tender.databinding.ActivityStaffSearchBinding;

import java.util.ArrayList;

/**
 * Created by Johnson on 2017/5/4.
 */

public class StaffSearchActivity extends SearchActivity<ActivityStaffSearchBinding> {
  @Override
  int getLayoutId() {
    return R.layout.activity_staff_search;
  }

  @Override
  int getTitleId() {
    return R.string.staff_search_title;
  }

  @Override
  Toolbar getToolbar() {
    return binding.toolbar;
  }

  ArrayList<String> getQueries() {
    ArrayList<String> list = new ArrayList<>();
    String id = binding.id.getText().toString();
    String name = binding.name.getText().toString();
    String company = binding.company.getText().toString();
    String registrationId = binding.registrationId.getText().toString();
    String title = binding.title.getText().toString();
    if (!TextUtils.isEmpty(id)) {
      list.add("id");
      list.add(id);
    }
    if (!TextUtils.isEmpty(name)) {
      list.add("name");
      list.add(name);
    }
    if (!TextUtils.isEmpty(company)) {
      list.add("company");
      list.add(company);
    }
    if (!TextUtils.isEmpty(registrationId)) {
      list.add("registrationId");
      list.add(registrationId);
    }
    if (!TextUtils.isEmpty(title)) {
      list.add("title");
      list.add(title);
    }
    return list;
  }
}
