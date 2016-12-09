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
	 * 正常系テスト {@link quiz.CheckMean#validate(java.lang.String)}
	 *
	 * @note 引数 空白なし 255文字以下
	 * @note 期待戻り値 false
	 */
	@Test
	public void testNoSpace() {
		assertThat(cm.validate("確認する"), is(false));
	}

	/**
	 * 正常系テスト {@link quiz.CheckMean#validate(java.lang.String)}
	 *
	 * @note 引数 空白なし 255文字
	 * @note 期待戻り値 false
	 */
	@Test
	public void testNoSpaceMaxLength() {

		String notOverSize = "あいうえおあいうえおあいうえおあいうえおあいうえお" + "あいうえおあいうえおあいうえおあいうえおあいうえお" + "あいうえおあいうえおあいうえおあいうえおあいうえお"
				+ "あいうえおあいうえおあいうえおあいうえおあいうえお" + "あいうえおあいうえおあいうえおあいうえおあいうえお" + "あいうえおあいうえおあいうえおあいうえおあいうえお"
				+ "あいうえおあいうえおあいうえおあいうえおあいうえお" + "あいうえおあいうえおあいうえおあいうえおあいうえお" + "あいうえおあいうえおあいうえおあいうえおあいうえお"
				+ "あいうえおあいうえおあいうえおあいうえおあいうえお" + "あいうえお";

		assertThat(cm.validate(notOverSize), is(false));
	}

	/**
	 * 異常系テスト {@link quiz.CheckMean#validate(java.lang.String)}
	 *
	 * @note 引数 空白なし 256文字
	 * @note 期待戻り値 true
	 */
	@Test
	public void testNoSpaceOverMaxLength() {

		String overSize = "あいうえおあいうえおあいうえおあいうえおあいうえお" + "あいうえおあいうえおあいうえおあいうえおあいうえお" + "あいうえおあいうえおあいうえおあいうえおあいうえお"
				+ "あいうえおあいうえおあいうえおあいうえおあいうえお" + "あいうえおあいうえおあいうえおあいうえおあいうえお" + "あいうえおあいうえおあいうえおあいうえおあいうえお"
				+ "あいうえおあいうえおあいうえおあいうえおあいうえお" + "あいうえおあいうえおあいうえおあいうえおあいうえお" + "あいうえおあいうえおあいうえおあいうえおあいうえお"
				+ "あいうえおあいうえおあいうえおあいうえおあいうえお" + "あいうえお" + "a";

		assertThat(cm.validate(overSize), is(true));
	}

	/**
	 * 正常系テスト {@link quiz.CheckMean#validate(java.lang.String)}
	 *
	 * @note 引数 半角スペース、全角スペース、タブスペースを含む文字列
	 * @note 期待戻り値 false
	 */
	@Test
	public void testIncludeSpace() {

		assertThat(cm.validate("あい う　え	お"), is(false));
	}

	/**
	 * 異常系テスト {@link quiz.CheckMean#validate(java.lang.String)}
	 *
	 * @note 引数 半角スペース、全角スペース、タブスペースのみの文字列
	 * @note 期待戻り値 true
	 */
	@Test
	public void testOnrySpace() {
		assertThat(cm.validate("   　　　		"), is(true));
	}

}
