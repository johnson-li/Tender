package com.johnson.tender;

import android.content.Intent;
import android.view.View;

import com.johnson.tender.databinding.StaffBinding;
import com.johnson.tender.entity.Staff;

/**
 * Created by Johnson on 2017/5/2.
 */

public class StaffAdapter extends AbstractAdapter<StaffBinding, Staff> {
  @Override
  int getLayoutId() {
    return R.layout.staff;
  }

  @Override
  public void onBindViewHolder(final AbstractAdapter.ViewHolder holder, int position) {
    if (holder.binding != null) {
      StaffBinding binding = (StaffBinding) holder.binding;
      Staff staff = list.get(position);
      binding.name.setText(staff.getName());
      binding.id.setText(staff.getIdentityId());
      binding.company.setText(staff.getCompany().getCompanyName());
      binding.getRoot().setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          Intent intent = new Intent(holder.binding.getRoot().getContext(), StaffActivity.class);
          intent.putExtra(StaffActivity.STAFF_ATTR, list.get(holder.getAdapterPosition()));
          holder.binding.getRoot().getContext().startActivity(intent);
        }
      });
    } else {
      holder.itemLoadingBinding.progressBar.setIndeterminate(true);
    }
  }
}
