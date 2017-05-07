package com.johnson.tender;

import android.databinding.DataBindingUtil;
import android.support.annotation.StringRes;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.johnson.tender.databinding.AttributeBinding;
import com.johnson.tender.entity.Company;
import com.johnson.tender.entity.Project;
import com.johnson.tender.entity.Staff;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Johnson on 2017/5/2.
 */

public class AttributesAdapter extends RecyclerView.Adapter<AttributesAdapter.ViewHolder> {

  private List<Pair<Integer, String>> attributePairs = new ArrayList<>();

  public void add(Object object) {
    if (object instanceof Company) {
      add((Company) object);
    } else if (object instanceof Staff) {
      add((Staff) object);
    } else if (object instanceof Project) {
      add((Project) object);
    } else {
      throw new RuntimeException("Not supported class: " + object.getClass());
    }
  }

  public void add(Staff staff) {
    add(R.string.staff_detail_id, staff.getId());
    add(R.string.staff_detail_name, staff.getName());
    add(R.string.staff_detail_identification, staff.getIdentityId());
    add(R.string.staff_detail_gender, staff.getGender());
    add(R.string.staff_detail_type, staff.getRegisterType());
  }

  public void add(Project project) {
    add(R.string.project_detail_id, project.getId());
    add(R.string.project_detail_name, project.getName());
    add(R.string.project_detail_location, project.getLocation());
  }

  public void add(Company company) {
    add(R.string.company_detail_id, company.getId());
    add(R.string.company_detail_name, company.getCompanyName());
    add(R.string.company_detail_code, company.getCompanyCode());
  }

  public void add(@StringRes int id, long val) {
    add(id, String.valueOf(val));
  }

  public void add(@StringRes int id, String val) {
    attributePairs.add(new Pair<>(id, val));
    notifyDataSetChanged();
  }

  @Override
  public AttributesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    AttributeBinding binding = DataBindingUtil
        .inflate(LayoutInflater.from(parent.getContext()), R.layout.attribute, parent, false);
    return new ViewHolder(binding);
  }

  @Override
  public void onBindViewHolder(AttributesAdapter.ViewHolder holder, int position) {
    if (position % 2 == 0) {
      holder.binding.text.setText(attributePairs.get(position / 2).first);
    } else {
      holder.binding.text.setText(attributePairs.get(position / 2).second);
    }
  }

  @Override
  public int getItemCount() {
    return attributePairs.size() * 2;
  }

  class ViewHolder extends RecyclerView.ViewHolder {
    AttributeBinding binding;

    public ViewHolder(AttributeBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
    }
  }
}
