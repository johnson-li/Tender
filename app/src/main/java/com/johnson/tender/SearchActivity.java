package com.johnson.tender;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.johnson.tender.view.DragListView;
import com.johnson.tender.view.ListSwipeHelper;
import com.johnson.tender.view.OrderCheckBox;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Johnson on 2017/5/5.
 */

public abstract class SearchActivity<T extends ViewDataBinding> extends BaseActivity {
  public static final String QUERY_ATTR = "query";
  public static final String ORDER_ATTR = "order";
  T binding;
  BoxDragItemAdapter adapter = new BoxDragItemAdapter(getAvailableOrders());
  RecyclerView.LayoutManager layoutManager;

  abstract int getLayoutId();

  abstract int getTitleId();

  abstract Toolbar getToolbar();

  abstract ArrayList<String> getQueries();

  ArrayList<String> getOrders() {
    ArrayList<String> list = new ArrayList<>();
    for (int i = 0; i < adapter.getItemCount(); i++) {
      String key = adapter.getItemList().get(i).first;
      OrderCheckBox box = (OrderCheckBox) layoutManager.findViewByPosition(i).findViewById(R.id.box);
      switch (box.getOrder()) {
        case ASCENDING:
          list.add(key);
          break;
        case DESCENDING:
          list.add("~" + key);
          break;
      }
    }
    return list;
  }

  abstract List<Pair<String, Integer>> getAvailableOrders();

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = DataBindingUtil.setContentView(this, getLayoutId());

    setSupportActionBar(getToolbar());
    getSupportActionBar().setTitle(getTitleId());
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setDisplayShowHomeEnabled(true);

    layoutManager = new LinearLayoutManager(getApplicationContext()) {
      @Override
      public boolean canScrollVertically() {
        return false;
      }
    };
    getDragListView().setDragListListener(new DragListView.DragListListenerAdapter());
    getDragListView().setLayoutManager(layoutManager);
    getDragListView().setAdapter(adapter, true);
    getDragListView().setCanDragHorizontally(false);
    getDragListView().setSwipeListener(new ListSwipeHelper.OnSwipeListenerAdapter() {
    });
    getDragListView().setDragEnabled(true);
  }

  abstract DragListView getDragListView();

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.search, menu);
    return true;
  }


  private void searchAction() {
    Intent intent = new Intent();
    intent.putStringArrayListExtra(QUERY_ATTR, getQueries());
    intent.putStringArrayListExtra(ORDER_ATTR, getOrders());
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
