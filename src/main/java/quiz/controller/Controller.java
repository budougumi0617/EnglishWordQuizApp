/**
 * @file 2016/12/13
 */
package quiz.controller;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.UnsupportedCommOperationException;
import quiz.model.DataStore;
import quiz.model.EnglishWordBean;
import quiz.output.SendSerialData;
import quiz.view.AddDialog;
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
	// private EditDialog = new EditDialog(this);
	// /** 英単語削除画面 */
	// private DeleteDialog = new DeleteDialog(this);

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

		/** Observerの追加 */
		this.data.addObserver((Observer) manageDialog);
	}

	/**
	 * 【要求仕様 A】出題ボタンアクション
	 *
	 * @param ae
	 *            ボタン押下アクション
	 */
	public void btMakeQuizAction(ActionEvent ae) {

		try {
			JButton bt = null;
			MainFrame mf = null;

			if (ae.getSource() instanceof JButton) {
				bt = (JButton) (ae.getSource());
			}
			if (SwingUtilities.getRoot(bt) instanceof MainFrame) {
				mf = (MainFrame) (SwingUtilities.getRoot(bt));
			}

			data.open();
			mf.setLabelText(data.getRandom());
			data.close();

		} catch (SQLException e) {
			ErrorDialog.showErrorDialog("DBアクセスエラーが発生しました。");

		} catch (Exception e) {
			ErrorDialog.showErrorDialog("エラーが発生しました。" + e);
		}
	}

	/**
	 * 【要求仕様 A】英単語管理ボタンアクション
	 *
	 * @param ae
	 *            ボタン押下アクション
	 */
	public void btManageAction(ActionEvent ae) {

		try {
			JButton bt = null;
			MainFrame mf = null;

			if (ae.getSource() instanceof JButton) {
				bt = (JButton) (ae.getSource());
			}
			if (SwingUtilities.getRoot(bt) instanceof MainFrame) {
				mf = (MainFrame) (SwingUtilities.getRoot(bt));
			}
			mf.clearLabelText();
			data.open();
			manageDialog.showDialog(data.getAll());
			data.close();

		} catch (SQLException e) {
			ErrorDialog.showErrorDialog("DBアクセスエラーが発生しました。");

		} catch (Exception e) {
			ErrorDialog.showErrorDialog("エラーが発生しました。");
		}
	}

	/**
	 * 【要求仕様 A】答え合わせボタンアクション
	 *
	 * @param ae
	 *            ボタン押下アクション
	 */
	public void btAnswerAction(ActionEvent ae) {

		try {
			JButton bt = null;
			MainFrame mf = null;

			if (ae.getSource() instanceof JButton) {
				bt = (JButton) (ae.getSource());
			}
			if (SwingUtilities.getRoot(bt) instanceof MainFrame) {
				mf = (MainFrame) (SwingUtilities.getRoot(bt));
			}
			/** 検索対象データ取得 */
			EnglishWordBean bean = mf.getBean();

			/** 答え合わせ */
			String result = "Correct!";

			data.open();
			EnglishWordBean answerBean = data.searchWord(bean);
			data.close();

			if (answerBean == null) {

				data.open();
				answerBean = data.searchWord(bean.setWord("%"));
				data.close();

				result = "Incorrect!";
			}

			/** シリアルデータ送信 */
			SendSerialData ssd = new SendSerialData();
			ssd.setCommPort(mf.cbCommPort.getItemAt(mf.cbCommPort.getSelectedIndex()));
			ssd.setResultMessage(result);
			ssd.setAnswerWord(answerBean.getWord());

			ssd.open();
			ssd.stream();
			ssd.close();

		} catch (NoSuchPortException e) {
			ErrorDialog.showErrorDialog("COMポート番号が正しいか確認してください。");

		} catch (PortInUseException e) {
			ErrorDialog.showErrorDialog("COMポート番号が正しいか確認してください。");

		} catch (UnsupportedCommOperationException e) {
			ErrorDialog.showErrorDialog("通信時エラーが発生しました。");

		} catch (SQLException e) {
			ErrorDialog.showErrorDialog("DBの接続に失敗しました。");

		} catch (NumberFormatException e) {
			ErrorDialog.showErrorDialog("入力値エラーが発生しました。");

		} catch (InterruptedException e) {
			ErrorDialog.showErrorDialog("内部エラーが発生しました。");

		} catch (IOException e) {
			ErrorDialog.showErrorDialog("内部エラーが発生しました。");

		} catch (IllegalArgumentException e) {
			ErrorDialog.showErrorDialog(e.getMessage());

		} catch (Exception e) {
			ErrorDialog.showErrorDialog("エラーが発生しました。");
		}

	}

	/**
	 * 【要求仕様 A】追加画面表示ボタンアクション
	 *
	 * @param ae
	 *            ボタン押下アクション
	 */
	public void btAddDialogAction(ActionEvent ae) {
		addDialog.showDialog();
	}

	/**
	 * 【要求仕様 C】編集画面表示ボタンアクション
	 *
	 * @param ae
	 *            ボタン押下アクション
	 */
	public void btEditDialogAction(ActionEvent ae) {

	}

	/**
	 * 【要求仕様 B】削除画面表示ボタンアクション
	 *
	 * @param ae
	 *            ボタン押下アクション
	 */
	public void btDeleteDialogAction(ActionEvent ae) {

	}

	/**
	 * 【要求仕様 A】追加ボタンアクション
	 *
	 * @param ae
	 *            ボタン押下アクション
	 */
	public void btAddAction(ActionEvent ae) {

		try {
			addDialog.setVisible(false);

			if (data.searchWord(addDialog.getBean()) instanceof EnglishWordBean) {
				ErrorDialog.showErrorDialog("入力データは既に登録されています");
			} else {
				data.open();
				data.insert(addDialog.getBean());
				data.close();
			}

		} catch (SQLException e) {
			ErrorDialog.showErrorDialog("DBの接続に失敗しました。");

		} catch (IOException e) {
			ErrorDialog.showErrorDialog("内部エラーが発生しました。");

		} catch (IllegalArgumentException e) {
			ErrorDialog.showErrorDialog(e.getMessage());

		} catch (Exception e) {
			ErrorDialog.showErrorDialog("エラーが発生しました。");
		}

	}

	/**
	 * 【要求仕様 C】編集ボタンアクション
	 *
	 * @param ae
	 *            ボタン押下アクション
	 */
	public void btEditAction(ActionEvent ae) {

	}

	/**
	 * 【要求仕様 B】削除ボタンアクション
	 *
	 * @param ae
	 *            ボタン押下アクション
	 */
	public void btDeleteAction(ActionEvent ae) {

	}

}
