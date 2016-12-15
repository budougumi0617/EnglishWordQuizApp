/**
 * @file 2016/12/14
 */
package quiz.view;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import quiz.controller.Controller;
import quiz.model.EnglishWordBean;

/**
 * 英単語管理画面
 *
 * @author Yuka Yoshikawa
 *
 */
public class ManageDialog extends JDialog implements Observer {

	/** ボタンアクションコントローラー */
	private Controller ctrl;
	/** 全英単語表示テーブル */
	private JTable tbData;
	/** 英単語全データ保持モデル */
	private DefaultTableModel model;

	private JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

	/**
	 * コンポーネント初期設定コンストラクタ
	 *
	 * @param ctrl
	 *            ボタンアクションコントローラー
	 */
	public ManageDialog(Controller ctrl) {

		this.ctrl = ctrl;

		/** ダイアログ設定 */
		setTitle("英単語 管理画面");
		setBounds(150, 150, 586, 287);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setResizable(false);
		setModal(true);
		getContentPane().setLayout(null);

		/** 全英単語表示テーブルの乗るパネル設定 */
		JPanel tbDataPanel = new JPanel();
		tbDataPanel.setLayout(null);
		tbDataPanel.setBounds(38, 18, 500, 200);
		getContentPane().add(tbDataPanel);

		setDataTable(new ArrayList<EnglishWordBean>());

		/** 全英単語表示テーブルのスクロールバー設定 */

		scrollPane.setMinimumSize(new Dimension(500, 200));
		scrollPane.setBounds(12, 24, 476, 144);
		tbDataPanel.add(scrollPane);
		scrollPane.setViewportView(tbData);

		JButton btAdd = new JButton("追加");
		btAdd.setBounds(195, 220, 91, 21);
		getContentPane().add(btAdd);
		btAdd.addActionListener(ae -> this.ctrl.btAddDialogAction(ae));

		JButton btEdit = new JButton("編集");
		btEdit.setBounds(315, 220, 91, 21);
		getContentPane().add(btEdit);

		JButton btDelete = new JButton("削除");
		btDelete.setBounds(435, 220, 91, 21);
		getContentPane().add(btDelete);

	}

	/**
	 * 画面を開くメソッド
	 *
	 * @param list
	 *            ArrayList<EnglishWordBean>型 英単語全データリスト
	 */
	public void open(ArrayList<EnglishWordBean> list) {
		setDataTable(list);
		setVisible(true);
	}

	/**
	 * テーブルに英単語データを反映させるメソッド
	 *
	 * @param list
	 *            ArrayList<EnglishWordBean>型 英単語全データリスト
	 */
	public void setDataTable(ArrayList<EnglishWordBean> list) {

		model = new DefaultTableModel(new String[] { "英単語", "品詞", "意味" }, 0);

		for (int i = 0; i < list.size(); i++) {
			model.addRow(
					new String[] { list.get(i).getWord(), list.get(i).getPart().toString(), list.get(i).getMean() });
		}

		tbData = new JTable(model);

		// 列幅調整を設定
		tbData.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		// 列幅、最小列幅、最大列幅を設定
		DefaultTableColumnModel tcm = (DefaultTableColumnModel) tbData.getColumnModel();

		for (int i = 0; i < tcm.getColumnCount(); i++) {
			TableColumn column = tcm.getColumn(i);
			column.setPreferredWidth(150);
			column.setMinWidth(153);
			column.setMaxWidth(2000);
		}

		scrollPane.setViewportView(tbData);

		tbData.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbData.setDefaultEditor(Object.class, null);

	}

	/**
	 * Observableクラスから通知を受けた際に動作するメソッド
	 */
	@Override
	public void update(java.util.Observable o, Object arg) {
		System.out.println("");
		setDataTable((ArrayList<EnglishWordBean>) arg);
		repaint();
	}

}
