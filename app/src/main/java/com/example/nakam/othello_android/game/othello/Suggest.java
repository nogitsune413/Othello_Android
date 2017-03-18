package com.example.nakam.othello_android.game.othello;

/** サジェスト機能 */
class Suggest {

	/**
	 * 引数で指定した手番に石を置けるマスがある時、そのマスをサジェスト用のイメージで表示する。
	 *
	 * @param turn マスをサジェストする手番
	 */
	static void set(Turn turn)
	{
		for(int i = 0; i < Game.getInstance().board.getAllSquares().size(); i++)
		{
			// 検査対象のマスを取得
			Square targetSquare = Game.getInstance().board.getAllSquares().valueAt(i);

			// 空きマスの時は、空きマスの画像か、サジェスト画像のいずれかを表示する。
			if(targetSquare.getDisc() == Disc.EMPTY)
			{
				// 置ける時はサジェスト画像
				if(SearchUtil.isAbleToFlip(targetSquare,turn.getDisc()))
				{
					targetSquare.setImage(SquareImage.SUGGEST_ID);
				}
				// 置けない時は、ただの空きマス画像
				else
				{
					targetSquare.setImage(SquareImage.EMPTY_ID);
				}
			}
		}
	}

	/**
	 * サジェスト用の印を盤面からクリアする。
	 *
	 */
	static void clear()
	{
		for(int i = 0; i < Game.getInstance().board.getAllSquares().size(); i++)
		{
			// 検査対象のマスを取得
			Square targetSquare = Game.getInstance().board.getAllSquares().valueAt(i);

			// 空きマスの時は、空きマスの画像を表示する。
			if(targetSquare.getDisc() == Disc.EMPTY)
			{
				{
					targetSquare.setImage(SquareImage.EMPTY_ID);
				}
			}
		}
	}
}