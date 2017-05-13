package com.johnson.tender;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.johnson.tender.databinding.ActivityStaffAchievementBinding;

/**
 * Created by Johnson on 2017/5/2.
 */

public class StaffAchievementActivity extends DetailActivity<ActivityStaffAchievementBinding> {

  public static final String STAFF_ACHIEVEMENT_ATTR = "staff achievement";

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
    return STAFF_ACHIEVEMENT_ATTR;
  }

  @Override
  int getLayoutId() {
    return R.layout.activity_staff_achievement;
  }

  @Override
  int getTitleId() {
    return R.string.staff_achievement_detail_title;
  }
}
