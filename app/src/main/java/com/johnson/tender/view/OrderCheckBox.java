package com.johnson.tender.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.johnson.tender.R;
import com.johnson.tender.databinding.OrderCheckBoxBinding;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Johnson on 2017/5/6.
 */

public class OrderCheckBox extends LinearLayout {
  OrderCheckBoxBinding binding;
  AtomicReference<ORDER> reference;

  public OrderCheckBox(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);

    View view = null;
    setOrientation(LinearLayout.HORIZONTAL);
    LayoutInflater inflater = (LayoutInflater) context
        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    if (isInEditMode()) {
      view = inflater.inflate(R.layout.order_check_box, this, true);
    } else {
      binding = OrderCheckBoxBinding.inflate(inflater, this, true);
    }

    TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.OrderCheckBox);
    String title = typedArray.getString(R.styleable.OrderCheckBox_text);
    if (isInEditMode()) {
      ((TextView) view.findViewById(R.id.title)).setText(title);
    } else {
      binding.title.setText(title);
    }
    typedArray.recycle();

    binding.descending.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        if (reference != null) {
          reference.set(ORDER.DESCENDING);
        }
      }
    });
    binding.ascending.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        if (reference != null) {
          reference.set(ORDER.ASCENDING);
        }
      }
    });
    binding.none.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        if (reference != null) {
          reference.set(ORDER.NONE);
        }
      }
    });
  }

  public void setReference(AtomicReference<ORDER> reference) {
    this.reference = reference;
  }

  public void setText(@StringRes int resid) {
    binding.title.setText(resid);
  }

  public void setText(String text) {
    binding.title.setText(text);
  }

  public ORDER getOrder() {
    if (binding.none.isChecked()) {
      return ORDER.NONE;
    }
    if (binding.ascending.isChecked()) {
      return ORDER.ASCENDING;
    }
    if (binding.descending.isChecked()) {
      return ORDER.DESCENDING;
    }
    return ORDER.NONE;
  }

  public enum ORDER {
    NONE, ASCENDING, DESCENDING
  }
}
