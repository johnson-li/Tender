package com.johnson.tender;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.johnson.tender.api.RestApi;
import com.johnson.tender.databinding.FragmentStaffBinding;
import com.johnson.tender.databinding.StaffBinding;
import com.johnson.tender.entity.ListResponse;
import com.johnson.tender.entity.Staff;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by Johnson on 2017/5/2.
 */

public class StaffFragment extends ListFragment<FragmentStaffBinding, StaffBinding, Staff> {
  @Inject
  RestApi restApi;

  @Override
  int getLayoutId() {
    return R.layout.fragment_staff;
  }

  @Override
  int getTitleId() {
    return R.string.navigation_drawer_staff;
  }

  @Override
  RecyclerView getContainer() {
    return binding.container;
  }

  @Override
  Class getSearchActivityClass() {
    return StaffSearchActivity.class;
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    ((App) getActivity().getApplication()).getNetworkComponent().inject(this);
    return super.onCreateView(inflater, container, savedInstanceState);
  }

  @Override
  String getMainKey() {
    return "name";
  }

  @Override
  AbstractAdapter<StaffBinding, Staff> generateAdapter() {
    return new StaffAdapter();
  }

  @Override
  View getNoContent() {
    return binding.noContent;
  }

  @Override
  Observable<ListResponse<Staff>> observeQuery(Map<String, String> queries, int offset, int pageSize) {
    return restApi.queryStaff(queries, offset, pageSize);
  }
}
