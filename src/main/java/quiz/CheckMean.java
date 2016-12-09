/**
 * @file 2016/12/06
 */
package quiz;

/**
 * 入力された英単語意味を判定するクラス
 *
 * @author Yuka Yoshikawa
 *
 */
public class CheckMean implements CheckInput {

	/** 入力値 上限文字数 */
	private final int MAX_LENGTH = 255;

	/**
	 * 入力された英単語意味が255文字以下か判定する（空欄を除く）か判定する
	 *
	 * @param mean
	 *            String型 入力された英単語意味
	 * @return Boolean型 異常値が入力された場合trueを返す
	 */
	@Override
	public Boolean validate(String mean) {
		return (mean == null || mean.length() > MAX_LENGTH || mean.matches("^[\\t 　]*$"));
	}

}