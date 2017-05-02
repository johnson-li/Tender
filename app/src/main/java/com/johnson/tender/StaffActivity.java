package com.johnson.tender;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.view.MenuItem;

import com.johnson.tender.databinding.ActivityStaffBinding;

/**
 * Created by Johnson on 2017/5/2.
 */

public class StaffActivity extends AppCompatActivity {
  public static final String STAFF_ID_ATTR = "staff id";
  ActivityStaffBinding binding;
  AttributesAdapter adapter = new AttributesAdapter();

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = DataBindingUtil.setContentView(this, R.layout.activity_staff);

    setSupportActionBar(binding.toolbar);
    getSupportActionBar().setTitle(R.string.staff_detail_title);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setDisplayShowHomeEnabled(true);

    binding.attributes.setHasFixedSize(true);
    GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
    layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
      @Override
      public int getSpanSize(int position) {
        return 1;
      }
    });
    binding.attributes.setLayoutManager(layoutManager);
    binding.attributes.setAdapter(adapter);

    for (int i = 0; i < 10; i++) {
      adapter.add("key" + i, "val" + i);
    }
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        finish();
    }
    return super.onOptionsItemSelected(item);
  }
}
