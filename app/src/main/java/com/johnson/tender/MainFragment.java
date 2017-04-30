package com.johnson.tender;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.johnson.tender.databinding.FragmentMainBinding;

/**
 * Created by Johnson on 2017/4/30.
 */

public class MainFragment extends Fragment {
  FragmentMainBinding binding;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
    ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.navigation_drawer_main);
    return binding.getRoot();
  }
}
