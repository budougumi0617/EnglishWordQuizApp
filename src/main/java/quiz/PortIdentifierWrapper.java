/**
 * @file 2016/12/05
 */
package quiz;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;

/**
 * CommPortIdentifierクラスのstatic getPortIdentifier() の wrapper を記述するクラス
 *
 * @author Yuka Yoshikawa
 *
 */
public class PortIdentifierWrapper {

	/**
	 * CommPortIdentifierクラスのstatic getPortIdentifier() の wrapperメソッド
	 *
	 * @param commPort
	 *            ユーザーが指定したCOMポート番号
	 * @return ユーザー指定値で開いたCommPortインスタンス
	 * @throws NoSuchPortException
	 *             指定したポートが存在しない場合にスローする
	 * @throws PortInUseException
	 *             指定したポートが既に使われている場合にスローする
	 */
	static CommPort getCommPort(String commPort) throws NoSuchPortException, PortInUseException {
		return CommPortIdentifier.getPortIdentifier(commPort).open("Arduino Uno", 2000);
	}
}