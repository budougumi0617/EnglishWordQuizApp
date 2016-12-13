/**
 * @file 2016/12/06
 */
package quiz;

/**
 * 入力された英単語を判定するクラス
 *
 * @author Yuka Yoshikawa
 *
 */
public class CheckWord implements CheckInput {

	/**
	 * 入力英単語が半角英語16文字（空欄を除く）か判定する
	 *
	 * @param word
	 *            String型 入力された英単語
	 * @return Boolean型 異常値が入力された場合trueを返す
	 */
	@Override
	public Boolean validate(String word) {
		return (word == null || !word.matches("^[a-zA-Z]{1,16}$"));
	}

}
