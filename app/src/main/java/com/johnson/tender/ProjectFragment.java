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

import com.johnson.tender.databinding.FragmentProjectBinding;

/**
 * Created by Johnson on 2017/5/2.
 */

public class ProjectFragment extends Fragment {
  FragmentProjectBinding binding;
  ProjectAdapter adapter = new ProjectAdapter();

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_project, container, false);
    ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.navigation_drawer_projects);
    binding.container.setHasFixedSize(true);
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
    binding.container.setLayoutManager(layoutManager);
    binding.container.setAdapter(adapter);
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    for (int i = 0; i < 10; i++) {
      Project project = new Project();
      project.setName("project" + i);
      adapter.add(project);
    }
  }
}
