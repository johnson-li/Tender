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

import com.johnson.tender.databinding.FragmentCompanyBinding;


/**
 * Created by Johnson on 2017/4/30.
 */

public class CompanyFragment extends Fragment {
  FragmentCompanyBinding binding;
  CompanyAdapter adapter = new CompanyAdapter();

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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
    for (int i = 0; i < 10; i++) {
      adapter.add(new Company());
    }
  }
}
