package com.johnson.tender;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.johnson.tender.entity.Response;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Johnson on 2017/5/3.
 */

public class BaseActivity extends AppCompatActivity {

  <T extends Response> void subscribe(Observable<T> observable, final Consumer<T> consumer, final ProgressDialog dialog) {
    observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<T>() {
          @Override
          public void accept(@NonNull T t) throws Exception {
            if (dialog != null) {
              dialog.dismiss();
            }
            if (!t.isSuccessful()) {
              Toast.makeText(getApplicationContext(), "Error: " + t.error(), Toast.LENGTH_SHORT).show();
            } else {
              if (consumer != null) {
                consumer.accept(t);
              }
            }
          }
        }, new Consumer<Throwable>() {
          @Override
          public void accept(@NonNull Throwable t) throws Exception {
            if (dialog != null) {
              dialog.dismiss();
            }
            t.printStackTrace();
            Toast.makeText(getApplicationContext(), "On Error: " + t.getLocalizedMessage(),
                Toast.LENGTH_LONG).show();
          }
        });
  }
}
