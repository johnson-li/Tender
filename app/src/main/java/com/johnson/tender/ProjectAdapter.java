package com.johnson.tender;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.johnson.tender.databinding.ProjectBinding;
import com.johnson.tender.entity.Project;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Johnson on 2017/5/2.
 */

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ViewHolder> {
  List<Project> projectList = new ArrayList<>();

  public void add(Project project) {
    projectList.add(project);
    notifyDataSetChanged();
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    ProjectBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.project, parent, false);
    ViewHolder viewHolder = new ViewHolder(binding);
    return viewHolder;
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, final int position) {
    holder.binding.name.setText(projectList.get(position).getName());
    holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(v.getContext(), ProjectActivity.class);
        intent.putExtra(ProjectActivity.PROJECT_ID_ATTR, projectList.get(position).getId());
        v.getContext().startActivity(intent);
      }
    });
  }

  @Override
  public int getItemCount() {
    return projectList.size();
  }

  class ViewHolder extends RecyclerView.ViewHolder {
    ProjectBinding binding;

    public ViewHolder(ProjectBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
    }
  }
}
