package com.example.nakam.othello_android.game.othello;

import android.view.View;
import android.widget.ImageButton;

/** マス */
class Square implements View.OnClickListener {

	/** 石 */
	private Disc disc;

	/** 画面上のボタン */
	private ImageButton button;

	/** 画面上の座標位置 */
	Point point;

	/** コンストラクタ */
	Square (Point point)
	{
		// マスの座標情報を保持する。
		this.point = point;

		// 盤面でマスを表すボタンイメージを設定。
		button = (ImageButton)Game
				                .getInstance()
				                .gameScreenActivity
				                .findViewById(Board.getButtonImageId(point));

		// ボタンイメージにリスナーを登録。
		button.setOnClickListener(this);
	}

	/** 石を置く */
	public void setDisc(Disc disc)
	{
		// 石の情報を更新する。
		this.disc = disc;

		// 画像リソースを更新。
		setImage(disc.getImageId());
	}

	/** マスに画像を設定する。
	 *
	 * @param ImageId 画像と紐付くID
	 */
	void setImage(int ImageId)
	{
		// 画像リソースを更新。
		button.setImageResource(ImageId);
	}

	/** 石を取得する。 */
	public Disc getDisc()
	{
		return this.disc;
	}

	@Override
	public void onClick(View v) {
		Game.getInstance().board.place(this);
	}
}