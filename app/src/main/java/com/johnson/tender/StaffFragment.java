package com.johnson.tender;

import android.content.Intent;
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

import com.johnson.tender.databinding.FragmentStaffBinding;
import com.johnson.tender.entity.Staff;

/**
 * Created by Johnson on 2017/5/2.
 */

public class StaffFragment extends Fragment {
  FragmentStaffBinding binding;
  StaffAdapter adapter = new StaffAdapter();

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_staff, container, false);
    ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.navigation_drawer_staff);
    binding.container.setHasFixedSize(true);
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
    binding.container.setLayoutManager(layoutManager);
    binding.container.setAdapter(adapter);
    ((MainActivity) getActivity()).binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = new Intent(getContext(), StaffSearchActivity.class);
        startActivity(intent);
      }
    });
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    for (int i = 0; i < 10; i++) {
      Staff staff = new Staff();
      staff.setName("staff" + i);
      adapter.add(staff);
    }
  }
}
