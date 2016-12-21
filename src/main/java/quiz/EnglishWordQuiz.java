/**
 * @file 2016/12/05
 */
package quiz;

import quiz.controller.Controller;
import quiz.model.SqlDataStore;
import quiz.view.MainFrame;

/**
 * メインメソッドを持つクラス
 *
 * @author Yuka Yoshikawa
 *
 */
public class EnglishWordQuiz {

	/**
	 * GUIインスタンスを生成するメインメソッド
	 *
	 * @param args
	 *            実行時引数 指定なし
	 */
	public static void main(String[] args) {

		@SuppressWarnings("unused")
		MainFrame frame = new MainFrame(new Controller(new SqlDataStore()));

	}

}
