package com.example.nakam.othello_android.game.othello;

import java.util.ArrayList;

import android.util.SparseArray;

/** 盤の履歴を管理する。 */
class History {

	/** 履歴中の現在の盤の状態を指すINDEX */
	private int currentBoardIndex = -1;

	/** 盤の履歴 */
	private ArrayList<HistoryRecord> historyList = new ArrayList<>();

	/** 盤面を保存する。
	 *
	 * @param squares  保存する盤の状態
	 * @param turn     その盤面で打つ権利のある手番
	 */
	void store(SparseArray<Square> squares, Turn turn)
	{
		// 現在の盤面移行の盤面の履歴は削除。
		removeListAfter(currentBoardIndex);

		currentBoardIndex++;
		historyList.add(new HistoryRecord(turn,copy(squares)));
	}

	/** 指定されたINDEXより後の履歴を削除する。
	 *
	 * @param index 保持する履歴の一番最後のレコードを指すINDEX。これ以降の履歴は削除。
	 */
	private void removeListAfter(int index)
	{
		while(index + 1 < historyList.size())
		{
			historyList.remove(historyList.size()-1);
		}
	}

	/** 盤上に置かれた石と手番の情報を取得する。
	 *
	 * @param squares 盤の状態
	 * @return        盤に置かれた石の情報
	 */
	private ArrayList<Disc> copy(SparseArray<Square> squares)
	{
		ArrayList<Disc> boardState = new ArrayList<>();
		for(int i = 0; i < squares.size(); i++)
		{
			boardState.add(squares.valueAt(i).getDisc());
		}
		return boardState;
	}

	/** 盤面を引数で指示された状態に戻す。
	 *
	 * @param squares 盤面。この盤の情報を更新する。
	 * @param boardIndex 復旧する盤の状態を指すINDEX。
	 */
	private void restore(SparseArray<Square> squares,ManageTurn manageTurn,int boardIndex)
	{
		// 盤の状態を指示された状態にする。
		HistoryRecord record = historyList.get(boardIndex);
		for(int i = 0; i < record.boardState.size(); i++)
		{
			squares.valueAt(i).setDisc(record.boardState.get(i));
		}
		// 手番を指示された状態にする。
		manageTurn.setTurn(record.turn);
	}

	/** 盤面の状態をひとつ進める。
	 *
	 * @param squares    盤面
	 * @param manageTurn 手番管理
	 */
	void next(SparseArray<Square> squares, ManageTurn manageTurn)
	{
		currentBoardIndex++;
		restore(squares,manageTurn,currentBoardIndex);
	}

	/** 盤面の状態をひとつ戻す。
	 *
	 * @param squares    盤面
	 * @param manageTurn 手番管理
	 */
	void previous(SparseArray<Square> squares, ManageTurn manageTurn)
	{
		currentBoardIndex--;
		restore(squares,manageTurn,currentBoardIndex);
	}

	/** 盤が履歴の最初の状態の時、trueを返します。
	 *  trueの時、前の盤面は存在しません。
	 *
	 * @return 盤が履歴の最初の状態の時、true
	 */
	boolean isTop()
	{
		return currentBoardIndex == 0;
	}

	/** 盤が履歴の最後の状態の時、trueを返します。
	 *  trueの時、次の盤面は存在しません。
	 *
	 * @return 盤が履歴の最後の状態の時、true
	 */
	boolean isEnd()
	{
		return currentBoardIndex + 1 == historyList.size();
	}
}