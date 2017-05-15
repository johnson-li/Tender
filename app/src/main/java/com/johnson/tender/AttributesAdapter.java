package com.johnson.tender;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Paint;
import android.support.annotation.StringRes;
import android.support.v4.util.Pair;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.johnson.tender.databinding.AttributeBinding;
import com.johnson.tender.entity.Company;
import com.johnson.tender.entity.CompanyCert;
import com.johnson.tender.entity.Project;
import com.johnson.tender.entity.Staff;
import com.johnson.tender.entity.StaffAchievement;
import com.johnson.tender.entity.StaffCert;

import java.io.Serializable;
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
    } else if (object instanceof StaffCert) {
      add((StaffCert) object);
    } else if (object instanceof StaffAchievement) {
      add((StaffAchievement) object);
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
    add(R.string.staff_detail_cert, staff.getStaffCerts());
    add(R.string.staff_detail_achievements, staff.getStaffAchievements());

    if (staff.getStaffCerts() != null) {
      for (StaffCert staffCert : staff.getStaffCerts()) {
        staffCert.setStaff(staff);
      }
    }
    if (staff.getStaffAchievements() != null) {
      for (StaffAchievement staffAchievement : staff.getStaffAchievements()) {
        staffAchievement.setStaff(staff);
      }
    }
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
    add(R.string.company_detail_projects, company.getProjects());

    if (company.getProjects() != null) {
      for (Project project : company.getProjects()) {
        project.setCompany(company);
      }
    }
  }

  public void add(StaffCert staffCert) {
    add(R.string.staff_cert_detail_staff, staffCert.getStaff().getName());
    add(R.string.staff_cert_detail_type, staffCert.getType());
    add(R.string.staff_detail_specialty, staffCert.getSpecialty());
    add(R.string.staff_cert_detail_organization, staffCert.getOrganizationName());
    add(R.string.staff_cert_detail_cert_id, staffCert.getCertId());
    add(R.string.staff_cert_detail_seal_id, staffCert.getSealId());
    add(R.string.staff_cert_detail_register_date, staffCert.getRegisterDate());
    add(R.string.staff_cert_detail_expire_date, staffCert.getExpireDate());
  }

  public void add(StaffAchievement staffAchievement) {
    add(R.string.staff_achi_detail_staff, staffAchievement.getStaff().getName());
    add(R.string.staff_achi_detail_project, staffAchievement.getProjectName());
    add(R.string.staff_achi_detail_role, staffAchievement.getRole());
    add(R.string.staff_achi_detail_customer, staffAchievement.getCustomerInstitute());
    add(R.string.staff_achi_detail_status, staffAchievement.getStatus());
    add(R.string.staff_achi_detail_category, staffAchievement.getCategory());
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
      } else if (object instanceof StaffCert) {
        final StaffCert staffCert = (StaffCert) object;
        holder.binding.text.setText(staffCert.getType());
        holder.binding.text.setPaintFlags(holder.binding.text.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        holder.binding.text.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            Intent intent = new Intent(holder.binding.getRoot().getContext(), StaffCertActivity.class);
            intent.putExtra(StaffCertActivity.STAFF_CERT_ATTR, staffCert);
            holder.binding.getRoot().getContext().startActivity(intent);
          }
        });
      } else if (object instanceof StaffAchievement) {
        final StaffAchievement staffAchievement = (StaffAchievement) object;
        holder.binding.text.setText(staffAchievement.getProjectName());
        holder.binding.text.setPaintFlags(holder.binding.text.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        holder.binding.text.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            Intent intent = new Intent(holder.binding.getRoot().getContext(), StaffAchievementActivity.class);
            intent.putExtra(StaffAchievementActivity.STAFF_ACHIEVEMENT_ATTR, staffAchievement);
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
          } else if (list.get(0) instanceof Project) {
            final List<Project> projects = (List<Project>) list;
            holder.binding.text.setText(holder.binding.getRoot().getContext().getString(R.string.list_1_more, projects.get(0).getName()));
            holder.binding.text.setPaintFlags(holder.binding.text.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            holder.binding.text.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.binding.getRoot().getContext());
                builder.setTitle(R.string.dialog_projects);
                final ArrayAdapter<String> adapter = new ArrayAdapter<>(holder.binding.getRoot().getContext(), R.layout.select_dialog_item);
                for (Project project : projects) {
                  adapter.add(project.getName());
                }
                builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                    Project project = projects.get(which);
                    dialog.dismiss();
                    Intent intent = new Intent(holder.binding.getRoot().getContext(), ProjectActivity.class);
                    intent.putExtra(ProjectActivity.PROJECT_ATTR, project);
                    holder.binding.getRoot().getContext().startActivity(intent);
                  }
                });
                builder.show();
              }
            });
          } else if (list.get(0) instanceof StaffCert) {
            initListItem(list, holder, StaffCertActivity.STAFF_CERT_ATTR, StaffCertActivity.class, R.string.staff_cert_detail_title);
          } else if (list.get(0) instanceof StaffAchievement) {
            initListItem(list, holder, StaffAchievementActivity.STAFF_ACHIEVEMENT_ATTR, StaffAchievementActivity.class, R.string.staff_achievement_detail_title);
          }
        }
      } else {
        throw new RuntimeException("unsupported class: " + object.getClass());
      }
    }
  }

  private String getName(Object object) {
    if (object instanceof Project) {
      return ((Project) object).getName();
    } else if (object instanceof StaffCert) {
      return ((StaffCert) object).getType();
    } else if (object instanceof StaffAchievement) {
      return ((StaffAchievement) object).getProjectName();
    }
    return "";
  }

  private <T extends Serializable> void initListItem(final List<T> list,
                                                     final AttributesAdapter.ViewHolder holder,
                                                     final String attr, final Class clazz,
                                                     final @StringRes int id) {
    holder.binding.text.setText(holder.binding.getRoot().getContext().getString(R.string.list_1_more, getName(list.get(0))));
    holder.binding.text.setPaintFlags(holder.binding.text.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    holder.binding.text.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(holder.binding.getRoot().getContext());
        builder.setTitle(id);
        final ArrayAdapter<Spanned> adapter = new ArrayAdapter<>(holder.binding.getRoot().getContext(), R.layout.select_dialog_item);
        for (T t : list) {
          adapter.add(Html.fromHtml(String.format("<u>%s</u>", getName(t))));
        }
        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            T t = list.get(which);
            dialog.dismiss();
            Intent intent = new Intent(holder.binding.getRoot().getContext(), clazz);
            intent.putExtra(attr, t);
            holder.binding.getRoot().getContext().startActivity(intent);
          }
        });
        builder.show();
      }
    });
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
