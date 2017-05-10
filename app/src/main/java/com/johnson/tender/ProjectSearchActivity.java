package com.johnson.tender;

import android.support.v4.util.Pair;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;

import com.johnson.tender.databinding.ActivityProjectSearchBinding;
import com.johnson.tender.view.DragListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Johnson on 2017/5/4.
 */

public class ProjectSearchActivity extends SearchActivity<ActivityProjectSearchBinding> {
  @Override
  List<Pair<String, Integer>> getAvailableOrders() {
    List<Pair<String, Integer>> list = new ArrayList<>();
    list.add(new Pair<>("name", R.string.project_search_name));
    return list;
  }

  @Override
  DragListView getDragListView() {
    return binding.dragListView;
  }

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
    String location = binding.location.getText().toString();
    String price = binding.price.getText().toString();
    String type = binding.type.getText().toString();
    String time = binding.time.getText().toString();
    String source = binding.source.getText().toString();
    String judge = binding.judge.getText().toString();
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
    if (!TextUtils.isEmpty(location)) {
      list.add("location");
      list.add(location);
    }
    if (!TextUtils.isEmpty(price)) {
      list.add("price");
      list.add(price);
    }
    if (!TextUtils.isEmpty(type)) {
      list.add("projectType");
      list.add(type);
    }
    if (!TextUtils.isEmpty(time)) {
      list.add("projectTime");
      list.add(time);
    }
    if (!TextUtils.isEmpty(source)) {
      list.add("source");
      list.add(source);
    }
    if (!TextUtils.isEmpty(judge)) {
      list.add("judge");
      list.add(judge);
    }
    return list;
  }
}
