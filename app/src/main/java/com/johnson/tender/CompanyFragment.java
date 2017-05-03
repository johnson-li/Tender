package com.johnson.tender;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Johnson on 2017/4/30.
 */

public class CompanyFragment extends Fragment {
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
    Call<ListResponse<Company>> call = restApi.getCompanies();
    call.enqueue(new Callback<ListResponse<Company>>() {
      @Override
      public void onResponse(Call<ListResponse<Company>> call, Response<ListResponse<Company>> response) {
        for (Company company : response.body().getContent()) {
          adapter.add(company);
        }
      }

      @Override
      public void onFailure(Call<ListResponse<Company>> call, Throwable t) {
        t.printStackTrace();
      }
    });
  }
}
