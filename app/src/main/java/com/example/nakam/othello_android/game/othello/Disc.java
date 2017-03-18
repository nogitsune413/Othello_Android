package com.example.nakam.othello_android.game.othello;

/** 石 */
public enum Disc {
	WHITE,
	BLACK,
	EMPTY;

	int getImageId()
	{
		switch(this)
		{
			case WHITE : {
				return SquareImage.WHITE_ID;
			}
			case BLACK : {
				return SquareImage.BLACK_ID;
			}
			case EMPTY : {
				return SquareImage.EMPTY_ID;
			}
			default : {
				new Exception("Discクラスに含まれない不明な列挙型です。").printStackTrace();

				// 処理を継続するため、空のマスのイメージIDを返す。
				return SquareImage.EMPTY_ID;
			}
		}
	}

	/** 相手の石を返す。
	 *
	 * @return 相手の石
	 */
	Disc getDiscOfOpponent()
	{
		switch(this)
		{
			case WHITE : {
				return BLACK;
			}
			case BLACK : {
				return WHITE;
			}
			case EMPTY : {
				new Exception("この石は空であるため、相手の石はありません。").printStackTrace();

				// 処理を継続するため、空の石を返す。
				return EMPTY;
			}
			default : {
				new Exception("この石は不明な石であるため、相手の石はありません。").printStackTrace();

				// 処理を継続するため、空の石を返す。
				return EMPTY;
			}
		}
	}
}