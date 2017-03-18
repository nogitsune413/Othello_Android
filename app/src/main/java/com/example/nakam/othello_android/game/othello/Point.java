package com.example.nakam.othello_android.game.othello;

/** オセロのマスを座標で表現する */
class Point {

	private final int x;
	private final int y;

	Point(int x,int y){
		this.x = x;
		this.y = y;
	}

	int getX() {
		return x;
	}

	int getY() {
		return y;
	}

	/**
	 * 次の座標を取得する。
	 *
	 * @param currentPoint 現在の座標
	 * @param direction 現在の座標を原点としたときの取得したい座標の位置ベクトル
	 * @return 次の座標
	 */
	static Point getNext(Point currentPoint, Point direction)
	{
		return new Point(currentPoint.getX() + direction.getX(),currentPoint.getY() + direction.getY());
	}
}