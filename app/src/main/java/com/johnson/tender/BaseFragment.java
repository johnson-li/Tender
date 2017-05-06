package com.johnson.tender;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.johnson.tender.entity.Response;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Johnson on 2017/5/3.
 */

public class BaseFragment extends Fragment {

  <T extends Response> void subscribe(Observable<T> observable, final Consumer<T> consumer, final ProgressDialog dialog) {
    subscribe(observable, consumer, dialog, null);
  }

  <T extends Response> void subscribe(Observable<T> observable, final Consumer<T> consumer, final ProgressDialog dialog, final Action finalAction) {
    observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<T>() {
          @Override
          public void accept(@NonNull T t) throws Exception {
            if (dialog != null) {
              dialog.dismiss();
            }
            if (!t.isSuccessful()) {
              Toast.makeText(getContext(), "Error: " + t.error(), Toast.LENGTH_SHORT).show();
            } else {
              consumer.accept(t);
            }
            if (finalAction != null) {
              finalAction.run();
            }
          }
        }, new Consumer<Throwable>() {
          @Override
          public void accept(@NonNull Throwable t) throws Exception {
            if (dialog != null) {
              dialog.dismiss();
            }
            t.printStackTrace();
            Toast.makeText(getContext(), "On Error: " + t.getLocalizedMessage(),
                Toast.LENGTH_LONG).show();
            if (finalAction != null) {
              finalAction.run();
            }
          }
        });
  }
}
