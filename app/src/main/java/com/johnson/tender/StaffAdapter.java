package com.johnson.tender;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.johnson.tender.databinding.StaffBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Johnson on 2017/5/2.
 */

public class StaffAdapter extends RecyclerView.Adapter<StaffAdapter.ViewHolder> {
  List<Staff> staffList = new ArrayList<>();

  public void add(Staff staff) {
    staffList.add(staff);
    notifyDataSetChanged();
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    StaffBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.staff, parent, false);
    ViewHolder viewHolder = new ViewHolder(binding);
    return viewHolder;
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, final int position) {
    holder.binding.name.setText(staffList.get(position).getName());
    holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(v.getContext(), StaffActivity.class);
        intent.putExtra(StaffActivity.STAFF_ID_ATTR, staffList.get(position).getId());
        v.getContext().startActivity(intent);
      }
    });
  }

  @Override
  public int getItemCount() {
    return staffList.size();
  }

  class ViewHolder extends RecyclerView.ViewHolder {
    StaffBinding binding;

    public ViewHolder(StaffBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
    }
  }
}
