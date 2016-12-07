/**
 *
 */
package quiz.model;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * EnglishWordBeanクラス テスト
 *
 * @author Yuka Yoshikawa
 *
 */
public class EnglishWordBeanTest {

	private EnglishWordBean ewb;
	private Field field;

	/**
	 * 初期設定
	 *
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		ewb = new EnglishWordBean();
	}

	/**
	 * 終了時動作
	 *
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		ewb = null;
	}

	/**
	 * 正常系テスト {@link quiz.model.EnglishWordBean#getId()}
	 *
	 * @note メンバが取得できるか判定する
	 */
	@Test
	public void testGetId() {
		try {
			ewb.setId(500);
			assertThat(ewb.getId(), is(500));
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}

	/**
	 * 正常系テスト {@link quiz.model.EnglishWordBean#setId()}
	 *
	 * @note 引数で受け取った値をメンバにセットできるか判定する
	 */
	@Test
	public void testSetId() {
		try {
			field = EnglishWordBean.class.getDeclaredField("id");
			field.setAccessible(true);

			ewb.setId(100);
			assertThat((Integer) field.get(ewb), is(100));
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}

	/**
	 * 正常系テスト {@link quiz.model.EnglishWordBean#getWord()}
	 *
	 * @note メンバが取得できるか判定する
	 */
	@Test
	public void testGetWord() {
		try {
			ewb.setWord("Sunday");
			assertThat(ewb.getWord(), is("Sunday"));
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}

	/**
	 * 正常系テスト {@link quiz.model.EnglishWordBean#setWord()}
	 *
	 * @note 引数で受け取った値をメンバにセットできるか判定する
	 */
	@Test
	public void testSetWord() {
		try {
			field = EnglishWordBean.class.getDeclaredField("word");
			field.setAccessible(true);

			ewb.setWord("Monday");
			assertThat((String) field.get(ewb), is("Monday"));
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}

	/**
	 * 正常系テスト {@link quiz.model.EnglishWordBean#getPart()}
	 *
	 * @note メンバが取得できるか判定する
	 */
	@Test
	public void testGetPart() {
		try {
			Part part = Part.valueOf("名詞");
			ewb.setPart(part);
			assertThat(ewb.getPart(), is(Part.valueOf("名詞")));
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}

	/**
	 * 正常系テスト {@link quiz.model.EnglishWordBean#setPart()}
	 *
	 * @note 引数で受け取った値をメンバにセットできるか判定する
	 */
	@Test
	public void testSetPart() {
		try {
			field = EnglishWordBean.class.getDeclaredField("part");
			field.setAccessible(true);

			Part part = Part.valueOf("動詞");
			ewb.setPart(part);
			assertThat((Part) field.get(ewb), is(Part.valueOf("動詞")));
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}

	/**
	 * 正常系テスト {@link quiz.model.EnglishWordBean#getMean()}
	 *
	 * @note メンバが取得できるか判定する
	 */
	@Test
	public void testGetMean() {
		try {
			ewb.setMean("承認する");
			assertThat(ewb.getMean(), is("承認する"));
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}

	/**
	 * 正常系テスト {@link quiz.model.EnglishWordBean#setMean()}
	 *
	 * @note 引数で受け取った値をメンバにセットできるか判定する
	 */
	@Test
	public void testSetMean() {
		try {
			field = EnglishWordBean.class.getDeclaredField("mean");
			field.setAccessible(true);

			ewb.setMean("確認する");
			assertThat((String) field.get(ewb), is("確認する"));
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}

	/**
	 * 正常系テスト {@link quiz.model.EnglishWordBean#getUpdateTime()}
	 *
	 * @note メンバが取得できるか判定する
	 */
	@Test
	public void testGetUpdateTime() {
		try {
			ewb.setUpdateTime("2016.12.06-14:37");
			assertThat(ewb.getUpdateTime(), is("2016.12.06-14:37"));
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}

	/**
	 * 正常系テスト {@link quiz.model.EnglishWordBean#setUpdateTime()}
	 *
	 * @note 引数で受け取った値をメンバにセットできるか判定する
	 */
	@Test
	public void testSetUpdateTime() {
		try {
			field = EnglishWordBean.class.getDeclaredField("updateTime");
			field.setAccessible(true);

			ewb.setUpdateTime("2016.12.06-16:57");
			assertThat((String) field.get(ewb), is("2016.12.06-16:57"));
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}

}
