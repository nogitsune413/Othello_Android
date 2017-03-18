package com.example.nakam.othello_android.game.othello;

import android.preference.PreferenceManager;

class SettingReader {

	private GameScreen gameScreenActivity;

	SettingReader(GameScreen gameScreenActiviry)
	{
		this.gameScreenActivity = gameScreenActiviry;
	}

	/** サジェスト機能が有効の時、TRUEを返す。
	 *
	 * @return サジェスト機能が有効の時、TRUE
	 */
	boolean SuggestFeatureIsEnabled()
	{
		return PreferenceManager
				.getDefaultSharedPreferences(gameScreenActivity.getApplicationContext())
				.getBoolean("suggest_checkbox", false);
	}
}
