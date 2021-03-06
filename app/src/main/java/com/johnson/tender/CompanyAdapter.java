package com.johnson.tender;

import android.content.Intent;
import android.view.View;

import com.johnson.tender.databinding.CompanyBinding;
import com.johnson.tender.entity.Company;

/**
 * Created by Johnson on 2017/4/30.
 */

public class CompanyAdapter extends AbstractAdapter<CompanyBinding, Company> {
  @Override
  int getLayoutId() {
    return R.layout.company;
  }

  @Override
  public void onBindViewHolder(final AbstractAdapter.ViewHolder holder, int position) {
    if (holder.binding != null) {
      CompanyBinding binding = (CompanyBinding) holder.binding;
      Company company = list.get(position);
      binding.name.setText(company.getCompanyName());
      binding.code.setText(company.getCompanyCode());
      binding.people.setText(company.getLegalRepresentative());
      binding.getRoot().setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          Intent intent = new Intent(holder.binding.getRoot().getContext(), CompanyActivity.class);
          intent.putExtra(CompanyActivity.COMPANY_ATTR, list.get(holder.getAdapterPosition()));
          intent.putExtra(CompanyActivity.LIKE_ATTR, true);
          holder.binding.getRoot().getContext().startActivity(intent);
        }
      });
    } else {
      holder.itemLoadingBinding.progressBar.setIndeterminate(true);
    }
  }
}
