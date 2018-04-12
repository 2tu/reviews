package com.tu.review;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.os.Bundle;

/**
 * @author Tu enum@foxmail.com.
 */

public class LoadingFragment extends DialogFragment {
  public static final String FRAGMENT_TAG = "LoadingFragment";
  public static LoadingFragment newInstance() {
    return new LoadingFragment();
  }

  @Override public Dialog onCreateDialog(Bundle savedInstanceState) {
    final ProgressDialog dialog = new ProgressDialog(getActivity());
    dialog.setIndeterminate(true);
    dialog.setCancelable(false);
    dialog.setIndeterminate(true);
    dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    return dialog;
  }
}
