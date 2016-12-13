/**
 * @file 2016/12/07
 */
package quiz.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * MySQLデータベース接続クラス
 *
 * @author Yuka Yoshikawa
 *
 */
public class SqlDataStore implements DataStore {

	/**
	 * @接続するDB情報
	 *
	 * @DB名 EnglishWordQuiz
	 * @テーブル名 EnglishWord
	 *
	 * @userID root
	 * @password root
	 *
	 */

	/** JDBCドライバ */
	private final String driver = "com.mysql.jdbc.Driver";

	/** 接続先DB */
	private final String jdbc = "jdbc:mysql://localhost/englishwordquiz?useUnicode=true&characterEncoding=utf8";
	/** 接続ユーザー名 */
	private final String user = "root";
	/** 接続パスワード */
	private final String pass = "root";

	/** セッション */
	private Connection con = null;
	/** プリコンパイルされたSQL文 */
	private Statement ps = null;

	/**
	 * 【要求仕様 A】 DBに接続するメソッド
	 *
	 * @see quiz.model.DataStore#open()
	 */
	@Override
	public void open() throws Exception {

		/* JDBCドライバをロード */
		Class.forName(driver);

		/* DBに接続 */
		con = DriverManager.getConnection(jdbc, user, pass);

	}

	/**
	 * 【要求仕様 A】 DB接続を閉じるメソッド
	 *
	 * @throws java.lang.Exception
	 *             発生する全例外
	 *
	 * @note 全ての例外をthrowsする
	 */
	@Override
	public void close() throws Exception {

		if (ps != null) {
			ps.close();
		}

		if (con != null) {
			con.close();
		}
	}

	/**
	 * 【要求仕様 A】データ全件取得メソッド
	 *
	 * @throws java.lang.Exception
	 *             発生する全例外
	 *
	 * @note 全ての例外をthrowsする
	 */
	@Override
	public ArrayList<EnglishWordBean> getAll() throws Exception {

		String sql = "select * from englishword ";

		ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery(sql);

		ArrayList<EnglishWordBean> allData = new ArrayList<EnglishWordBean>();

		/* データ格納 */
		while (rs.next()) {
			EnglishWordBean bean = new EnglishWordBean();

			bean.setId(rs.getInt("id"));
			bean.setWord(rs.getString("word"));
			bean.setPart(Part.getPart(rs.getString("part")));
			bean.setMean(rs.getString("mean"));
			bean.setUpdateTime(rs.getTimestamp("updated_at"));

			allData.add(bean);
		}

		return allData;
	}

	/**
	 * 【要求仕様 A】データ1件追加メソッド
	 *
	 * @throws java.lang.Exception
	 *             発生する全例外
	 *
	 * @note 全ての例外をthrowsする
	 */
	@Override
	public void insert(EnglishWordBean bean) throws Exception {
		String sql = "insert into englishword (word, part, mean ) values ( '" + bean.getWord() + "' , '"
				+ bean.getPart().toString() + "'  , '" + bean.getMean() + "'";

		ps = con.createStatement();
		ps.executeUpdate(sql);

	}

	/**
	 * 【要求仕様 C】データ1件更新メソッド
	 *
	 * @throws java.lang.Exception
	 *             発生する全例外
	 *
	 * @note 全ての例外をthrowsする
	 */
	@Override
	public void update(EnglishWordBean bean) throws Exception {
		// TODO 【要求仕様 C】データ1件更新メソッド

	}

	/**
	 * 【要求仕様 B】データ1件削除メソッド
	 *
	 * @throws java.lang.Exception
	 *             発生する全例外
	 *
	 * @note 全ての例外をthrowsする
	 */
	@Override
	public void delete(EnglishWordBean bean) throws Exception {
		// TODO 【要求仕様 B】データ1件削除メソッド

	}

	/**
	 * 【要求仕様 A】データ1件検索メソッド
	 *
	 * @throws java.lang.Exception
	 *             発生する全例外
	 *
	 * @note 全ての例外をthrowsする
	 */
	@Override
	public EnglishWordBean searchWord(EnglishWordBean bean) throws Exception {

		String sql = "select * from englishword where word like '" + bean.getWord() + "' " + "and mean like '%"
				+ bean.getMean() + "%'  order by updated_at desc limit 1 ";

		ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery(sql);

		EnglishWordBean searchBean = new EnglishWordBean();

		/* データ表示 */
		if (rs.next()) {
			searchBean.setId(rs.getInt("id"));
			searchBean.setWord(rs.getString("word"));
			searchBean.setPart(Part.getPart(rs.getString("part")));
			searchBean.setMean(rs.getString("mean"));
			searchBean.setUpdateTime(rs.getTimestamp("updated_at"));

		}

		return searchBean;
	}

	/**
	 * 【要求仕様 A】データ1件ランダム取得メソッド
	 *
	 * @throws java.lang.Exception
	 *             発生する全例外
	 *
	 * @note 全ての例外をthrowsする
	 */
	@Override
	public EnglishWordBean getRandom() throws Exception {
		String sql = "select * from englishword order by rand() limit 1 ";

		ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery(sql);

		EnglishWordBean randomBean = new EnglishWordBean();

		if (rs.next()) {
			randomBean.setId(rs.getInt("id"));
			randomBean.setWord(rs.getString("word"));
			randomBean.setPart(Part.getPart(rs.getString("part")));
			randomBean.setMean(rs.getString("mean"));
			randomBean.setUpdateTime(rs.getTimestamp("updated_at"));

		}

		return randomBean;
	}

}
