package com.johnson.tender;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.johnson.tender.api.RestApi;
import com.johnson.tender.databinding.ActivityCompanyBinding;
import com.johnson.tender.entity.Company;

import javax.inject.Inject;

/**
 * Created by Johnson on 2017/5/2.
 */

public class CompanyActivity extends DetailActivity<ActivityCompanyBinding> {
  public static final String COMPANY_ATTR = "company";
  Menu menu;

  @Inject
  RestApi restApi;

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

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    ((App) getApplication()).getNetworkComponent().inject(this);
    super.onCreate(savedInstanceState);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    Company company = (Company) data;
    this.menu = menu;
    getMenuInflater().inflate(R.menu.company, menu);
    if (company.isLiked()) {
      menu.findItem(R.id.like).setIcon(R.drawable.ic_favorite_black_24dp);
    }
    menu.findItem(R.id.likeNum).setTitle(String.valueOf(company.getLikedNum()));
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    Company company = (Company) data;
    switch (item.getItemId()) {
      case R.id.like:
        if (company.isLiked()) {
          company.setLiked(false);
          company.setLikedNum(company.getLikedNum() - 1);
          item.setIcon(R.drawable.ic_favorite_border_black_24dp);
          subscribe(restApi.companyUnlike(company.getId()), null, null);
        } else {
          company.setLiked(true);
          company.setLikedNum(company.getLikedNum() + 1);
          item.setIcon(R.drawable.ic_favorite_black_24dp);
          subscribe(restApi.companyLike(company.getId()), null, null);
        }
        menu.findItem(R.id.likeNum).setTitle(String.valueOf(company.getLikedNum()));
        return true;
      case R.id.map:
        return true;
      case R.id.share:
        return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
