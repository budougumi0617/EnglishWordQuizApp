/**
 * @file 2016/12/06
 */
package quiz;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.stream.IntStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import quiz.checkInput.CheckMean;

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
	 * @note 期待戻り値 true
	 */
	@Test
	public void testNoSpace() {
		assertThat(cm.validate("確認する"), is(true));
	}

	/**
	 * 正常系テスト {@link quiz.CheckMean#validate(java.lang.String)}
	 *
	 * @note 引数 空白なし 255文字
	 * @note 期待戻り値 true
	 */
	@Test
	public void testNoSpaceMaxLength() {

		StringBuilder notOverSize = new StringBuilder();

		IntStream.range(0, 51).forEach(i -> notOverSize.append("あいうえお"));

		assertThat(cm.validate(notOverSize.toString()), is(true));
	}

	/**
	 * 正常系テスト {@link quiz.CheckMean#validate(java.lang.String)}
	 *
	 * @note 引数 空白なし 256文字
	 * @note 期待戻り値 false
	 */
	@Test
	public void testNoSpaceOverMaxLength() {

		StringBuilder overSize = new StringBuilder();

		IntStream.range(0, 51).forEach(i -> overSize.append("あいうえお"));
		overSize.append("あ");

		assertThat(cm.validate(overSize.toString()), is(false));
	}

	/**
	 * 正常系テスト {@link quiz.CheckMean#validate(java.lang.String)}
	 *
	 * @note 引数 半角スペース、全角スペース、タブスペースを含む文字列
	 * @note 期待戻り値 true
	 */
	@Test
	public void testIncludeSpace() {

		assertThat(cm.validate("あい う　え	お"), is(true));
	}

	/**
	 * 正常系テスト {@link quiz.CheckMean#validate(java.lang.String)}
	 *
	 * @note 引数 半角スペース、全角スペース、タブスペースのみの文字列
	 * @note 期待戻り値 false
	 */
	@Test
	public void testOnrySpace() {
		assertThat(cm.validate("   　　　		"), is(false));
	}

	/**
	 * 正常系テスト {@link quiz.CheckMean#validate(java.lang.String)}
	 *
	 * @note 引数 null
	 * @note 期待戻り値 false
	 */
	@Test
	public void testGetNull() {
		assertThat(cm.validate(null), is(false));
	}

}
