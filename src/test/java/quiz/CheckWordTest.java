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
 * CheckWordクラス テスト
 *
 * @author Yuka Yoshikawa
 *
 */
public class CheckWordTest {

	private CheckWord cw;

	/**
	 * 初期設定
	 *
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		cw = new CheckWord();
	}

	/**
	 * 終了時動作
	 *
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		cw = null;
	}

	/**
	 * 正常系テスト {@link quiz.CheckWord#validate(java.lang.String)}
	 *
	 * @note 引数 半角小文字 英字
	 * @note 期待戻り値 false
	 */
	@Test
	public void testHalfLowerAlphabet() {
		assertThat(cw.validate("one"), is(false));
	}

	/**
	 * 正常系テスト {@link quiz.CheckWord#validate(java.lang.String)}
	 *
	 * @note 引数 半角大文字 英字
	 * @note 期待戻り値 false
	 */
	@Test
	public void testHalfUpperAlpha() {
		assertThat(cw.validate("TWO"), is(false));
	}

	/**
	 * 正常系テスト {@link quiz.CheckWord#validate(java.lang.String)}
	 *
	 * @note 引数 半角大文字小文字混合 英字
	 * @note 期待戻り値 false
	 */
	@Test
	public void testHalfMixAlphabet() {
		assertThat(cw.validate("tHRee"), is(false));
	}

	/**
	 * 正常系テスト {@link quiz.CheckWord#validate(java.lang.String)}
	 *
	 * @note 引数 半角英字 16文字
	 * @note 期待戻り値 false
	 */
	@Test
	public void testMaxLength() {
		assertThat(cw.validate("fourfourfourfour"), is(false));
	}

	/**
	 * 異常系テスト {@link quiz.CheckWord#validate(java.lang.String)}
	 *
	 * @note 引数 半角英字 17文字
	 * @note 期待戻り値 true
	 */
	@Test
	public void testOverMaxLength() {
		assertThat(cw.validate("fivefivefivefivef"), is(true));
	}

	/**
	 * 異常系テスト {@link quiz.CheckWord#validate(java.lang.String)}
	 *
	 * @note 引数 半角英字 半角スペース混合
	 * @note 期待戻り値 true
	 */
	@Test
	public void testIncludeHalfSpace() {
		assertThat(cw.validate("si x"), is(true));
	}

	/**
	 * 異常系テスト {@link quiz.CheckWord#validate(java.lang.String)}
	 *
	 * @note 引数 半角英字 全角スペース混合
	 * @note 期待戻り値 true
	 */
	@Test
	public void testIncludeFullSpace() {
		assertThat(cw.validate("seve　n"), is(true));
	}

	/**
	 * 異常系テスト {@link quiz.CheckWord#validate(java.lang.String)}
	 *
	 * @note 引数 半角英字 タブスペース混合
	 * @note 期待戻り値 true
	 */
	@Test
	public void testIncludeTabSpace() {
		assertThat(cw.validate("eigh		t"), is(true));
	}

	/**
	 * 異常系テスト {@link quiz.CheckWord#validate(java.lang.String)}
	 *
	 * @note 引数 全角英字
	 * @note 期待戻り値 true
	 */
	@Test
	public void testFullAlphabet() {
		assertThat(cw.validate("ｎｉｎｅ"), is(true));
	}

	/**
	 * 異常系テスト {@link quiz.CheckWord#validate(java.lang.String)}
	 *
	 * @note 引数 ひらがなカタカナ漢字
	 * @note 期待戻り値 true
	 */
	@Test
	public void testIncludeJapaneseLang() {
		assertThat(cw.validate("じゅうジュウ十"), is(true));
	}

	/**
	 * 異常系テスト {@link quiz.CheckWord#validate(java.lang.String)}
	 *
	 * @note 引数 未入力
	 * @note 期待戻り値 true
	 */
	@Test
	public void testNotEntered() {
		assertThat(cw.validate(""), is(true));
	}

}
