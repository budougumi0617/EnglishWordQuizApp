/**
 * @file 2016/12/08
 */
package quiz.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import quiz.controller.Controller;
import quiz.model.EnglishWordBean;

/**
 * メイン画面構成
 *
 * @author Yuka Yoshikawa
 *
 */
public class MainFrame extends JFrame {

	/** ボタンアクションコントローラー */
	private Controller ctrl = null;

	/** COMポートコンボボックス */
	public JComboBox<String> cbCommPort;
	/** COMポートコンボボックスの最大格納数 */
	private final int CONTENT_MAX_NUM = 16;
	/** クイズ出題時の説明ラベル */
	private JLabel lbQuiz;
	/** クイズ出題欄 */
	private JTextField tfQuizBox;
	/** 解答欄 */
	private JTextField tfAnswerBox;

	/**
	 * コンポーネント初期設定コンストラクタ
	 *
	 * @param ctrl
	 *            ボタンアクションコントローラー
	 */
	public MainFrame(Controller ctrl) {

		this.ctrl = ctrl;
		ctrl.setViewInstance();

		/** フレーム設定 */
		setTitle("英単語 クイズアプリ");
		setBounds(100, 100, 773, 331);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		getContentPane().setLayout(null);

		/** コンボボックスに値を設定する */
		String[] combodata = new String[CONTENT_MAX_NUM];

		for (int i = 0; i < CONTENT_MAX_NUM; i++) {
			combodata[i] = "COM" + Integer.toString(i + 1);
		}

		/** COMポートコンボボックス設定 */
		cbCommPort = new JComboBox<String>(combodata);
		cbCommPort.setBounds(568, 31, 131, 21);
		getContentPane().add(cbCommPort);

		/** COMポートラベル設定 */
		JLabel lbCommPort = new JLabel("COMポート番号：");
		lbCommPort.setBounds(450, 31, 131, 21);
		getContentPane().add(lbCommPort);

		/** クイズラベル設定 */
		lbQuiz = new JLabel("");
		lbQuiz.setBounds(49, 84, 598, 13);
		getContentPane().add(lbQuiz);

		/** クイズ出題欄設定 */
		tfQuizBox = new JTextField("");
		tfQuizBox.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		tfQuizBox.setBackground(new Color(255, 255, 204));
		tfQuizBox.setOpaque(true);
		tfQuizBox.setFont(new Font("MS UI Gothic", Font.PLAIN, 23));
		tfQuizBox.setBounds(121, 125, 579, 48);
		tfQuizBox.setEnabled(false);
		tfQuizBox.setDisabledTextColor(Color.BLACK);
		getContentPane().add(tfQuizBox);

		/** クイズ出題ボタン設定 */
		JButton btQuiz = new JButton("出題");
		btQuiz.setBounds(48, 31, 131, 21);
		getContentPane().add(btQuiz);
		btQuiz.addActionListener(ae -> this.ctrl.btMakeQuizAction(ae));

		/** 英単語管理ボタン設定 */
		JButton btEdit = new JButton("英単語管理");
		btEdit.setBounds(225, 31, 131, 21);
		getContentPane().add(btEdit);
		btEdit.addActionListener(ae -> this.ctrl.btManageAction(ae));

		/** 解答欄設定 */
		tfAnswerBox = new JTextField();
		tfAnswerBox.setFont(new Font("MS UI Gothic", Font.PLAIN, 23));
		tfAnswerBox.setBounds(121, 188, 579, 48);
		getContentPane().add(tfAnswerBox);
		tfAnswerBox.setColumns(10);

		/** 答え合わせボタン設定 */
		JButton btAnswer = new JButton("答え合わせ");
		btAnswer.setBounds(568, 259, 131, 21);
		getContentPane().add(btAnswer);
		btAnswer.addActionListener(ae -> this.ctrl.btAnswerAction(ae));

		/** 解答欄ラベル設定 */
		JLabel lbAnswerSeet = new JLabel("解答欄：");
		lbAnswerSeet.setBounds(49, 209, 60, 13);
		getContentPane().add(lbAnswerSeet);

		/** 問題ラベル設定 */
		JLabel lbQuizSeet = new JLabel("問題：");
		lbQuizSeet.setBounds(49, 140, 50, 13);
		getContentPane().add(lbQuizSeet);

		setVisible(true);

	}

	/**
	 * 出題欄、解答欄、クイズ誘導文の表示を初期化する
	 */
	public void clearLabelText() {
		lbQuiz.setText("");
		tfQuizBox.setText("");
		tfAnswerBox.setText("");
	}

	/**
	 * 出題ボタン押下時に問題をGUI上に表示する
	 *
	 * @param bean
	 *            クイズデータ
	 */
	public void setLabelText(EnglishWordBean bean) {
		lbQuiz.setText("問題の意味に合った英単語を解答欄に入力してください。");
		tfQuizBox.setText(bean.getMean());
	}

	/**
	 * 入力されているデータを渡すメソッド
	 *
	 * @return bean EnglishWordBean 入力値データ
	 */
	public EnglishWordBean getBean() {
		return new EnglishWordBean().setWord(tfAnswerBox.getText()).setMean(tfQuizBox.getText());

	}

}
