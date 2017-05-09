package com.johnson.tender;

import android.support.v4.util.Pair;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;

import com.johnson.tender.databinding.ActivityStaffSearchBinding;
import com.johnson.tender.view.DragListView;

import java.util.ArrayList;
import java.util.List;

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
    String gender = binding.gender.getText().toString();
    String type = binding.type.getText().toString();
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
    if (!TextUtils.isEmpty(type)) {
      list.add("type");
      list.add(type);
    }
    if (!TextUtils.isEmpty(gender)) {
      list.add("gender");
      list.add(gender);
    }
    return list;
  }

  List<Pair<String, Integer>> getAvailableOrders() {
    List<Pair<String, Integer>> list = new ArrayList<>();
    list.add(new Pair<>("name", R.string.staff_search_name));
    list.add(new Pair<>("identityId", R.string.staff_search_id));
    return list;
  }

  @Override
  DragListView getDragListView() {
    return binding.dragListView;
  }
}
