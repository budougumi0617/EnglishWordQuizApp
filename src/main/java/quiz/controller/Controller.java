/**
 * @file 2016/12/13
 */
package quiz.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.UnsupportedCommOperationException;
import quiz.model.DataStore;
import quiz.model.EnglishWordBean;
import quiz.output.Output;
import quiz.output.SendSerialData;
import quiz.view.AddDialog;
import quiz.view.DeleteDialog;
import quiz.view.ErrorDialog;
import quiz.view.MainFrame;
import quiz.view.ManageDialog;

/**
 * ボタンアクションを管理するコントローラー
 *
 * @author Yuka Yoshikawa
 *
 */
public class Controller {

	/** データストア */
	private DataStore data;

	/** 英単語管理画面 */
	private ManageDialog manageDialog;
	/** 英単語追加画面 */
	private AddDialog addDialog;

	// /** 英単語編集画面 */
	// private EditDialog editDialog;
	/** 英単語削除画面 */
	private DeleteDialog deleteDialog;

	/**
	 * コンストラクタでデータストアのインスタンスをセット
	 *
	 * @param data
	 */
	public Controller(DataStore data) {
		this.data = data;
	}

	/**
	 * 画面インスタンスの生成を行う
	 */
	public void setViewInstance() {
		manageDialog = new ManageDialog(this);
		addDialog = new AddDialog(this);
		deleteDialog = new DeleteDialog(this);

		/** Observerの追加 */
		this.data.addObserver((Observer) manageDialog);
	}

	/**
	 * 【要求仕様 A】出題ボタンアクション
	 *
	 * @param ae
	 *            ボタン押下イベント
	 */
	public void btMakeQuizAction(ActionEvent ae) {

		try {
			if (ae.getSource() instanceof JButton
					&& SwingUtilities.getRoot((Component) ae.getSource()) instanceof MainFrame) {
				MainFrame mf = (MainFrame) (SwingUtilities.getRoot((JButton) (ae.getSource())));

				data.open();
				mf.setLabelText(data.getRandom());

			} else {
				throw new UnsupportedOperationException("イベント発生箇所に問題があります。");
			}
		} catch (SQLException e) {
			ErrorDialog.showErrorDialog("DBアクセスエラーが発生しました。");
		} catch (NullPointerException e) {
			ErrorDialog.showErrorDialog("単語が登録されていません。");
		} catch (Exception e) {
			ErrorDialog.showErrorDialog("エラーが発生しました。");
		} finally {
			try {
				data.close();
			} catch (Exception e) {
				ErrorDialog.showErrorDialog("エラーが発生しました。");
			}
		}
	}

	/**
	 * 【要求仕様 A】英単語管理ボタンアクション
	 *
	 * @param ae
	 *            ボタン押下イベント
	 */
	public void btManageAction(ActionEvent ae) {

		try {
			if (ae.getSource() instanceof JButton
					&& SwingUtilities.getRoot((Component) ae.getSource()) instanceof MainFrame) {
				MainFrame mf = (MainFrame) (SwingUtilities.getRoot((JButton) (ae.getSource())));

				mf.clearLabelText();
				data.open();
				manageDialog.showDialog(data.getAll());

			} else {
				throw new UnsupportedOperationException("イベント発生箇所に問題があります。");
			}
		} catch (SQLException e) {
			ErrorDialog.showErrorDialog("DBアクセスエラーが発生しました。");
		} catch (Exception e) {
			ErrorDialog.showErrorDialog("エラーが発生しました。");
		} finally {
			try {
				data.close();
			} catch (Exception e) {
				ErrorDialog.showErrorDialog("エラーが発生しました。");
			}
		}
	}

	/**
	 * 【要求仕様 A】答え合わせボタンアクション
	 *
	 * @param ae
	 *            ボタン押下イベント
	 */
	public void btAnswerAction(ActionEvent ae) {

		try {
			if (ae.getSource() instanceof JButton
					&& SwingUtilities.getRoot((Component) ae.getSource()) instanceof MainFrame) {
				MainFrame mf = (MainFrame) (SwingUtilities.getRoot((JButton) (ae.getSource())));

				/** 検索対象データ取得 */
				EnglishWordBean bean = mf.getBean();

				/** 答え合わせ */
				String result = "Correct!";

				data.open();
				EnglishWordBean answerBean = data.searchWord(bean);

				if (answerBean == null) {

					answerBean = data.searchWord(bean.setWord("%"));

					result = "Incorrect!";
				}

				/** シリアルデータ送信 */
				Output ssd = new SendSerialData(mf.cbCommPort.getItemAt(mf.cbCommPort.getSelectedIndex()), result,
						answerBean.getWord());

				ssd.open();
				ssd.stream();
				ssd.close();

			} else {
				throw new UnsupportedOperationException("イベント発生箇所に問題があります。");
			}
		} catch (NoSuchPortException | PortInUseException e) {
			ErrorDialog.showErrorDialog("COMポート番号が正しいか確認してください。");
		} catch (UnsupportedCommOperationException e) {
			ErrorDialog.showErrorDialog("通信時エラーが発生しました。");
		} catch (SQLException e) {
			ErrorDialog.showErrorDialog("DBの接続に失敗しました。");
		} catch (NumberFormatException e) {
			ErrorDialog.showErrorDialog("入力値エラーが発生しました。");
		} catch (IllegalArgumentException e) {
			ErrorDialog.showErrorDialog("問題、入力欄を再確認してください。英単語は半角英字16文字までです。");
		} catch (Exception e) {
			ErrorDialog.showErrorDialog("エラーが発生しました。");
		} finally {
			try {
				data.close();
			} catch (Exception e) {
				ErrorDialog.showErrorDialog("エラーが発生しました。");
			}
		}

	}

	/**
	 * 【要求仕様 A】追加画面表示ボタンアクション
	 *
	 * @param ae
	 *            ボタン押下イベント
	 */
	public void btAddDialogAction(ActionEvent ae) {
		addDialog.showDialog();
	}

	/**
	 * 【要求仕様 C】編集画面表示ボタンアクション
	 *
	 * @param ae
	 *            ボタン押下イベント
	 */
	// public void btEditDialogAction(ActionEvent ae) { 未実装
	// }

	/**
	 * 【要求仕様 B】削除画面表示ボタンアクション
	 *
	 * @param ae
	 *            ボタン押下イベント
	 */
	public void btDeleteDialogAction(ActionEvent ae) {

		try {
			if (ae.getSource() instanceof JButton
					&& SwingUtilities.getRoot((Component) ae.getSource()) instanceof ManageDialog) {
				ManageDialog md = (ManageDialog) (SwingUtilities.getRoot((JButton) (ae.getSource())));

				/**
				 * 検索対象データ取得
				 *
				 * JTableデータが選択されていない場合ArrayIndexOutOfBoundsExceptionが発生
				 */
				EnglishWordBean bean = md.getBean();

				deleteDialog.showDialog(bean);

			} else {
				throw new UnsupportedOperationException("イベント発生箇所に問題があります。");
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			ErrorDialog.showErrorDialog("削除する英単語を選択してください。");
		} catch (Exception e) {
			ErrorDialog.showErrorDialog("エラーが発生しました。");
		}

	}

	/**
	 * 【要求仕様 A】追加ボタンアクション
	 *
	 * @param ae
	 *            ボタン押下イベント
	 */
	public void btAddAction(ActionEvent ae) {

		try {

			data.open();
			EnglishWordBean bean = data.searchWord(addDialog.getBean());

			if (bean != null) {
				ErrorDialog.showErrorDialog("入力データは既に登録されています。");
			} else {
				data.insert(addDialog.getBean());
			}

			addDialog.setVisible(false);

		} catch (SQLException e) {
			ErrorDialog.showErrorDialog("DBの接続に失敗しました。");
		} catch (IllegalArgumentException e) {
			ErrorDialog.showErrorDialog(e.getMessage());
		} catch (Exception e) {
			ErrorDialog.showErrorDialog("エラーが発生しました。" + e);
		} finally {
			try {
				data.close();
			} catch (Exception e) {
				ErrorDialog.showErrorDialog("エラーが発生しました。");
			}
		}

	}

	/**
	 * 【要求仕様 C】編集ボタンアクション
	 *
	 * @param ae
	 *            ボタン押下イベント
	 */
	// public void btEditAction(ActionEvent ae) { 未実装
	// }

	/**
	 * 【要求仕様 B】削除ボタンアクション
	 *
	 * @param ae
	 *            ボタン押下イベント
	 */
	public void btDeleteAction(EnglishWordBean bean) {

		try {
			data.open();
			data.delete(bean);

			addDialog.setVisible(false);

		} catch (SQLException e) {
			ErrorDialog.showErrorDialog("DBの接続に失敗しました。");
		} catch (IllegalArgumentException e) {
			ErrorDialog.showErrorDialog(e.getMessage());
		} catch (Exception e) {
			ErrorDialog.showErrorDialog("エラーが発生しました。");
		} finally {
			try {
				data.close();
			} catch (Exception e) {
				ErrorDialog.showErrorDialog("エラーが発生しました。");
			}
		}

	}

}
