package com.example.nakam.othello_android.game.othello;

import android.app.AlertDialog;
import android.content.DialogInterface;

class ResultDialog {

	/** 勝敗結果を通知するダイアログ */
	AlertDialog.Builder notificationOfResult;

	ResultDialog(GameScreen gameScreenActivity)
	{
		AlertDialog.Builder notificationOfResult = new AlertDialog.Builder(gameScreenActivity);
		notificationOfResult.setTitle("勝敗結果");
		notificationOfResult.setPositiveButton("O.K.",
	                new DialogInterface.OnClickListener() {
	                    @Override
	                    public void onClick(DialogInterface dialog, int which) {}
	                });
		this.notificationOfResult = notificationOfResult;
	}
}