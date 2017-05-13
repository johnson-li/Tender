package com.johnson.tender;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.johnson.tender.databinding.ActivityStaffCertBinding;

/**
 * Created by Johnson on 2017/5/2.
 */

public class StaffCertActivity extends DetailActivity<ActivityStaffCertBinding> {

  public static final String STAFF_CERT_ATTR = "staff cert";

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
    return STAFF_CERT_ATTR;
  }

  @Override
  int getLayoutId() {
    return R.layout.activity_staff_cert;
  }

  @Override
  int getTitleId() {
    return R.string.staff_cert_detail_title;
  }
}
