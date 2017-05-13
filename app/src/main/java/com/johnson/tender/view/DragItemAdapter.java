package com.johnson.tender.view;

import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public abstract class DragItemAdapter<T, VH extends DragItemAdapter.ViewHolder> extends RecyclerView.Adapter<VH> {

  protected List<Pair<T, AtomicReference<OrderCheckBox.ORDER>>> mItemList;
  private DragStartCallback mDragStartCallback;
  private long mDragItemId = RecyclerView.NO_ID;
  private long mDropTargetId = RecyclerView.NO_ID;

  public List<Pair<T, OrderCheckBox.ORDER>> getItemListWithOrder() {
    List<Pair<T, OrderCheckBox.ORDER>> list = new ArrayList<>();
    for (Pair<T, AtomicReference<OrderCheckBox.ORDER>> pair : mItemList) {
      list.add(new Pair<>(pair.first, pair.second.get()));
    }
    return list;
  }

  public void setItemList(List<T> itemList) {
    List<Pair<T, AtomicReference<OrderCheckBox.ORDER>>> list = new ArrayList<>();
    for (T t : itemList) {
      list.add(new Pair<>(t, new AtomicReference<>(OrderCheckBox.ORDER.NONE)));
    }
    mItemList = list;
    notifyDataSetChanged();
  }

  public int getPositionForItem(T item) {
    int count = getItemCount();
    for (int i = 0; i < count; i++) {
      if (mItemList.get(i) == item) {
        return i;
      }
    }
    return RecyclerView.NO_POSITION;
  }

  public Object removeItem(int pos) {
    if (mItemList != null && mItemList.size() > pos && pos >= 0) {
      Object item = mItemList.remove(pos);
      notifyItemRemoved(pos);
      return item;
    }
    return null;
  }

  public void addItem(int pos, T item) {
    if (mItemList != null && mItemList.size() >= pos) {
      mItemList.add(pos, new Pair<>(item, new AtomicReference<>(OrderCheckBox.ORDER.NONE)));
      notifyItemInserted(pos);
    }
  }

  public void changeItemPosition(int fromPos, int toPos) {
    if (mItemList != null && mItemList.size() > fromPos && mItemList.size() > toPos) {
      Pair<T, AtomicReference<OrderCheckBox.ORDER>> item = mItemList.remove(fromPos);
      mItemList.add(toPos, item);
      notifyItemMoved(fromPos, toPos);
    }
  }

  public void swapItems(int pos1, int pos2) {
    if (mItemList != null && mItemList.size() > pos1 && mItemList.size() > pos2) {
      Collections.swap(mItemList, pos1, pos2);
      notifyDataSetChanged();
    }
  }

  public int getPositionForItemId(long id) {
    int count = getItemCount();
    for (int i = 0; i < count; i++) {
      if (id == getItemId(i)) {
        return i;
      }
    }
    return RecyclerView.NO_POSITION;
  }

  @Override
  public int getItemCount() {
    return mItemList == null ? 0 : mItemList.size();
  }

  @Override
  public void onBindViewHolder(final VH holder, int position) {
    long itemId = getItemId(position);
    holder.mItemId = itemId;
    holder.itemView.setVisibility(mDragItemId == itemId ? View.INVISIBLE : View.VISIBLE);
    holder.setDragStartCallback(mDragStartCallback);
    final OrderCheckBox box = (OrderCheckBox) holder.mGrabView;
    box.setReference(mItemList.get(position).second);
  }

  @Override
  public void onViewRecycled(VH holder) {
    super.onViewRecycled(holder);
    holder.setDragStartCallback(null);
  }

  void setDragStartedListener(DragStartCallback dragStartedListener) {
    mDragStartCallback = dragStartedListener;
  }

  void setDragItemId(long dragItemId) {
    mDragItemId = dragItemId;
  }

  public long getDropTargetId() {
    return mDropTargetId;
  }

  void setDropTargetId(long dropTargetId) {
    mDropTargetId = dropTargetId;
  }

  interface DragStartCallback {
    boolean startDrag(View itemView, long itemId);

    boolean isDragging();
  }

  public static abstract class ViewHolder extends RecyclerView.ViewHolder {
    public View mGrabView;
    public long mItemId;

    private DragStartCallback mDragStartCallback;

    public ViewHolder(final View itemView, int handleResId, boolean dragOnLongPress) {
      super(itemView);
      mGrabView = itemView.findViewById(handleResId);

      if (dragOnLongPress) {
        mGrabView.setOnLongClickListener(new View.OnLongClickListener() {
          @Override
          public boolean onLongClick(View view) {
            if (mDragStartCallback == null) {
              return false;
            }

            if (mDragStartCallback.startDrag(itemView, mItemId)) {
              return true;
            }

            if (itemView == mGrabView) {
              return onItemLongClicked(view);
            }
            return false;
          }
        });
      } else {
        mGrabView.setOnTouchListener(new View.OnTouchListener() {
          @Override
          public boolean onTouch(View view, MotionEvent event) {
            if (mDragStartCallback == null) {
              return false;
            }

            if (event.getAction() == MotionEvent.ACTION_DOWN && mDragStartCallback.startDrag(itemView, mItemId)) {
              return true;
            }

            if (!mDragStartCallback.isDragging() && itemView == mGrabView) {
              return onItemTouch(view, event);
            }
            return false;
          }
        });
      }

      itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          onItemClicked(view);
        }
      });

      if (itemView != mGrabView) {
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
          @Override
          public boolean onLongClick(View view) {
            return onItemLongClicked(view);
          }
        });
        itemView.setOnTouchListener(new View.OnTouchListener() {
          @Override
          public boolean onTouch(View view, MotionEvent event) {
            return onItemTouch(view, event);
          }
        });
      }
    }

    public void setDragStartCallback(DragStartCallback dragStartedListener) {
      mDragStartCallback = dragStartedListener;
    }

    public void onItemClicked(View view) {
    }

    public boolean onItemLongClicked(View view) {
      return false;
    }

    public boolean onItemTouch(View view, MotionEvent event) {
      return false;
    }
  }
}