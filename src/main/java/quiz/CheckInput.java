/**
 * @file 2016/12/06
 */
package quiz;

/**
 * 入力値判定インタフェース
 *
 * @author Yuka Yoshikawa
 *
 */
public interface CheckInput {

	/**
	 * 入力値を判定するメソッド
	 *
	 * @param input
	 *            ユーザー入力値
	 * @return Boolean型 異常値が入力された場合trueを返す
	 */
	Boolean existsError(String input);

}
