package com.example.nakam.othello_android.game.othello;


/** 単体テスト用のクラス */
public class UnitTest {

	/** オセロ盤 */
	private final Game game;

	UnitTest()
	{
		this.game = Game.getInstance();
	}

	/** テスト用の石の配置 */
	public void  setUnitTestPosition()
	{
		pass();
	}

	/** パスを発生させる。黒先で次の手番の白番がパスになる。 */
	private void pass()
	{
		// 石の配置を行う。
		for(int x = 0; x < Board.WIDTH; x++)
		{
			for(int y = 0; y < Board.HEIGHT; y++)
			{
				// 空白を設定
				game.board.getSquare(new Point(x,y)).setDisc(Disc.EMPTY);
			}
		}
		game.board.getSquare(new Point(0,2)).setDisc(Disc.BLACK);
		game.board.getSquare(new Point(0,3)).setDisc(Disc.WHITE);
		game.board.getSquare(new Point(1,3)).setDisc(Disc.WHITE);

		// 手番を黒番にする。
		game.turn.setTurn(Turn.BLACK);
	}

	/** ひとつ石を置くと、引き分けになる。*/
	@SuppressWarnings("unused")
	private void draw()
	{
		// 石の配置を行う。
		for(int x = 0; x < Board.WIDTH; x++)
		{
			for(int y = 0; y < Board.HEIGHT / 2; y++)
			{
				// 半分は黒
				game.board.getSquare(new Point(x,y)).setDisc(Disc.BLACK);
			}
			for(int y = Board.HEIGHT / 2; y < Board.HEIGHT; y++)
			{
				// 半分は白
				game.board.getSquare(new Point(x,y)).setDisc(Disc.WHITE);
			}
		}
		game.board.getSquare(new Point(7,0)).setDisc(Disc.EMPTY);
		game.board.getSquare(new Point(7,1)).setDisc(Disc.WHITE);

		// 手番を黒番にする。
		game.turn.setTurn(Turn.BLACK);
	}

	/** ひとつ石を置くと、白番が勝つ。*/
	@SuppressWarnings("unused")
	private void whiteWin()
	{
		// 石の配置を行う。
		for(int x = 0; x < Board.WIDTH; x++)
		{
			for(int y = 0; y < Board.HEIGHT; y++)
			{
				// 空いているマス
				game.board.getSquare(new Point(x,y)).setDisc(Disc.EMPTY);
			}
		}
		game.board.getSquare(new Point(3,3)).setDisc(Disc.WHITE);
		game.board.getSquare(new Point(3,4)).setDisc(Disc.WHITE);
		game.board.getSquare(new Point(4,3)).setDisc(Disc.WHITE);
		game.board.getSquare(new Point(4,4)).setDisc(Disc.BLACK);

		// 手番を白番にする。
		game.turn.setTurn(Turn.WHITE);
	}

	/** バグ #70
	 *
	 * [サジェスト機能が有効で、空きマスを残して勝敗が決まった時、空きマスのサジェストが消えない。]
	 */
	@SuppressWarnings("unused")
	private void  redmine_70()
	{
		for(int x = 0; x < Board.WIDTH; x++)
		{
			for(int y = 0; y < Board.HEIGHT; y++)
			{
				// 空いているマス
				game.board.getSquare(new Point(x,y)).setDisc(Disc.EMPTY);
			}
		}
		game.board.getSquare(new Point(3,3)).setDisc(Disc.BLACK);
		game.board.getSquare(new Point(3,4)).setDisc(Disc.BLACK);
		game.board.getSquare(new Point(4,3)).setDisc(Disc.BLACK);
		game.board.getSquare(new Point(4,4)).setDisc(Disc.WHITE);
	}
}