/**
 * @file 2016/12/06
 */
package quiz;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * CheckMeanクラス テスト
 *
 * @author Yuka Yoshikawa
 *
 */
public class CheckMeanTest {

	private CheckMean cm;

	/**
	 * 初期設定
	 *
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		cm = new CheckMean();
	}

	/**
	 * 終了時動作
	 *
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		cm = null;
	}

	/**
	 * 正常系テスト {@link quiz.CheckMean#existsError(java.lang.String)}
	 *
	 * @note 引数 空白なし 255文字以下
	 * @note 期待戻り値 false
	 */
	@Test
	public void test1() {
		assertThat(cm.existsError("確認する"), is(false));
	}

	/**
	 * 正常系テスト {@link quiz.CheckMean#existsError(java.lang.String)}
	 *
	 * @note 引数 空白なし 255文字
	 * @note 期待戻り値 false
	 */
	@Test
	public void test2() {

		String notOverSize = "あいうえおあいうえおあいうえおあいうえおあいうえお" + "あいうえおあいうえおあいうえおあいうえおあいうえお" + "あいうえおあいうえおあいうえおあいうえおあいうえお"
				+ "あいうえおあいうえおあいうえおあいうえおあいうえお" + "あいうえおあいうえおあいうえおあいうえおあいうえお" + "あいうえおあいうえおあいうえおあいうえおあいうえお"
				+ "あいうえおあいうえおあいうえおあいうえおあいうえお" + "あいうえおあいうえおあいうえおあいうえおあいうえお" + "あいうえおあいうえおあいうえおあいうえおあいうえお"
				+ "あいうえおあいうえおあいうえおあいうえおあいうえお" + "あいうえお";

		assertThat(cm.existsError(notOverSize), is(false));
	}

	/**
	 * 異常系テスト {@link quiz.CheckMean#existsError(java.lang.String)}
	 *
	 * @note 引数 空白なし 256文字
	 * @note 期待戻り値 true
	 */
	@Test
	public void test3() {

		String overSize = "あいうえおあいうえおあいうえおあいうえおあいうえお" + "あいうえおあいうえおあいうえおあいうえおあいうえお" + "あいうえおあいうえおあいうえおあいうえおあいうえお"
				+ "あいうえおあいうえおあいうえおあいうえおあいうえお" + "あいうえおあいうえおあいうえおあいうえおあいうえお" + "あいうえおあいうえおあいうえおあいうえおあいうえお"
				+ "あいうえおあいうえおあいうえおあいうえおあいうえお" + "あいうえおあいうえおあいうえおあいうえおあいうえお" + "あいうえおあいうえおあいうえおあいうえおあいうえお"
				+ "あいうえおあいうえおあいうえおあいうえおあいうえお" + "あいうえお" + "a";

		assertThat(cm.existsError(overSize), is(true));
	}

	/**
	 * 正常系テスト {@link quiz.CheckMean#existsError(java.lang.String)}
	 *
	 * @note 引数 半角スペース、全角スペース、タブスペースを含む文字列
	 * @note 期待戻り値 false
	 */
	@Test
	public void test4() {

		assertThat(cm.existsError("あい う　え	お"), is(false));
	}

	/**
	 * 異常系テスト {@link quiz.CheckMean#existsError(java.lang.String)}
	 *
	 * @note 引数 半角スペース、全角スペース、タブスペースのみの文字列
	 * @note 期待戻り値 true
	 */
	@Test
	public void test5() {
		assertThat(cm.existsError("   　　　		"), is(true));
	}

}
