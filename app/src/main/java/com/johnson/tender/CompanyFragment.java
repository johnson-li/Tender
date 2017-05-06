package com.johnson.tender;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.johnson.tender.api.RestApi;
import com.johnson.tender.databinding.CompanyBinding;
import com.johnson.tender.databinding.FragmentCompanyBinding;
import com.johnson.tender.entity.Company;
import com.johnson.tender.entity.ListResponse;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;


/**
 * Created by Johnson on 2017/4/30.
 */

public class CompanyFragment extends ListFragment<FragmentCompanyBinding, CompanyBinding, Company> {
  @Inject
  RestApi restApi;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    ((App) getActivity().getApplication()).getNetworkComponent().inject(this);
    return super.onCreateView(inflater, container, savedInstanceState);
  }

  @Override
  int getLayoutId() {
    return R.layout.fragment_company;
  }

  @Override
  int getTitleId() {
    return R.string.navigation_drawer_companies;
  }

  @Override
  RecyclerView getContainer() {
    return binding.container;
  }

  @Override
  Class getSearchActivityClass() {
    return CompanySearchActivity.class;
  }

  @Override
  String getMainKey() {
    return "name";
  }

  @Override
  AbstractAdapter<CompanyBinding, Company> generateAdapter() {
    return new CompanyAdapter();
  }

  @Override
  View getNoContent() {
    return binding.noContent;
  }

  @Override
  Observable<ListResponse<Company>> observeQuery(Map<String, String> queries, int offset, int pageSize) {
    return restApi.queryCompanies(queries, offset, pageSize);
  }
}
