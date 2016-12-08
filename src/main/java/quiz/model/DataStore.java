/**
 * @file 2016/12/07
 */
package quiz.model;

import java.util.ArrayList;

/**
 * データストアインタフェース
 *
 * @author Yuka Yoshikawa
 *
 */
public interface DataStore {

	/**
	 * データストアに接続するメソッド
	 *
	 * @throws java.lang.Exception
	 *             発生する全例外
	 *
	 * @note 全ての例外をthrowsする
	 */
	void open() throws Exception;

	/**
	 * データストアとの接続を閉じるメソッド
	 *
	 * @throws java.lang.Exception
	 *             発生する全例外
	 *
	 * @note 全ての例外をthrowsする
	 */
	void close() throws Exception;

	/**
	 * データ全件取得メソッド
	 *
	 * @return ArrayList 全データリスト
	 * @note ArrayList格納データ - EnglishWordBean
	 *
	 * @throws java.lang.Exception
	 *             発生する全例外
	 *
	 * @note 全ての例外をthrowsする
	 */
	ArrayList<EnglishWordBean> getAll() throws Exception;

	/**
	 * データ1件追加メソッド
	 *
	 * @param bean
	 *            データ1件の情報を格納している
	 * @throws java.lang.Exception
	 *             発生する全例外
	 *
	 * @note 全ての例外をthrowsする
	 */
	void insert(EnglishWordBean bean) throws Exception;

	/**
	 * データ1件更新メソッド
	 *
	 * @param bean
	 *            データ1件の情報を格納している
	 * @throws java.lang.Exception
	 *             発生する全例外
	 *
	 * @note 全ての例外をthrowsする
	 */
	void update(EnglishWordBean bean) throws Exception;

	/**
	 * データ1件削除メソッド
	 *
	 * @param bean
	 *            データ1件の情報を格納している
	 * @throws java.lang.Exception
	 *             発生する全例外
	 *
	 * @note 全ての例外をthrowsする
	 */
	void delete(EnglishWordBean bean) throws Exception;

	/**
	 * データ1件検索メソッド
	 *
	 * @param bean
	 *            データ1件の情報を格納している
	 * @return EnglishWordBean データ1件の情報を格納している
	 * @throws java.lang.Exception
	 *             発生する全例外
	 *
	 * @note 全ての例外をthrowsする
	 */
	EnglishWordBean searchWord(EnglishWordBean bean) throws Exception;

	/**
	 * データ1件ランダム取得メソッド
	 *
	 * @return EnglishWordBean データ1件の情報を格納している
	 * @throws java.lang.Exception
	 *             発生する全例外
	 *
	 * @note 全ての例外をthrowsする
	 */
	EnglishWordBean getRandom() throws Exception;

}
