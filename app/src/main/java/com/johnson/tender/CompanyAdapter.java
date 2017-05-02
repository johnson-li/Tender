package com.johnson.tender;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.johnson.tender.databinding.CompanyBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Johnson on 2017/4/30.
 */

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.ViewHolder> {

  List<Company> companies = new ArrayList<>();

  public void add(Company company) {
    companies.add(company);
    notifyDataSetChanged();
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    CompanyBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.company, parent, false);
    ViewHolder viewHolder = new ViewHolder(binding);
    return viewHolder;
  }

  @Override
  public void onBindViewHolder(final ViewHolder holder, final int position) {
    holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(holder.binding.getRoot().getContext(), CompanyActivity.class);
        intent.putExtra(CompanyActivity.COMPANY_ID_ATTR, companies.get(position).getId());
        holder.binding.getRoot().getContext().startActivity(intent);
      }
    });
  }

  @Override
  public int getItemCount() {
    return companies.size();
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {
    CompanyBinding binding;

    public ViewHolder(CompanyBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
    }
  }
}
