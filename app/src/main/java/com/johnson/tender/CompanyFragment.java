package com.johnson.tender;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.johnson.tender.api.RestApi;
import com.johnson.tender.databinding.FragmentCompanyBinding;
import com.johnson.tender.entity.Company;
import com.johnson.tender.entity.ListResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;


/**
 * Created by Johnson on 2017/4/30.
 */

public class CompanyFragment extends BaseFragment implements SearchEngine {
  private static final int SEARCH_CODE = 1;
  FragmentCompanyBinding binding;
  CompanyAdapter adapter = new CompanyAdapter();
  Map<String, String> queries = new HashMap<>();
  @Inject
  RestApi restApi;
  boolean isLoading = false;
  boolean noMore = false;
  private int visibleThreshold = 1;
  private int pageSize = 10;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    ((App) getActivity().getApplication()).getNetworkComponent().inject(this);
    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_company, container, false);
    ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.navigation_drawer_companies);
    binding.container.setHasFixedSize(true);
    final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
    binding.container.setLayoutManager(layoutManager);
    binding.container.setAdapter(adapter);
    binding.container.addOnScrollListener(new RecyclerView.OnScrollListener() {
      @Override
      public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        int totalItemCount = layoutManager.getItemCount();
        int lastVisibleItem = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();

        if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
          isLoading = true;
          loadMore(false);
        }
      }
    });
    ((MainActivity) getActivity()).binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = new Intent(getContext(), CompanySearchActivity.class);
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
          List<String> queries = data.getStringArrayListExtra(CompanySearchActivity.QUERY_ATTR);
          Map<String, String> map = new HashMap<>();
          for (int i = 0; i < queries.size(); ) {
            map.put(queries.get(i++), queries.get(i++));
          }
          doSearch(map);
        }
    }
  }

  void doSearch(Map<String, String> queries) {
    this.queries = queries;
    loadMore(true);
  }

  @Override
  public void doSearch(String query) {
    Map<String, String> map = new HashMap<>();
    map.put("name", query);
    doSearch(map);
  }

  void loadMore(final boolean init) {
    ProgressDialog dialog = null;
    int offset = adapter.getSize();
    if (init) {
      adapter.clear();
      noMore = false;
      dialog = ProgressDialog.show(getContext(), "Loading", "Loading...", true, false);
    } else if (noMore) {
      return;
    } else {
      adapter.add(null);
    }
    subscribe(restApi.queryCompanies(queries, offset, pageSize), new Consumer<ListResponse<Company>>() {
      @Override
      public void accept(@NonNull ListResponse<Company> companyListResponse) throws Exception {
        if (init && companyListResponse.getContent().isEmpty()) {
          binding.noContent.setVisibility(View.VISIBLE);
        } else {
          binding.noContent.setVisibility(View.GONE);
        }
        if (!init) {
          adapter.removeLast();
          isLoading = false;
        }
        if (companyListResponse.getContent().isEmpty()) {
          noMore = true;
        }
        for (Company company : companyListResponse.getContent()) {
          adapter.add(company);
        }
      }
    }, dialog);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    ((MainActivity) getActivity()).setSearchEngine(this);
    loadMore(true);
  }
}
