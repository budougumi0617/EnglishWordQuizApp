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
	/** String型 送信する文字列データ */
	private String message;
	/** シリアルポート */
	private SerialPort port = null;

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
		port.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
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

		for (int i = 0; i < message.length(); i++) {
			out.write(message.charAt(i));
		}

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
	 * 送信する文字列データのセッターメソッド
	 *
	 * @param message
	 *            String型 入力文字列
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
