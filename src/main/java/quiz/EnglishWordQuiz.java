/**
 * @file 2016/12/05
 */
package quiz;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

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
	 *
	 * @note アプリケーションは多重起動できない
	 */
	public static void main(String[] args) {

		try {
			@SuppressWarnings("resource")
			FileOutputStream fos = new FileOutputStream("checklock.txt");

			FileChannel fc = fos.getChannel();
			FileLock fl = fc.tryLock();

			if (fl == null) {
				System.out.println("アプリケーションは既に開いています。");
			} else {
				new MainFrame(new Controller(new SqlDataStore()));
			}

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("アプリケーションを開けません。");

		}
	}

}
