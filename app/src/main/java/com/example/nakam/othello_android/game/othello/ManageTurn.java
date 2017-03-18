package com.example.nakam.othello_android.game.othello;

class ManageTurn {

	private Turn currentTurn;

	/** コンストラクタ。初手は黒番。 */
	ManageTurn()
	{
		currentTurn = Turn.BLACK;
	}

	/** 現在の手番を返す */
	Turn getCurrentTurn()
	{
		return currentTurn;
	}

	/** 現在の手番から見て、相手方の手番を取得する。 */
	Turn getTurnOfOpponent()
	{
		if(currentTurn == Turn.BLACK)
		{
			return Turn.WHITE;
		}
		else
		{
			return Turn.BLACK;
		}
	}

	/** 手番を交代する。*/
	void changeTurn()
	{
		if(currentTurn == Turn.BLACK)
		{
			currentTurn = Turn.WHITE;
		}
		else
		{
			currentTurn = Turn.BLACK;
		}
	}

	/** 手番を設定する。
	 *
	 * @param turn 設定する手番
	 */
	void setTurn(Turn turn)
	{
		this.currentTurn = turn;
	}
}