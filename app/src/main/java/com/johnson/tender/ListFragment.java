package com.johnson.tender;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.johnson.tender.entity.ListResponse;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by Johnson on 2017/5/5.
 */

public abstract class ListFragment<B extends ViewDataBinding, T extends ViewDataBinding, M> extends BaseFragment implements SearchEngine {

  private static final int SEARCH_CODE = 1;
  boolean isLoading = false;
  boolean noMore = false;
  Map<String, String> queries = new HashMap<>();
  String orders;
  B binding;
  private int visibleThreshold = 1;
  private int pageSize = 10;
  private AbstractAdapter<T, M> adapter = generateAdapter();

  abstract int getLayoutId();

  abstract int getTitleId();

  abstract RecyclerView getContainer();

  abstract Class getSearchActivityClass();

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
    ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getTitleId());
    getContainer().setHasFixedSize(true);
    final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
    getContainer().setLayoutManager(layoutManager);
    getContainer().addItemDecoration(new DividerItemDecoration(getContext(), layoutManager.getOrientation()));
    getContainer().setAdapter(adapter);
    getContainer().addOnScrollListener(new RecyclerView.OnScrollListener() {
      @Override
      public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        int totalItemCount = layoutManager.getItemCount();
        int lastVisibleItem = layoutManager.findLastVisibleItemPosition();
        if (totalItemCount <= (lastVisibleItem + visibleThreshold)) {
          loadMore(false);
        }
      }
    });
    ((MainActivity) getActivity()).binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = new Intent(getContext(), getSearchActivityClass());
        startActivityForResult(intent, SEARCH_CODE);
      }
    });
    return binding.getRoot();
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    switch (requestCode) {
      case SEARCH_CODE:
        if (resultCode == Activity.RESULT_OK) {
          ((MainActivity) getActivity()).collapseSearch();
          List<String> queries = data.getStringArrayListExtra(SearchActivity.QUERY_ATTR);
          Map<String, String> map = new HashMap<>();
          for (int i = 0; i < queries.size(); ) {
            map.put(queries.get(i++), queries.get(i++));
          }
          List<String> orders = data.getStringArrayListExtra(SearchActivity.ORDER_ATTR);
          doSearch(map, StringUtils.join(orders, ","));
        }
    }
  }

  abstract String getMainKey();

  @Override
  public void doSearch(String query) {
    Map<String, String> map = new HashMap<>();
    map.put(getMainKey(), query);
    doSearch(map, "");
  }

  void doSearch(Map<String, String> queries, String orders) {
    this.queries = queries;
    this.orders = orders;
    loadMore(true);
  }

  abstract AbstractAdapter<T, M> generateAdapter();

  abstract View getNoContent();

  abstract Observable<ListResponse<M>> observeQuery(Map<String, String> queries, String orders, int offset, int pageSize);

  void loadMore(final boolean init) {
    if (isLoading) {
      return;
    }
    isLoading = true;
    ProgressDialog dialog = null;
    if (init) {
      adapter.clear();
      noMore = false;
      dialog = ProgressDialog.show(getContext(), "Loading", "Loading...", true, false);
    } else if (noMore) {
      return;
    } else {
      adapter.add(null);
    }
    int offset = adapter.getSize();
    subscribe(observeQuery(queries, orders, offset, pageSize), new Consumer<ListResponse<M>>() {
      @Override
      public void accept(@NonNull ListResponse<M> listResponse) throws Exception {
        if (init && listResponse.getContent().isEmpty()) {
          getNoContent().setVisibility(View.VISIBLE);
        } else {
          getNoContent().setVisibility(View.GONE);
        }
        if (!init) {
          adapter.removeLast();
        }
        if (listResponse.getContent().isEmpty()) {
          noMore = true;
        }
        for (M m : listResponse.getContent()) {
          adapter.add(m);
        }
      }
    }, dialog, new Action() {
      @Override
      public void run() throws Exception {
        isLoading = false;
      }
    });
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    ((MainActivity) getActivity()).setSearchEngine(this);
    loadMore(true);
  }
}
