package com.johnson.tender;

import android.app.ProgressDialog;
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

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;


/**
 * Created by Johnson on 2017/4/30.
 */

public class CompanyFragment extends BaseFragment {
  FragmentCompanyBinding binding;
  CompanyAdapter adapter = new CompanyAdapter();

  @Inject
  RestApi restApi;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    ((App) getActivity().getApplication()).getNetworkComponent().inject(this);
    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_company, container, false);
    ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.navigation_drawer_companies);
    binding.container.setHasFixedSize(true);
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
    binding.container.setLayoutManager(layoutManager);
    binding.container.setAdapter(adapter);
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    ProgressDialog dialog = ProgressDialog.show(getContext(), "Loading", "Loading...", true, false);
    subscribe(restApi.getCompanies(), new Consumer<ListResponse<Company>>() {
      @Override
      public void accept(@NonNull ListResponse<Company> companyListResponse) throws Exception {
        for (Company company : companyListResponse.getContent()) {
          adapter.add(company);
        }
      }
    }, dialog);
  }
}
