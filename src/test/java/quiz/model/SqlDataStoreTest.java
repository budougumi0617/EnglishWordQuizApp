/**
 * @file 2016/12/08
 */
package quiz.model;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.File;
import java.lang.reflect.Modifier;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import quiz.Enum.Part;

/**
 * MySQLデータベース接続クラスのテスト
 *
 * @author Yuka Yoshikawa
 *
 */

public class SqlDataStoreTest {

	/** SqlDataStoreクラスのconnection先のリフレクション */
	private java.lang.reflect.Field jdbc;

	/** SqlDataStoreクラスのpasswordリフレクション */
	private java.lang.reflect.Field pass;

	/** Connectionnクラスのリフレクション */
	private java.lang.reflect.Field con;

	/** DBUnitテスト用Connection */
	private IDatabaseConnection dbconn;

	/** テストデータバックアップファイル */
	private File file;

	/** テスト対象MySQLデータストアクラス */
	private SqlDataStore sds;

	/**
	 * 初期設定
	 *
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

		sds = new SqlDataStore();
		java.sql.Connection connection = getConnection();

		/** SqlDataStoreのConnectionの書き換え */
		con = SqlDataStore.class.getDeclaredField("con");
		con.setAccessible(true);
		con.set(sds, connection);

		/** DBコンフィグ設定 */
		dbconn = new DatabaseConnection(connection);
		DatabaseConfig config = dbconn.getConfig();
		config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());

		// /** テストデータバックアップ */
		// QueryDataSet partialDataSet = new QueryDataSet(dbconn);

		// partialDataSet.addTable("englishword");
		// file = File.createTempFile("escape", ".xml");
		// FlatXmlDataSet.write(partialDataSet, new FileOutputStream(file));

		/** XmlテストデータをテストDBに入れる */
		IDataSet dataset = new FlatXmlDataSetBuilder().build(new File("testData.xml"));
		DatabaseOperation.CLEAN_INSERT.execute(dbconn, dataset);

	}

	/**
	 * テストDBへ接続する
	 *
	 * @return testDBへのconnection
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static java.sql.Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");

		String password = System.getProperty("os.name").toLowerCase().matches(".*windows.*") ? "root" : "";
		return DriverManager.getConnection("jdbc:mysql://localhost/test?useUnicode=true&characterEncoding=utf8", "root",
				password);
	}

	/**
	 * 終了時動作
	 *
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {

		// /** テストデータを入れる */
		// IDataSet dataset = new FlatXmlDataSetBuilder().build(file);
		// DatabaseOperation.CLEAN_INSERT.execute(dbconn, dataset);

		if (con != null) {
			sds.close();

		}

	}

	/**
	 * 正常系テスト {@link quiz.model.SqlDataStore#open()} のためのテスト・メソッド。
	 *
	 * @note 処理が全て通るか判定する
	 */
	@Ignore
	public void testOpen() {

		try {

			/** SqlDataStoreのConnection先の書き換え */
			jdbc = sds.getClass().getDeclaredField("jdbc");
			jdbc.setAccessible(true);
			java.lang.reflect.Field jdbcmodifiersField = java.lang.reflect.Field.class.getDeclaredField("modifiers");
			jdbcmodifiersField.setAccessible(true);
			jdbcmodifiersField.setInt(jdbc, jdbc.getModifiers() & ~Modifier.PRIVATE & ~Modifier.FINAL);

			jdbc.set(sds, "jdbc:mysql://localhost/test?useUnicode=true&characterEncoding=utf8");

			/** SqlDataStoreのpasswordの書き換え */
			pass = sds.getClass().getDeclaredField("pass");
			pass.setAccessible(true);
			java.lang.reflect.Field passmodifiersField = java.lang.reflect.Field.class.getDeclaredField("modifiers");
			passmodifiersField.setAccessible(true);
			passmodifiersField.setInt(pass, pass.getModifiers() & ~Modifier.PRIVATE & ~Modifier.FINAL);

			String password = System.getProperty("os.name").toLowerCase().matches(".*windows.*") ? "root" : "";
			pass.set(sds, password);

			sds.close();
			sds.open();
			sds.close();

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/**
	 * 正常系テスト {@link quiz.model.SqlDataStore#close()} のためのテスト・メソッド。
	 *
	 * @note 処理が全て通るか判定する
	 */
	@Test
	public void testClose() {

		try {
			sds.close();

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/**
	 * 正常系テスト {@link quiz.model.SqlDataStore#getAll()} のためのテスト・メソッド。
	 *
	 * @note testDBのenglishwordテーブルの全データを取得できるか確認
	 */
	@Test
	public void testGetAll() {

		try {
			ArrayList<EnglishWordBean> list = sds.getAll();

			assertThat(list.size(), is(4));

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/**
	 * 正常系テスト {@link quiz.model.SqlDataStore#insert()} のためのテスト・メソッド。
	 *
	 * @note testDBのenglishwordテーブルにデータを1件追加できるか確認
	 */
	@Test
	public void testInsert() {
		try {
			EnglishWordBean bean = new EnglishWordBean();

			bean.setWord("soccer");
			bean.setPart(Part.getPart("名詞"));
			bean.setMean("サッカー");

			sds.insert(bean);

			assertThat(sds.getAll().size(), is(5));

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/**
	 * {@link quiz.model.SqlDataStore#update(quiz.model.EnglishWordBean)}
	 * のためのテスト・メソッド。
	 */
	@Ignore
	public void testUpdate() {
		fail("まだ実装されていません"); // TODO
	}

	/**
	 * {@link quiz.model.SqlDataStore#delete(quiz.model.EnglishWordBean)}
	 * のためのテスト・メソッド。
	 */
	@Test
	public void testDelete() {
		try {
			EnglishWordBean bean = new EnglishWordBean();

			bean.setWord("cat");
			bean.setPart(Part.getPart("名詞"));
			bean.setMean("ねこ");

			sds.delete(bean);

			assertThat(sds.getAll().size(), is(3));

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/**
	 * 正常系テスト {@link quiz.model.SqlDataStore#searchWord()} のためのテスト・メソッド。
	 *
	 * @note testDBのenglishwordテーブルからデータを1件検索できるか確認
	 */
	@Test
	public void testSearchWord() {
		try {
			EnglishWordBean bean = new EnglishWordBean();

			bean.setWord("cat");
			bean.setMean("ねこ");

			assertNotNull(sds.searchWord(bean));

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/**
	 * 正常系テスト {@link quiz.model.SqlDataStore#getRandom()} のためのテスト・メソッド。
	 *
	 * @note testDBのenglishwordテーブルからデータを1件取得できるか確認
	 */
	@Test
	public void testGetRandom() {
		try {

			EnglishWordBean resultBean = sds.getRandom();

			assertNotNull(resultBean);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
