/**
 * @file 2016/12/14
 */
package quiz.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EnumSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import quiz.controller.Controller;
import quiz.model.EnglishWordBean;
import quiz.model.Part;

/**
 * 英単語追加画面
 *
 * @author Yuka Yoshikawa
 *
 */
public class AddDialog extends JDialog {

	/** ボタンアクションコントローラー */
	private Controller ctrl;

	/** 英単語入力欄 */
	private JTextField tfWord;
	/** 英単語意味入力欄 */
	private JTextField tfMean;
	/* 品詞コンボボックス */
	private JComboBox<String> cbPart;

	/**
	 * コンポーネント初期設定コンストラクタ
	 *
	 * @param ctrl
	 *            ボタンアクションコントローラー
	 */
	public AddDialog(Controller ctrl) {

		this.ctrl = ctrl;

		/** ダイアログ設定 */
		setTitle("英単語 追加画面");
		setBounds(200, 200, 586, 287);
		setResizable(false);
		setModal(true);
		getContentPane().setLayout(null);

		/** 英単語ラベル設定 */
		JLabel lbWord = new JLabel("単語：");
		lbWord.setBounds(49, 39, 50, 13);
		getContentPane().add(lbWord);

		/** 品詞ラベル設定 */
		JLabel lbPart = new JLabel("品詞：");
		lbPart.setBounds(49, 89, 50, 13);
		getContentPane().add(lbPart);

		/** 英単語意味ラベル設定 */
		JLabel lbMean = new JLabel("意味：");
		lbMean.setBounds(49, 141, 50, 13);
		getContentPane().add(lbMean);

		/** 英単語入力欄設定 */
		tfWord = new JTextField();
		tfWord.setBounds(106, 36, 425, 19);
		getContentPane().add(tfWord);
		tfWord.setColumns(10);

		/** 英単語意味入力欄設定 */
		tfMean = new JTextField();
		tfMean.setBounds(106, 138, 425, 19);
		getContentPane().add(tfMean);
		tfMean.setColumns(10);

		/* 品詞コンボボックスに値を設定する */
		String[] cbPartData = new String[EnumSet.allOf(Part.class).size()];

		Part[] parts = Part.values();
		for (int i = 0; i < EnumSet.allOf(Part.class).size(); i++) {
			cbPartData[i] = parts[i].toString();
		}

		/** 品詞コンボボックス設定 */
		cbPart = new JComboBox<String>(cbPartData);
		cbPart.setBounds(106, 86, 126, 19);
		getContentPane().add(cbPart);

		/** 追加ボタン設定 */
		JButton btAdd = new JButton("追加");
		btAdd.setBounds(274, 206, 121, 21);
		getContentPane().add(btAdd);
		btAdd.addActionListener(ae -> this.ctrl.btAddAction(ae));

		/** キャンセルボタン設定 */
		JButton btCancel = new JButton("キャンセル");
		btCancel.setBounds(423, 206, 121, 21);
		getContentPane().add(btCancel);
		btCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

	}

	/**
	 * 画面を開くメソッド
	 */
	public void open() {

		tfWord.setText("");
		cbPart.setSelectedIndex(0);
		tfMean.setText("");

		setVisible(true);
	}

	/**
	 * 入力されているデータを渡すメソッド
	 *
	 * @return bean EnglishWordBean 入力値データ
	 */
	public EnglishWordBean getBean() {

		EnglishWordBean bean = new EnglishWordBean();

		bean.setWord(tfWord.getText());
		bean.setPart(Part.getPart(cbPart.getItemAt(cbPart.getSelectedIndex())));
		bean.setMean(tfMean.getText());

		return bean;

	}

}
