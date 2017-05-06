package com.johnson.tender;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

/**
 * Created by Johnson on 2017/5/5.
 */

public abstract class SearchActivity<T extends ViewDataBinding> extends BaseActivity {
  public static final String QUERY_ATTR = "query";
  T binding;

  abstract int getLayoutId();

  abstract int getTitleId();

  abstract Toolbar getToolbar();

  abstract ArrayList<String> getQueries();

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = DataBindingUtil.setContentView(this, getLayoutId());

    setSupportActionBar(getToolbar());
    getSupportActionBar().setTitle(getTitleId());
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setDisplayShowHomeEnabled(true);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.search, menu);
    return true;
  }


  private void searchAction() {
    Intent intent = new Intent();
    intent.putStringArrayListExtra(QUERY_ATTR, getQueries());
    setResult(Activity.RESULT_OK, intent);
    finish();
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        finish();
      case R.id.confirm:
        searchAction();
    }
    return super.onOptionsItemSelected(item);
  }
}
