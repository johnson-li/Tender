package com.johnson.tender;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.johnson.tender.databinding.ItemLoadingBinding;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Johnson on 2017/5/5.
 */

public abstract class AbstractAdapter<T extends ViewDataBinding, M> extends RecyclerView.Adapter<AbstractAdapter.ViewHolder> {
  private final int VIEW_TYPE_ITEM = 0;
  private final int VIEW_TYPE_LOADING = 1;
  List<M> list = new ArrayList<>();

  abstract int getLayoutId();

  public void removeLast() {
    assert list.get(list.size() - 1) == null;
    list.remove(list.size() - 1);
    notifyDataSetChanged();
  }

  public void clear() {
    list.clear();
    notifyDataSetChanged();
  }

  public void add(M m) {
    list.add(m);
    notifyDataSetChanged();
  }


  public int getSize() {
    return list.size();
  }

  @Override
  public int getItemViewType(int position) {
    return list.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    switch (viewType) {
      case VIEW_TYPE_ITEM:
        T binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), getLayoutId(), parent, false);
        return new ViewHolder(binding);
      case VIEW_TYPE_LOADING:
        ItemLoadingBinding itemLoadingBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_loading, parent, false);
        return new ViewHolder(itemLoadingBinding);
    }
    return null;
  }

  @Override
  public int getItemCount() {
    return list.size();
  }

  class ViewHolder extends RecyclerView.ViewHolder {
    T binding;
    ItemLoadingBinding itemLoadingBinding;

    public ViewHolder(ItemLoadingBinding itemLoadingBinding) {
      super(itemLoadingBinding.getRoot());
      this.itemLoadingBinding = itemLoadingBinding;
    }

    public ViewHolder(T binding) {
      super(binding.getRoot());
      this.binding = binding;
    }
  }
}
