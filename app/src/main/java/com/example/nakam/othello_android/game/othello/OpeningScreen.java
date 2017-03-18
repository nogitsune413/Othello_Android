package com.example.nakam.othello_android.game.othello;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import game.othello.R;

public class OpeningScreen extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_opening_screen);

		// スタートボタンの登録
		Button startButton = (Button) findViewById(R.id.StartBtn);
		startButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v)
			{
				// ゲーム画面に遷移
				startActivity(new Intent(getApplicationContext(),GameScreen.class));
			}
		});

		// 設定ボタンの登録
		Button settingButton = (Button) findViewById(R.id.SettingBtn);
		settingButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v)
			{
				// 設定画面に遷移
				startActivity(new Intent(getApplicationContext(),SettingScreen.class));
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.opening_screen, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		return (id == R.id.action_settings) || super.onOptionsItemSelected(item);
	}
}