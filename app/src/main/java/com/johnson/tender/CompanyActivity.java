package com.johnson.tender;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.johnson.tender.databinding.ActivityCompanyBinding;

/**
 * Created by Johnson on 2017/5/2.
 */

public class CompanyActivity extends DetailActivity<ActivityCompanyBinding> {
  public static final String COMPANY_ATTR = "company";

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
    return COMPANY_ATTR;
  }

  @Override
  int getLayoutId() {
    return R.layout.activity_company;
  }

  @Override
  int getTitleId() {
    return R.string.company_detail_title;
  }
}
