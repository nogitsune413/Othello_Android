package com.example.nakam.othello_android.game.othello;

import android.widget.TextView;

import java.util.Locale;

import game.othello.R;

class DiscCountView {

	/** 黒石の表示 */
	private TextView blackDiscCountView;

	/** 白石の表示 */
	private TextView whiteDiscCountView;

	DiscCountView(GameScreen gameScreenActivity)
	{
		blackDiscCountView = (TextView)gameScreenActivity.findViewById(R.id.blackCount);
		whiteDiscCountView = (TextView)gameScreenActivity.findViewById(R.id.whiteCount);
	}

	/** オセロ盤の画面上部にある黒石の数と白石の数を更新する。
	 *
	 * @param black 黒石の数
	 * @param white 白石の数
	 */
	void update(int black, int white)
	{
		blackDiscCountView.setText(String.format(Locale.JAPAN,"黒石の数：%1$2d",black));
		whiteDiscCountView.setText(String.format(Locale.JAPAN,"白石の数：%1$2d",white));
	}
}