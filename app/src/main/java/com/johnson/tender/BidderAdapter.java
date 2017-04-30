package com.johnson.tender;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.johnson.tender.databinding.BidderBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Johnson on 2017/4/30.
 */

public class BidderAdapter extends RecyclerView.Adapter<BidderAdapter.ViewHolder> {

  List<Bidder> bidders = new ArrayList<>();

  public void add(Bidder bidder) {
    bidders.add(bidder);
    notifyDataSetChanged();
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    BidderBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.bidder, parent, false);
    ViewHolder viewHolder = new ViewHolder(binding);
    return viewHolder;
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {

  }

  @Override
  public int getItemCount() {
    return bidders.size();
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {
    BidderBinding binding;

    public ViewHolder(BidderBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
    }
  }
}
