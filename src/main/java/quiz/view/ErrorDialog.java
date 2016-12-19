/**
 * @file 2016/12/13
 */
package quiz.view;

import javax.swing.JOptionPane;

/**
 * エラーダイアログを生成する
 *
 * @author Yuka Yoshikawa
 *
 */
public class ErrorDialog extends JOptionPane {

	/**
	 * エラーダイアログを生成するメソッド
	 *
	 * @param errorMessage
	 *            String型 エラー内容のメッセージ
	 */
	static public void showErrorDialog(String errorMessage) {
		Object[] selectvalue = { "閉じる" };
		showOptionDialog(null, errorMessage, "エラー発生", YES_NO_OPTION, ERROR_MESSAGE, null, selectvalue, null);
	}

}