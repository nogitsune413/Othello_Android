package com.example.nakam.othello_android.game.othello;

enum Result {
	BLACK,
	WHITE,
	DRAW;

	public void show(ResultDialog resultDialog)
	{
		switch(this)
		{
			case BLACK :
			{
				resultDialog.notificationOfResult.setMessage("黒番の勝ちです。");
				break;
			}
			case WHITE :
			{
				resultDialog.notificationOfResult.setMessage("白番の勝ちです。");
				break;
			}
			case DRAW :
			{
				resultDialog.notificationOfResult.setMessage("引き分けです。");
				break;
			}
		}
		resultDialog.notificationOfResult.create().show();
	}
}