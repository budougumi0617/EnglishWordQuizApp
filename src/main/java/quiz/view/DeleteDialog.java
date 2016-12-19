/**
 * @file 2016/12/19
 */
package quiz.view;

import javax.swing.JOptionPane;

import quiz.controller.Controller;
import quiz.model.EnglishWordBean;

/**
 * 英単語追加画面
 *
 * @author Yuka Yoshikawa
 *
 */
public class DeleteDialog extends JOptionPane {

	/** ボタンアクションコントローラー */
	private Controller ctrl;

	/**
	 * コントローラーを受け取るコンストラクタ
	 *
	 * @param ctrl
	 */
	public DeleteDialog(Controller ctrl) {
		this.ctrl = ctrl;
	}

	/**
	 * エラーダイアログを生成するメソッド
	 *
	 * @param errorMessage
	 *            String型 エラー内容のメッセージ
	 */
	public void showDialog(EnglishWordBean bean) {
		Object[] selectvalue = { "はい", "いいえ" };
		int option = showOptionDialog(null, bean.getWord()+"を削除しますか？", "英単語を削除", YES_NO_OPTION, WARNING_MESSAGE, null, selectvalue,
				null);

		if (option == JOptionPane.YES_OPTION) {
			ctrl.btDeleteAction(bean);
		} else if (option == JOptionPane.NO_OPTION) {

		}

	}

}
