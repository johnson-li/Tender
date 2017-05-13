package com.johnson.tender;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Paint;
import android.support.annotation.StringRes;
import android.support.v4.util.Pair;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.johnson.tender.databinding.AttributeBinding;
import com.johnson.tender.entity.Company;
import com.johnson.tender.entity.CompanyCert;
import com.johnson.tender.entity.Project;
import com.johnson.tender.entity.Staff;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Johnson on 2017/5/2.
 */

public class AttributesAdapter extends RecyclerView.Adapter<AttributesAdapter.ViewHolder> {

  private List<Pair<Integer, Object>> attributePairs = new ArrayList<>();

  public void add(Object object) {
    if (object instanceof Company) {
      add((Company) object);
    } else if (object instanceof Staff) {
      add((Staff) object);
    } else if (object instanceof Project) {
      add((Project) object);
    } else if (object instanceof CompanyCert) {
      add((CompanyCert) object);
    } else {
      throw new RuntimeException("Not supported class: " + object.getClass());
    }
  }

  public void add(CompanyCert companyCert) {
    add(R.string.cert_detail_ID, companyCert.getId());
    add(R.string.cert_detail_name, companyCert.getName());
    add(R.string.cert_detail_type, companyCert.getType());
    add(R.string.cert_detail_cert_id, companyCert.getCertId());
    add(R.string.cert_detail_issue_date, companyCert.getIssueDate());
    add(R.string.cert_detail_expire_date, companyCert.getExpireDate());
    add(R.string.cert_detail_issuer, companyCert.getIssuer());
  }

  public void add(Staff staff) {
    add(R.string.staff_detail_id, staff.getId());
    add(R.string.staff_detail_name, staff.getName());
    add(R.string.staff_detail_identification, staff.getIdentityId());
    add(R.string.staff_detail_gender, staff.getGender());
    add(R.string.staff_detail_type, staff.getRegisterType());
    add(R.string.staff_detail_level, staff.getLevel());
    add(R.string.staff_detail_specialty, staff.getSpecialty());
    add(R.string.staff_detail_seal_id, staff.getSealId());
    add(R.string.staff_detail_seal_register_date, staff.getRegisterDate());
    add(R.string.staff_detail_expire_date, staff.getExpireDate());
    add(R.string.staff_detail_status, staff.getStatus());
    add(R.string.staff_detail_company_name, staff.getCompany());
  }

  public void add(Project project) {
    add(R.string.project_detail_id, project.getId());
    add(R.string.project_detail_name, project.getName());
    add(R.string.project_detail_location, project.getLocation());
    add(R.string.project_detail_customer_organization, project.getCustomerOrganization());
    add(R.string.project_detail_project_number, project.getProjectNumber());
    add(R.string.project_detail_type, project.getProjectType());
    add(R.string.project_detail_company, project.getCompany());
  }

  public void add(Company company) {
    add(R.string.company_detail_id, company.getId());
    add(R.string.company_detail_name, company.getCompanyName());
    add(R.string.company_detail_code, company.getCompanyCode());
    add(R.string.company_detail_register_location, company.getRegisterLocation());
    add(R.string.company_detail_operating_location, company.getOperatingLocation());
    add(R.string.company_detail_people, company.getLegalRepresentative());
    add(R.string.company_detail_type, company.getCompanyType());
    add(R.string.company_detail_capital, company.getRegisteredCapital());
    add(R.string.company_detail_cert_id, company.getCertId());
    add(R.string.company_detail_security_cert_id, company.getSecurityCertId());
    add(R.string.company_detail_cert_expire, company.getCertExpire());
    add(R.string.company_detail_certs, company.getCompanyCerts() == null || company.getCompanyCerts().isEmpty() ? null : company.getCompanyCerts().get(0));
    add(R.string.company_detail_staff, company.getStaffs());
  }

  public void add(@StringRes int id, long val) {
    add(id, String.valueOf(val));
  }

  public void add(@StringRes int id, Object val) {
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
  public void onBindViewHolder(final AttributesAdapter.ViewHolder holder, int position) {
    if (position % 2 == 0) {
      holder.binding.text.setText(attributePairs.get(position / 2).first);
    } else {
      Object object = attributePairs.get(position / 2).second;
      if (object == null) {
        //do nothing
      } else if (object instanceof String) {
        holder.binding.text.setText(object.toString());
      } else if (object instanceof CompanyCert) {
        final CompanyCert companyCert = (CompanyCert) object;
        holder.binding.text.setText(companyCert.getName());
        holder.binding.text.setPaintFlags(holder.binding.text.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        holder.binding.text.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            Intent intent = new Intent(holder.binding.getRoot().getContext(), CompanyCertActivity.class);
            intent.putExtra(CompanyCertActivity.COMPANY_CERT_ATTR, companyCert);
            holder.binding.getRoot().getContext().startActivity(intent);
          }
        });
      } else if (object instanceof Company) {
        final Company company = (Company) object;
        holder.binding.text.setText(company.getCompanyName());
        holder.binding.text.setPaintFlags(holder.binding.text.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        holder.binding.text.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            Intent intent = new Intent(holder.binding.getRoot().getContext(), CompanyActivity.class);
            intent.putExtra(CompanyActivity.COMPANY_ATTR, company);
            holder.binding.getRoot().getContext().startActivity(intent);
          }
        });
      } else if (object instanceof List) {
        List list = (List) object;
        if (!list.isEmpty()) {
          if (list.get(0) instanceof Staff) {
            final List<Staff> staffs = (List<Staff>) list;
            holder.binding.text.setText(holder.binding.getRoot().getContext().getString(R.string.list_1_more, staffs.get(0).getName()));
            holder.binding.text.setPaintFlags(holder.binding.text.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            holder.binding.text.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.binding.getRoot().getContext());
                builder.setTitle(R.string.dialog_staffs);
                final ArrayAdapter<String> adapter = new ArrayAdapter<>(holder.binding.getRoot().getContext(), R.layout.select_dialog_item);
                for (Staff staff : staffs) {
                  adapter.add(staff.getName());
                }
                builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                    Staff staff = staffs.get(which);
                    dialog.dismiss();
                    Intent intent = new Intent(holder.binding.getRoot().getContext(), StaffActivity.class);
                    intent.putExtra(StaffActivity.STAFF_ATTR, staff);
                    holder.binding.getRoot().getContext().startActivity(intent);
                  }
                });
                builder.show();
              }
            });
          }
        }
      } else {
        throw new RuntimeException("unsupported class: " + object.getClass());
      }
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
