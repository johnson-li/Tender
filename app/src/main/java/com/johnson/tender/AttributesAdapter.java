package com.johnson.tender;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.johnson.tender.databinding.AttributeBinding;
import com.johnson.tender.entity.Company;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Johnson on 2017/5/2.
 */

public class AttributesAdapter extends RecyclerView.Adapter<AttributesAdapter.ViewHolder> {

  private List<String> attributePairs = new ArrayList<>();

  public void add(Company company) {
    add("ID", company.getId());
    add("CompanyName", company.getCompanyName());
    add("CompanyCode", company.getCompanyCode());
  }

  public void add(String key, long val) {
    add(key, String.valueOf(val));
  }

  public void add(String key, String val) {
    attributePairs.add(key);
    attributePairs.add(val);
    notifyDataSetChanged();
  }

  @Override
  public AttributesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    AttributeBinding binding = DataBindingUtil
        .inflate(LayoutInflater.from(parent.getContext()), R.layout.attribute, parent, false);
    ViewHolder viewHolder = new ViewHolder(binding);
    return viewHolder;
  }

  @Override
  public void onBindViewHolder(AttributesAdapter.ViewHolder holder, int position) {
    holder.binding.text.setText(attributePairs.get(position));
  }

  @Override
  public int getItemCount() {
    return attributePairs.size();
  }

  class ViewHolder extends RecyclerView.ViewHolder {
    AttributeBinding binding;

    public ViewHolder(AttributeBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
    }
  }
}
