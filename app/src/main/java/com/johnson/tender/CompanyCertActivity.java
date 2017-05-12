package com.johnson.tender;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.johnson.tender.databinding.ActivityCompanyCertBinding;

/**
 * Created by Johnson on 2017/5/2.
 */

public class CompanyCertActivity extends DetailActivity<ActivityCompanyCertBinding> {
  public static final String COMPANY_CERT_ATTR = "company cert";

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
    return COMPANY_CERT_ATTR;
  }

  @Override
  int getLayoutId() {
    return R.layout.activity_company_cert;
  }

  @Override
  int getTitleId() {
    return R.string.company_cert_detail_title;
  }
}
