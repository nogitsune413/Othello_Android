package com.example.nakam.othello_android.game.othello;

import android.util.SparseArray;
import android.view.View;
import android.widget.Button;

import game.othello.R;


class BoardStateCtl {

	// 盤の履歴
	private History history = new History();

	// 「進む」ボタン
	private Button redoButton;

	// 「戻る」ボタン
	private Button undoButton;

	// コンストラクタ
	BoardStateCtl(Game game)
	{
		initializeRedoButton(game); // Redoボタンを初期化する。
		initializeUndoButton(game); // Undoボタンを初期化する。
	}

	/** Redoボタンを初期化する。
	 *
	 * @param game  盤や手番の状態を把握し、UIを更新するためのGameクラス
	 */
	private void initializeRedoButton(final Game game)
	{
		// Redoボタンのオブジェクトを取得。
		redoButton = ((Button)game.gameScreenActivity.findViewById(R.id.redoBtn));

		// Redoボタンのリスナーに「盤の状態をひとつ進める」メソッドを登録
		redoButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// 盤面の状態をひとつ進める。
				history.next(game.board.getAllSquares(),game.turn);

				// 画面上の他のコンポーネントの状態を更新。
				refreshUiComponents(game);
			}
		});
	}

	/** Undoボタンを初期化する。
	 *
	 * @param game  盤や手番の状態を把握し、UIを更新するためのGameクラス
	 */
	private void initializeUndoButton(final Game game)
	{
		// Undoボタンのオブジェクトを取得。
		undoButton = ((Button)game.gameScreenActivity.findViewById(R.id.undoBtn));

		// Undoボタンのリスナーに「盤の状態をひとつ戻す」メソッドを登録
		undoButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// 盤面の状態をひとつ戻す。
				history.previous(game.board.getAllSquares(),game.turn);

				// 画面上の他のコンポーネントの状態を更新。
				refreshUiComponents(game);
			}
		});
	}

	/** ボタンの状態を更新する。
	 *
	 *  盤が最新の時はREDOボタンを非活性に、盤が初期配置の時はUNDOボタンを非活性にする。
	 */
	private void updateButtonState()
	{
		// REDOボタンを更新。盤が最新の時はボタンを無効にする。
		redoButton.setEnabled(!history.isEnd());

		// UNDOボタンを更新。盤が初期配置の時はボタンを無効にする。
		undoButton.setEnabled(!history.isTop());
	}

	/** 新しい盤面を履歴に追加する。
	 *
	 * @param squares 追加する盤面
	 * @param turn    その盤面で打つ権利のある手番
	 */
	void addNewStateToHistory(SparseArray<Square> squares, Turn turn)
	{
		// 新しい盤面をリストに保存。
		history.store(squares, turn);

		// 「戻る」「進む」ボタンの状態を更新。
		updateButtonState();
	}

	/** 画面を最新化する。
	 *
	 * @param game UIとGame状態にアクセスするためのGameクラス
	 */
	private void refreshUiComponents(Game game)
	{
		// サジェスト機能が有効の時、石が置ける空きマスの画像をサジェスト用の画像に差し替える。
		if(game.settingReader.SuggestFeatureIsEnabled())
		{
			Suggest.set(game.turn.getCurrentTurn());
		}

		// 石の数の表示を更新する。
		game.discCountView.update(game.board.count(Disc.BLACK), game.board.count(Disc.WHITE));

		// 「進む」「戻る」ボタンの活性、非活性を更新。
		updateButtonState();
	}
}