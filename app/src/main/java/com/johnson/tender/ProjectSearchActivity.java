package com.johnson.tender;

import android.support.v7.widget.Toolbar;
import android.text.TextUtils;

import com.johnson.tender.databinding.ActivityProjectSearchBinding;

import java.util.ArrayList;

/**
 * Created by Johnson on 2017/5/4.
 */

public class ProjectSearchActivity extends SearchActivity<ActivityProjectSearchBinding> {

  @Override
  int getLayoutId() {
    return R.layout.activity_project_search;
  }

  @Override
  int getTitleId() {
    return R.string.project_search_title;
  }

  @Override
  Toolbar getToolbar() {
    return binding.toolbar;
  }

  @Override
  ArrayList<String> getQueries() {
    ArrayList<String> list = new ArrayList<>();
    String name = binding.name.getText().toString();
    String company = binding.company.getText().toString();
    String staff = binding.staff.getText().toString();
    String title = binding.title.getText().toString();
    if (!TextUtils.isEmpty(name)) {
      list.add("name");
      list.add(name);
    }
    if (!TextUtils.isEmpty(company)) {
      list.add("company");
      list.add(company);
    }
    if (!TextUtils.isEmpty(staff)) {
      list.add("staff");
      list.add(staff);
    }
    if (!TextUtils.isEmpty(title)) {
      list.add("title");
      list.add(title);
    }
    return list;
  }
}
