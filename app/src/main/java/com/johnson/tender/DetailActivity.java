package com.johnson.tender;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

/**
 * Created by Johnson on 2017/5/7.
 */

public abstract class DetailActivity<T extends ViewDataBinding> extends AppCompatActivity {
  AttributesAdapter adapter = new AttributesAdapter();
  T binding;

  abstract Toolbar getToolbar();

  abstract RecyclerView getContainer();

  abstract String getAttr();

  abstract int getLayoutId();

  abstract int getTitleId();

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = DataBindingUtil.setContentView(this, getLayoutId());

    setSupportActionBar(getToolbar());
    getSupportActionBar().setTitle(getTitleId());
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setDisplayShowHomeEnabled(true);

    getContainer().setHasFixedSize(true);
    GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
    layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
      @Override
      public int getSpanSize(int position) {
        return 1;
      }
    });
    getContainer().setLayoutManager(layoutManager);
    getContainer().setAdapter(adapter);
    getContainer().addItemDecoration(new DividerItemDecoration(getApplicationContext(), layoutManager.getOrientation()));

    if (getIntent().hasExtra(getAttr())) {
      adapter.add(getIntent().getSerializableExtra(getAttr()));
    }
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        finish();
    }
    return super.onOptionsItemSelected(item);
  }
}
