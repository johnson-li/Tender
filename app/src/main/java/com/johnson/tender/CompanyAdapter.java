package com.johnson.tender;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.johnson.tender.databinding.CompanyBinding;
import com.johnson.tender.databinding.ItemLoadingBinding;
import com.johnson.tender.entity.Company;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Johnson on 2017/4/30.
 */

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.ViewHolder> {
  private final int VIEW_TYPE_ITEM = 0;
  private final int VIEW_TYPE_LOADING = 1;

  private List<Company> companies = new ArrayList<>();

  public void removeLast() {
    assert companies.get(companies.size() - 1) == null;
    companies.remove(companies.size() - 1);
    notifyDataSetChanged();
  }

  public void add(Company company) {
    companies.add(company);
    notifyDataSetChanged();
  }

  public int getSize() {
    return companies.size();
  }

  @Override
  public int getItemViewType(int position) {
    return companies.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    switch (viewType) {
      case VIEW_TYPE_ITEM:
        CompanyBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.company, parent, false);
        return new ViewHolder(binding);
      case VIEW_TYPE_LOADING:
        ItemLoadingBinding itemLoadingBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_loading, parent, false);
        return new ViewHolder(itemLoadingBinding);
    }
    return null;
  }

  @Override
  public void onBindViewHolder(final ViewHolder holder, int position) {
    if (holder.companyBinding != null) {
      holder.companyBinding.name.setText(companies.get(position).getCompanyName());
      holder.companyBinding.getRoot().setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          Intent intent = new Intent(holder.companyBinding.getRoot().getContext(), CompanyActivity.class);
          intent.putExtra(CompanyActivity.COMPANY_ATTR, companies.get(holder.getAdapterPosition()));
          holder.companyBinding.getRoot().getContext().startActivity(intent);
        }
      });
    } else {
      holder.itemLoadingBinding.progressBar.setIndeterminate(true);
    }
  }

  @Override
  public int getItemCount() {
    return companies.size();
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {
    CompanyBinding companyBinding;
    ItemLoadingBinding itemLoadingBinding;

    public ViewHolder(ItemLoadingBinding binding) {
      super(binding.getRoot());
      this.itemLoadingBinding = binding;
    }

    public ViewHolder(CompanyBinding binding) {
      super(binding.getRoot());
      this.companyBinding = binding;
    }
  }
}
