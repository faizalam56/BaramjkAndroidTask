package com.example.baramjktask;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

public class Globals {

    public Dialog createDialog(Context ActivityContext, String progress) {
        ProgressDialog dialog = new ProgressDialog(ActivityContext);
        dialog.setMessage(progress);
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.show();
        return dialog;

    }

    AlertDialog alertDialog;
    public void showAlertDialog(String message,Context ActivityContext) {
        final AlertDialog.Builder myDialog = new AlertDialog.Builder(
                ActivityContext);
        final TextView title = new TextView(ActivityContext);
        title.setText("Alert");
        title.setPadding(10, 10, 10, 10);
        title.setGravity(Gravity.CENTER);
        title.setTextColor(Color.parseColor("#4182ac"));
        title.setTextSize(25);
        myDialog.setCustomTitle(title);
        myDialog.setMessage(message);
        myDialog.setCancelable(false);

        myDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                {

                }
            }
        });
        alertDialog = myDialog.show();

    }

    public void dismissDialog(){
        if (alertDialog!=null)alertDialog.dismiss();
    }



    //****** Check Network Connectivity***********************

    public boolean isNetworkConnected(Activity activity) {
        ConnectivityManager cm =
                (ConnectivityManager)activity.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }

    public void showSnakeBarMessage(View view,Activity activity){
        Snackbar snackbar = Snackbar.make(view,"Network unavailable,please check connection.", Snackbar.LENGTH_LONG);
        View snackView = snackbar.getView();
        snackView.setBackgroundColor(activity.getResources().getColor(R.color.green));
        snackbar.show();
    }

    public void showSnakeBarNotificationError(View view,Activity activity){
        Snackbar snackbar = Snackbar.make(view,"First select filter selection.", Snackbar.LENGTH_LONG);
        View snackView = snackbar.getView();
        snackView.setBackgroundColor(activity.getResources().getColor(R.color.green));
        snackbar.show();
    }

    public void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }
}
