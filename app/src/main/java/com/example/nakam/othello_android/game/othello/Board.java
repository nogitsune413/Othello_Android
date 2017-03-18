package com.example.nakam.othello_android.game.othello;

import android.util.SparseArray;

import game.othello.R;

class Board {

	/** タテ */
	static final int HEIGHT = 8;

	/** ヨコ */
	static final int WIDTH = 8;

	/** マス */
	private final SparseArray<Square> squares = new SparseArray<>();

	/** 画面上のボタンのID */
	private static final int[] buttonImageId = {
		R.id.square1, R.id.square2, R.id.square3, R.id.square4, R.id.square5, R.id.square6, R.id.square7, R.id.square8,
		R.id.square9, R.id.square10,R.id.square11,R.id.square12,R.id.square13,R.id.square14,R.id.square15,R.id.square16,
		R.id.square17,R.id.square18,R.id.square19,R.id.square20,R.id.square21,R.id.square22,R.id.square23,R.id.square24,
		R.id.square25,R.id.square26,R.id.square27,R.id.square28,R.id.square29,R.id.square30,R.id.square31,R.id.square32,
		R.id.square33,R.id.square34,R.id.square35,R.id.square36,R.id.square37,R.id.square38,R.id.square39,R.id.square40,
		R.id.square41,R.id.square42,R.id.square43,R.id.square44,R.id.square45,R.id.square46,R.id.square47,R.id.square48,
		R.id.square49,R.id.square50,R.id.square51,R.id.square52,R.id.square53,R.id.square54,R.id.square55,R.id.square56,
		R.id.square57,R.id.square58,R.id.square59,R.id.square60,R.id.square61,R.id.square62,R.id.square63,R.id.square64
	};

	void initialize()
	{
		// 盤のマスを生成する。
		setSquares();

		// 初期配置で石を盤に設定する。
		setInitialPosition();

		// test用の石の配置
//		new UnitTest().setUnitTestPosition();

		Game game = Game.getInstance();

		// サジェスト機能が有効の時、石が置ける空きマスの画像をサジェスト用の画像に差し替える。
		if(game.settingReader.SuggestFeatureIsEnabled())
		{
			Suggest.set(game.turn.getCurrentTurn());
		}

		// 石の数を画面上部に表示する。
		game.discCountView.update(count(Disc.BLACK), count(Disc.WHITE));

		// 盤面を履歴に追加する。
		game.boardStateCtl.addNewStateToHistory(squares,game.turn.getCurrentTurn());
	}

	/**
	 *  盤のマスを生成する。
	 */
	private void setSquares()
	{
		for(int x = 0; x < Board.WIDTH; x++)
		{
			for(int y = 0; y < Board.HEIGHT; y++)
			{
				// マスを表すSquareクラスを生成する。
				setSquare(new Square(new Point(x,y)));
			}
		}
	}

	/**
	 * 初期配置で石を盤に設定する。
	 */
	private void  setInitialPosition()
	{
		for(int x = 0; x < Board.WIDTH; x++)
		{
			for(int y = 0; y < Board.HEIGHT; y++)
			{
				// 白石を配置
				if(Board.isWhiteDiscAtFirst(new Point(x,y)))
				{
					getSquare(new Point(x,y)).setDisc(Disc.WHITE);
					continue;
				}
				// 黒石を配置
				if(Board.isBlackDiscAtFirst(new Point(x,y)))
				{
					getSquare(new Point(x,y)).setDisc(Disc.BLACK);
					continue;
				}
				// 空いているマス
				getSquare(new Point(x,y)).setDisc(Disc.EMPTY);
			}
		}
	}

	/** 初期配置が白石かどうか判定する。
	 *
	 * @param p 判定対象のマスの座標
	 * @return 初期配置が白石の時、True
	 */
	private static boolean isWhiteDiscAtFirst(final Point p)
	{
		return (p.getX() == 3 && p.getY() == 3) || (p.getX() == 4 && p.getY() == 4);
	}

	/** 初期配置が黒石かどうか判定する。
	 *
	 * @param p 判定対象のマスの座標
	 * @return 初期配置が黒石の時、True
	 */
	private static boolean isBlackDiscAtFirst(final Point p)
	{
		return (p.getX() == 3 && p.getY() == 4) || (p.getX() == 4 && p.getY() == 3);
	}

	/** マスの情報を格納
	 *
	 * @param s マスの情報
	 */
	private void setSquare(final Square s){
		squares.put(s.point.getX() * WIDTH + s.point.getY(),s);
	}

	/** マスの情報を取得
	 *
	 * @param p 情報が欲しいマスの座標
	 * @return マスの情報
	 */
	Square getSquare(final Point p){
		return squares.get(p.getX() * WIDTH + p.getY());
	}

	/** 全てのマスの情報を取得
	 *
	 * @return 全てのマスの情報
	 */
	SparseArray<Square> getAllSquares()
	{
		return squares;
	}

	/** 石を数える
	 *
	 * @param disc 数えたい石
	 * @return 石の数
	 */
	int count(Disc disc)
	{
		int sum = 0;
		for(int i = 0; i < squares.size(); i++)
		{
			if(squares.valueAt(i).getDisc() == disc)
			{
				sum++;
			}
		}
		return sum;
	}

	/** マスが存在するかどうか判定する。*/
	boolean contains(Point p)
	{
		return (0 <= p.getX() && p.getX() < WIDTH && 0 <= p.getY() && p.getY() < HEIGHT);
	}

	/** 座標に紐付くボタンのIDを取得
	 *
	 * @param p 座標
	 * @return ボタンイメージを指す部品ID
	 */
	static int getButtonImageId(final Point p)
	{
		return buttonImageId[p.getY() * WIDTH + p.getX()];
	}

	/** 勝敗を判定する。
	 *
	 * @return 勝敗結果
	 */
	private Result judge()
	{
		// 黒石の数と白石の数を集計。
		int white = count(Disc.WHITE);
		int black = count(Disc.BLACK);

		// 集計結果から勝敗を判定。
		if(black > white)
		{
			return Result.BLACK;
		}
		else if(black < white)
		{
			return Result.WHITE;
		}
		else
		{
			return Result.DRAW;
		}
	}

	/** マスに石を置く
	 *
	 * @param square 石を置くマス
	 */
	void place(Square square)
	{
		Game game = Game.getInstance();

		// 置こうとする石。現在の手番の石である。
		Disc disc = game.turn.getCurrentTurn().getDisc();

		// 石が置けるかどうか、判定する。
		if(SearchUtil.isAbleToFlip(square,disc))
		{
			// 石を置き、周囲の石を引っ繰り返す。
			SearchUtil.flip(square,disc);

			// オセロ盤の画面上部に表示される石の数を更新する。
			game.discCountView.update(count(Disc.BLACK), count(Disc.WHITE));

			// どちらの手番にも石を置ける場所がない時、勝敗の判定を行う。
			if(!SearchUtil.isAbleToFlip(game.turn.getCurrentTurn()) &&
               !SearchUtil.isAbleToFlip(game.turn.getTurnOfOpponent()))
			{
				// サジェストをクリア
				Suggest.clear();

				// 判定結果を表示する。
				judge().show(game.resultDialog);

				// オセロ盤の履歴に盤面を登録する。打つ権利のある手番には相手の手番を登録。
				game.boardStateCtl.addNewStateToHistory(squares,game.turn.getTurnOfOpponent());

				return;
			}
			// 手番を交代する。
			game.turn.changeTurn();

			// 引っ繰り返せる石がない時はパスする。
			if(!SearchUtil.isAbleToFlip(game.turn.getCurrentTurn()))
			{
				// パスの表示
				game.passDialog.show();

				// 手番を交代する。
				game.turn.changeTurn();
			}

			// オセロ盤の履歴に盤面を登録する。
			game.boardStateCtl.addNewStateToHistory(squares,game.turn.getCurrentTurn());

			// サジェスト機能が有効の時、石が置ける空きマスの画像をサジェスト用の画像に差し替える。
			if(game.settingReader.SuggestFeatureIsEnabled())
			{
				Suggest.set(game.turn.getCurrentTurn());
			}
		}
	}
}