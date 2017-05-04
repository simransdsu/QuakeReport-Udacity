package com.example.android.quakereport.Utils;

import android.app.ProgressDialog;
import android.content.Context;

public class ProgressSpinner
{
    private ProgressDialog mProgressDialog;

    public ProgressSpinner(Context context)
    {
        mProgressDialog = new ProgressDialog(context);
    }

    public void showProgressDialog()
    {
        mProgressDialog.setProgress(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setTitle("Loading");
        mProgressDialog.setMessage("Please wait...");
        mProgressDialog.setCancelable(false);
        if (mProgressDialog != null)
            mProgressDialog.show();
    }

    public void dismissProgressDialog()
    {
        if (mProgressDialog.isShowing())
        {
            mProgressDialog.dismiss();
        }
    }
}