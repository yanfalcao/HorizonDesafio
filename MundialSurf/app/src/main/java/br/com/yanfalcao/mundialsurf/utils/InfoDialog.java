package br.com.yanfalcao.mundialsurf.utils;

import android.content.Context;

import com.yarolegovich.lovelydialog.LovelyStandardDialog;

import br.com.yanfalcao.mundialsurf.R;

public class InfoDialog {
    public static void show(Context context, String message){
        new LovelyStandardDialog(context, LovelyStandardDialog.ButtonLayout.VERTICAL)
                .setTopColorRes(R.color.infoColor)
                .setButtonsColorRes(R.color.colorLightBlue)
                .setIcon(R.drawable.ic_alert)
                .setMessage(message)
                .configureMessageView(messageView -> messageView.setTextSize(17))
                .setPositiveButton(R.string.ok, null)
                .show();
    }
}
