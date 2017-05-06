package com.johnson.tender;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.johnson.tender.databinding.DragItemBinding;
import com.johnson.tender.view.DragItemAdapter;

import java.util.List;

/**
 * Created by Johnson on 2017/5/7.
 */

public class BoxDrapItemAdapter extends DragItemAdapter<String, BoxDrapItemAdapter.ViewHolder> {
  public BoxDrapItemAdapter(List<String> data) {
    setHasStableIds(true);
    setItemList(data);
  }

  @Override
  public long getItemId(int position) {
    return mItemList.get(position).hashCode();
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    DragItemBinding binding = DragItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
    return new ViewHolder(binding, R.id.box, false);
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    super.onBindViewHolder(holder, position);
    holder.binding.box.setText(mItemList.get(position));
  }

  class ViewHolder extends DragItemAdapter.ViewHolder {
    DragItemBinding binding;

    public ViewHolder(DragItemBinding binding, int handleResId, boolean dragOnLongPress) {
      super(binding.getRoot(), handleResId, dragOnLongPress);
      this.binding = binding;
    }
  }
}
