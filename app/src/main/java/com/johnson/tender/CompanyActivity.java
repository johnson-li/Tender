package com.johnson.tender;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.view.MenuItem;

import com.johnson.tender.databinding.ActivityCompanyBinding;
import com.johnson.tender.entity.Company;

/**
 * Created by Johnson on 2017/5/2.
 */

public class CompanyActivity extends AppCompatActivity {
  public static final String COMPANY_ID_ATTR = "company id";
  public static final String COMPANY_ATTR = "company";
  ActivityCompanyBinding binding;
  AttributesAdapter adapter = new AttributesAdapter();

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = DataBindingUtil.setContentView(this, R.layout.activity_company);

    setSupportActionBar(binding.toolbar);
    getSupportActionBar().setTitle(R.string.company_detail_title);
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

    if (getIntent().hasExtra(COMPANY_ATTR)) {
      Company company = (Company) getIntent().getSerializableExtra(COMPANY_ATTR);
      adapter.add(company);
    } else if (getIntent().hasExtra(COMPANY_ID_ATTR)) {
      adapter.add("1", "2");
      adapter.add("3", "44444444444444444444444444444444444444444444444444444444");
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
