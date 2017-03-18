package com.example.nakam.othello_android.game.othello;

import android.app.AlertDialog;
import android.content.DialogInterface;

class PassDialog {

	private AlertDialog.Builder notificationOfPass;

	PassDialog(GameScreen gameScreenActivity)
	{
		AlertDialog.Builder notificationOfPass = new AlertDialog.Builder(gameScreenActivity);
		notificationOfPass.setTitle("パス");
		notificationOfPass.setPositiveButton("O.K.",
	                new DialogInterface.OnClickListener() {
	                    @Override
	                    public void onClick(DialogInterface dialog, int which) {}
	                });
		notificationOfPass.setMessage("置ける場所がないため、パスします。");
		this.notificationOfPass = notificationOfPass;
	}

	void show()
	{
		notificationOfPass.create().show();
	}
}