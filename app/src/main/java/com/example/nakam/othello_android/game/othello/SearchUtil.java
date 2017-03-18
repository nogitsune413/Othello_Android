package com.example.nakam.othello_android.game.othello;

import java.util.ArrayList;

class SearchUtil {

	/** 探索方向 */
	private static final ArrayList<Point> directions = new ArrayList<>();

	static
	{
		// 周囲8方向を探索する時に用いる方角
		directions.add(new Point(-1, 1)); // ↑←
		directions.add(new Point( 0, 1)); // ↑
		directions.add(new Point( 1, 1)); // ↑→
		directions.add(new Point(-1, 0)); // ←
		directions.add(new Point( 1, 0)); // →
		directions.add(new Point(-1,-1)); // ↓←
		directions.add(new Point( 0,-1)); // ↓
		directions.add(new Point( 1,-1)); // ↓→
	}

	/** 石をマスに置き、周囲の相手の石を引っ繰り返す。
	 *
	 * @param placeSquare 石を置くマス
	 * @param disc        置く石
	 */
	static void flip(Square placeSquare, Disc disc)
	{
		// 石をマスに置く。
		placeSquare.setDisc(disc);

		// 周囲８方向に石を置いていく。
		for(Point direction : directions)
		{
			if(isAbleToFlip(placeSquare,disc,direction))
			{
				flip(placeSquare,disc,direction);
			}
		}
	}

	/** 引数で指定された方向にある相手の石を引っ繰り返す。
	 *
	 * @param placeSquare 石を置くマス
	 * @param disc        置く石
	 * @param direction   相手の石を探索する方向
	 */
	private static void flip(Square placeSquare,Disc disc,Point direction)
	{
		for(Point nextPoint = Point.getNext(placeSquare.point, direction);;
				  nextPoint = Point.getNext(nextPoint, direction))
		{
			// 調査対象の座標にマスが存在しない時は何もしない。
			if(!Game.getInstance().board.contains(nextPoint))
			{
				return;
			}

			// 調査対象のマスに置いてある石を取得
			Disc checkDisc = Game.getInstance().board.getSquare(nextPoint).getDisc();

			// その石が相手の石の時は引っ繰り返す。
			if(checkDisc == disc.getDiscOfOpponent())
			{
				Game.getInstance().board.getSquare(nextPoint).setDisc(disc);
			}
			else
			{
				return;
			}
		}
	}

	/**
	 * 引数で指定した手番に引っ繰り返せる石があるかどうか判定する。
	 *
	 * @param turn 判定する手番
	 * @return 引っ繰り返せる石があるとき、True
	 */
	static boolean isAbleToFlip(Turn turn)
	{
		for(int i = 0; i < Game.getInstance().board.getAllSquares().size(); i++)
		{
			if(isAbleToFlip(Game.getInstance().board.getAllSquares().valueAt(i),turn.getDisc()))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * 引数で指定したマスに石を置き、相手の石が引っ繰り返せるか判定する。
	 *
	 * @param placeSquare 石を置くマス
	 * @param disc        置く石
	 * @return 引っ繰り返せる相手の石がある時、True
	 */
	static boolean isAbleToFlip(Square placeSquare, Disc disc)
	{
		// 石を置こうとしたマスが、空きマスでない時はFalse
		if(placeSquare.getDisc() != Disc.EMPTY)
		{
			return false;
		}

		// 周囲８方向を調査する。
		for(Point direction : directions)
		{
			if(isAbleToFlip(placeSquare,disc,direction))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * 引数で指定した方向に引っ繰り返せる石があるか判定する。
	 *
	 * @param placeSquare 石を置くマス
	 * @param disc        置く石
	 * @param direction   調査する方向
	 * @return 引っ繰り返せる石がある時、TRUE
	 */
	private static boolean isAbleToFlip(Square placeSquare,Disc disc,Point direction)
	{
		// すぐ隣のマスを調査
		Point nextPoint = Point.getNext(placeSquare.point, direction);
		if(!checkNextPoint(disc,nextPoint))
		{
			return false;
		}

		// 更に先のマスを調査
		while(true)
		{
			nextPoint = Point.getNext(nextPoint, direction);

			// 調査対象の座標にマスが存在しない時はFalse
			if(!Game.getInstance().board.contains(nextPoint))
			{
				return false;
			}

			// 調査対象のマスに置いてある石を取得
			Disc checkDisc = Game.getInstance().board.getSquare(nextPoint).getDisc();

			// その石が置く石と同じ色の石の時はTrue
			if(checkDisc == disc)
			{
				return true;
			}

			// その石が置く石の相手の石の時は調査を続行する。
			if(checkDisc == disc.getDiscOfOpponent())
			{
				continue;
			}

			// 石がないときは、False
			if(checkDisc == Disc.EMPTY)
			{
				return false;
			}
		}
	}

	/** 石を置くマスのすぐ隣のマスに引っ繰り返せる相手の石があるかどうか、判定する。
	 *
	 * @param disc      置く石
	 * @param nextPoint 隣のマスの座標
	 * @return 引っ繰り返せる石があるとき、true
	 */
	private static boolean checkNextPoint(Disc disc,Point nextPoint)
	{
		// 調査対象の座標にマスが存在し、かつそのマスが相手の石のとき、true
		return Game.getInstance().board.contains(nextPoint) && Game.getInstance().board.getSquare(nextPoint).getDisc() == disc.getDiscOfOpponent();
	}
}