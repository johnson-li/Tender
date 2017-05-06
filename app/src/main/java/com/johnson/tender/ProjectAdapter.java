package com.johnson.tender;

import android.content.Intent;
import android.view.View;

import com.johnson.tender.databinding.ProjectBinding;
import com.johnson.tender.entity.Project;

/**
 * Created by Johnson on 2017/5/2.
 */

public class ProjectAdapter extends AbstractAdapter<ProjectBinding, Project> {
  @Override
  int getLayoutId() {
    return R.layout.project;
  }

  @Override
  public void onBindViewHolder(final AbstractAdapter.ViewHolder holder, int position) {
    if (holder.binding != null) {
      ProjectBinding binding = (ProjectBinding) holder.binding;
      binding.name.setText(list.get(position).getName());
      binding.getRoot().setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          Intent intent = new Intent(holder.binding.getRoot().getContext(), ProjectActivity.class);
          intent.putExtra(ProjectActivity.PROJECT_ATTR, list.get(holder.getAdapterPosition()));
          holder.binding.getRoot().getContext().startActivity(intent);
        }
      });
    } else {
      holder.itemLoadingBinding.progressBar.setIndeterminate(true);
    }
  }
}
