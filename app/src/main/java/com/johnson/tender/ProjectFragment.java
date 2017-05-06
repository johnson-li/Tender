package com.johnson.tender;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.johnson.tender.api.RestApi;
import com.johnson.tender.databinding.FragmentProjectBinding;
import com.johnson.tender.databinding.ProjectBinding;
import com.johnson.tender.entity.ListResponse;
import com.johnson.tender.entity.Project;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by Johnson on 2017/5/2.
 */

public class ProjectFragment extends ListFragment<FragmentProjectBinding, ProjectBinding, Project> {
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
    return R.layout.fragment_project;
  }

  @Override
  int getTitleId() {
    return R.string.navigation_drawer_projects;
  }

  @Override
  RecyclerView getContainer() {
    return binding.container;
  }

  @Override
  Class getSearchActivityClass() {
    return ProjectSearchActivity.class;
  }

  @Override
  String getMainKey() {
    return "name";
  }

  @Override
  AbstractAdapter<ProjectBinding, Project> generateAdapter() {
    return new ProjectAdapter();
  }

  @Override
  View getNoContent() {
    return binding.noContent;
  }

  @Override
  Observable<ListResponse<Project>> observeQuery(Map<String, String> queries, int offset, int pageSize) {
    return restApi.queryProject(queries, offset, pageSize);
  }
}
