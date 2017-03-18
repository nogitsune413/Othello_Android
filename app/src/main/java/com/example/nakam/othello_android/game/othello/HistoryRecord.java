package com.example.nakam.othello_android.game.othello;

import java.util.ArrayList;

/** 履歴レコード
 *
 * 手番と盤の状態を記録する。
 */
class HistoryRecord {

	/** 記録した盤面で打つ権利のある手番 */
	final Turn turn;

	/** 盤の状態 */
	final ArrayList<Disc> boardState;

	HistoryRecord(Turn turn,ArrayList<Disc> boardState)
	{
		this.turn = turn;
		this.boardState = boardState;
	}
}