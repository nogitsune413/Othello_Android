package com.example.nakam.othello_android.game.othello;

enum Turn {
	BLACK,
	WHITE;

	Disc getDisc()
	{
		switch(this)
		{
			case BLACK : {
				return Disc.BLACK;
			}
			case WHITE : {
				return Disc.WHITE;
			}
			default : {
				new Exception("不明な手番であるため、対応する石がありません。").printStackTrace();

				// 処理を継続するため、空の石を返す。
				return Disc.EMPTY;
			}
		}
	}
}