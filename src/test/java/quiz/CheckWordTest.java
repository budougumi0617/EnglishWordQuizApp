/**
 * @file 2016/12/06
 */
package quiz;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import quiz.checkInput.CheckWord;

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
	 * @note 期待戻り値 true
	 */
	@Test
	public void testHalfLowerAlphabet() {
		assertThat(cw.validate("one"), is(true));
	}

	/**
	 * 正常系テスト {@link quiz.CheckWord#validate(java.lang.String)}
	 *
	 * @note 引数 半角大文字 英字
	 * @note 期待戻り値 true
	 */
	@Test
	public void testHalfUpperAlpha() {
		assertThat(cw.validate("TWO"), is(true));
	}

	/**
	 * 正常系テスト {@link quiz.CheckWord#validate(java.lang.String)}
	 *
	 * @note 引数 半角大文字小文字混合 英字
	 * @note 期待戻り値 true
	 */
	@Test
	public void testHalfMixAlphabet() {
		assertThat(cw.validate("tHRee"), is(true));
	}

	/**
	 * 正常系テスト {@link quiz.CheckWord#validate(java.lang.String)}
	 *
	 * @note 引数 半角英字 16文字
	 * @note 期待戻り値 true
	 */
	@Test
	public void testMaxLength() {
		assertThat(cw.validate("fourfourfourfour"), is(true));
	}

	/**
	 * 異常系テスト {@link quiz.CheckWord#validate(java.lang.String)}
	 *
	 * @note 引数 半角英字 17文字
	 * @note 期待戻り値 false
	 */
	@Test
	public void testOverMaxLength() {
		assertThat(cw.validate("fivefivefivefivef"), is(false));
	}

	/**
	 * 異常系テスト {@link quiz.CheckWord#validate(java.lang.String)}
	 *
	 * @note 引数 半角英字 半角スペース混合
	 * @note 期待戻り値 false
	 */
	@Test
	public void testIncludeHalfSpace() {
		assertThat(cw.validate("si x"), is(false));
	}

	/**
	 * 異常系テスト {@link quiz.CheckWord#validate(java.lang.String)}
	 *
	 * @note 引数 半角英字 全角スペース混合
	 * @note 期待戻り値 false
	 */
	@Test
	public void testIncludeFullSpace() {
		assertThat(cw.validate("seve　n"), is(false));
	}

	/**
	 * 異常系テスト {@link quiz.CheckWord#validate(java.lang.String)}
	 *
	 * @note 引数 半角英字 タブスペース混合
	 * @note 期待戻り値 false
	 */
	@Test
	public void testIncludeTabSpace() {
		assertThat(cw.validate("eigh		t"), is(false));
	}

	/**
	 * 異常系テスト {@link quiz.CheckWord#validate(java.lang.String)}
	 *
	 * @note 引数 全角英字
	 * @note 期待戻り値 false
	 */
	@Test
	public void testFullAlphabet() {
		assertThat(cw.validate("ｎｉｎｅ"), is(false));
	}

	/**
	 * 異常系テスト {@link quiz.CheckWord#validate(java.lang.String)}
	 *
	 * @note 引数 ひらがなカタカナ漢字
	 * @note 期待戻り値 false
	 */
	@Test
	public void testIncludeJapaneseLang() {
		assertThat(cw.validate("emじゅうジュウ十mo"), is(false));
	}

	/**
	 * 異常系テスト {@link quiz.CheckWord#validate(java.lang.String)}
	 *
	 * @note 引数 未入力
	 * @note 期待戻り値 false
	 */
	@Test
	public void testNotEntered() {
		assertThat(cw.validate(""), is(false));
	}

	/**
	 * 異常系テスト {@link quiz.CheckWord#validate(java.lang.String)}
	 *
	 * @note 引数 null
	 * @note 期待戻り値 false
	 */
	@Test
	public void testGetNull() {
		assertThat(cw.validate(null), is(false));
	}

	/**
	 * 正常系テスト {@link quiz.CheckWord#validate(java.lang.String)}
	 *
	 * @note 引数 ワイルドカード%
	 * @note 期待戻り値 true
	 */
	@Test
	public void testWildCard() {
		assertThat(cw.validate("%"), is(true));
	}


}
