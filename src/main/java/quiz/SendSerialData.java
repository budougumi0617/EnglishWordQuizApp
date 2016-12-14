/**
 * @file 2016/12/05
 */
package quiz;

import java.io.OutputStream;

import gnu.io.CommPort;
import gnu.io.SerialPort;

/**
 * シリアルデータ通信クラス
 *
 * @author Yuka Yoshikawa
 *
 */
public class SendSerialData implements OutPut {

	/** String型 COMポート番号 */
	private String commPort;
	/** String型 送信する正誤結果文字列データ */
	private String result;
	/** String型 送信する解答英単語文字列データ */
	private String answer;
	/** シリアルポート */
	private SerialPort port = null;
	/** ボーレート */
	private final int BAUDRATE = 9600;

	/**
	 * 使用するCOMポートを取得するメソッド
	 *
	 * @throws java.lang.Exception
	 *             発生する全例外 NoSuchPortException PortInUseException
	 *             UnsupportedCommOperationException
	 *
	 * @note 全ての例外をthrowsする
	 */
	@Override
	public void open() throws Exception {

		CommPort cm = PortIdentifierWrapper.getCommPort(commPort);
		port = (SerialPort) cm;
		port.setSerialPortParams(BAUDRATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
		port.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);

	}

	/**
	 * 文字列データを一文字づつシリアル送信する
	 *
	 * @throws java.lang.Exception
	 *             発生する全例外 IOException InterreptedException
	 *
	 * @note 全ての例外をthrowsする
	 */
	@Override
	public void stream() throws Exception {

		OutputStream out = port.getOutputStream();
		Thread.sleep(2000); // LCDパネル表示のための固定数値とする

		for (int i = 0; i < result.length(); i++) {
			out.write(result.charAt(i));
		}

		out.write(0x0a);

		for (int i = 0; i < answer.length(); i++) {
			out.write(answer.charAt(i));
		}

		out.write(0x00);

		out.close();
	}

	/**
	 * シリアルポートを閉じるメソッド
	 *
	 * @throws java.lang.Exception
	 *             発生する全例外
	 *
	 * @note 全ての例外をthrowsする
	 */
	@Override
	public void close() throws Exception {
		port.close();
	}

	/**
	 * COMポート番号のセッターメソッド
	 *
	 * @param commPort
	 *            String型 COMポート番号
	 */
	public void setCommPort(String commPort) {
		this.commPort = commPort;
	}

	/**
	 * 送信する正誤結果文字列データのセッターメソッド
	 *
	 * @param result
	 *            String型 正誤結果
	 */
	public void setResultMessage(String result) {
		this.result = result;
	}

	/**
	 * 送信する解答英単語文字列データのセッターメソッド
	 *
	 * @param message
	 *            String型 解答の英単語
	 */
	public void setAnswerWord(String answer) {
		this.answer = answer;
	}

}
