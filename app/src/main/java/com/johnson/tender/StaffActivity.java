package com.johnson.tender;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.johnson.tender.databinding.ActivityStaffBinding;

/**
 * Created by Johnson on 2017/5/2.
 */

public class StaffActivity extends DetailActivity<ActivityStaffBinding> {

  public static final String STAFF_ATTR = "staff";

  @Override
  Toolbar getToolbar() {
    return binding.toolbar;
  }

  @Override
  RecyclerView getContainer() {
    return binding.attributes;
  }

  @Override
  String getAttr() {
    return STAFF_ATTR;
  }

  @Override
  int getLayoutId() {
    return R.layout.activity_staff;
  }

  @Override
  int getTitleId() {
    return R.string.staff_detail_title;
  }
}
