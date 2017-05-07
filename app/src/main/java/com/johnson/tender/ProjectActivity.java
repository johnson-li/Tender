package com.johnson.tender;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.johnson.tender.databinding.ActivityProjectBinding;

/**
 * Created by Johnson on 2017/5/2.
 */

public class ProjectActivity extends DetailActivity<ActivityProjectBinding> {
  public static final String PROJECT_ATTR = "project";

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
    return PROJECT_ATTR;
  }

  @Override
  int getLayoutId() {
    return R.layout.activity_project;
  }

  @Override
  int getTitleId() {
    return R.string.project_detail_title;
  }
}
