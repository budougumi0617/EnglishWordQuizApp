/**
 * @file 2016/12/05
 */
package quiz.output;

/**
 * 出力インタフェース
 *
 * @author Yuka Yoshikawa
 *
 */
public interface Output {

	/**
	 * 出力先を接続するメソッド
	 *
	 * @throws java.lang.Exception
	 *             発生する全例外
	 *
	 * @note 全ての例外をthrowsする
	 */
	void open() throws Exception;

	/**
	 * データを出力するメソッド
	 *
	 * @throws java.lang.Exception
	 *             発生する全例外
	 *
	 * @note 全ての例外をthrowsする
	 */
	void stream() throws Exception;

	/**
	 * 出力先との接続を閉じるメソッド
	 *
	 * @throws java.lang.Exception
	 *             発生する全例外
	 *
	 * @note 全ての例外をthrowsする
	 */
	void close() throws Exception;

}
