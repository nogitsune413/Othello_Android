package com.example.nakam.othello_android.game.othello;

public class Game {

	private static Game instance = new Game();
	public ManageTurn turn;
	public Board board = new Board();

	GameScreen gameScreenActivity;

	// ユーザ通知ダイアログ
	ResultDialog resultDialog; // 勝敗結果の通知
	PassDialog passDialog;     // パスの通知

	/** 設定情報 */
	SettingReader settingReader;

	/** 石の数を表示するTextView */
	DiscCountView discCountView;

	/** 盤の履歴と「戻る」「進む」ボタン */
	BoardStateCtl boardStateCtl;

	/** コンストラクタ
	 * シングルトン・オブジェクトにするため隠蔽する。
	 */
	private Game(){}

	/** Gameクラスのインスタンスを取得。
	 *  Gameクラスのインスタンスはシングルトン・オブジェクトである。
	 *
	 * @return Gameクラスのインスタンス
	 */
	static Game getInstance(){
		return instance;
	}

	/** Gameクラスを初期化する。
	 *
	 * @param gameScreenActivity 盤の画面のActivity
	 */
	void initialize(GameScreen gameScreenActivity)
	{
		this.gameScreenActivity = gameScreenActivity;          // オセロ盤の画面のアクティビティを保持する。
		settingReader = new SettingReader(gameScreenActivity); // 設定情報
		discCountView = new DiscCountView(gameScreenActivity); // オセロ盤画面の上部で石の数を表示するTextView。

		turn = new ManageTurn();                               // 手番を管理する。初手は黒番。

		// ユーザ通知のためのダイアログを作成
		resultDialog = new ResultDialog(gameScreenActivity);   // 勝敗通知
		passDialog = new PassDialog(gameScreenActivity);       // パス通知

		// 盤の履歴と「戻る」「進む」ボタン
		boardStateCtl = new BoardStateCtl(instance);

		board.initialize();                                    // 盤面の初期化
	}
}