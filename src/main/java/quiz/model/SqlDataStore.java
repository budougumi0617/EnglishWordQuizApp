/**
 * @file 2016/12/07
 */
package quiz.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import quiz.Enum.Part;

/**
 * MySQLデータベース接続クラス
 *
 * @author Yuka Yoshikawa
 *
 */
public class SqlDataStore extends Observable implements DataStore {

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
	private final String jdbc = "jdbc:mysql://localhost/englishwordquiz?useUnicode=true&characterEncoding=utf8&useSSL=false";
	/** 接続ユーザー名 */
	private final String user = "root";
	/** 接続パスワード */
	private final String pass = "root";

	/** 使用テーブル名 */
	private final String table = "englishword";

	/** テーブルカラム名 */
	private final String id = "id";
	private final String word = "word";
	private final String part = "part";
	private final String mean = "mean";
	private final String update = "updated_at";

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

		if (con == null) {
			/* DBに接続 */
			con = DriverManager.getConnection(jdbc, user, pass);
		}

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
			con = null;
		}
	}

	/**
	 * 【要求仕様 A】データ全件取得メソッド
	 *
	 * @throws java.lang.Exception
	 *             発生する全例外
	 *
	 * @return allData ArrayList<EnglishWordBean> 全データ
	 *
	 * @note 全ての例外をthrowsする
	 *
	 * @note allDataは更新降順にソートを行う
	 */
	@Override
	public ArrayList<EnglishWordBean> getAll() throws Exception {

		String sql = "select * from " + table + " order by " + update + " desc ";

		if (con != null) {
			ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery(sql);

			ArrayList<EnglishWordBean> allData = new ArrayList<EnglishWordBean>();

			/* データ格納 */
			while (rs.next()) {
				allData.add(getBean(rs));
			}

			return allData;

		} else {
			throw new IllegalStateException("接続が確立されていません。");
		}
	}

	/**
	 * 【要求仕様 A】データ1件追加メソッド
	 *
	 * @throws java.lang.Exception
	 *             発生する全例外
	 *
	 * @param bean
	 *            EnglishWordBean
	 *
	 * @note 全ての例外をthrowsする
	 */
	@Override
	public void insert(EnglishWordBean bean) throws Exception {

		String sql = "insert into " + table + " ( " + word + ", " + part + ", " + mean + " ) values ( '"
				+ bean.getWord() + "' , '" + bean.getPart().toString() + "' , '" + bean.getMean() + "' )";

		if (con != null) {
			ps = con.createStatement();
			ps.executeUpdate(sql);

			/** 変更通知をObserverへ出す */
			setChanged();
			notifyObservers(getAll());
			clearChanged();
		} else {
			throw new IllegalStateException("接続が確立されていません。");
		}

	}

	/**
	 * 【要求仕様 C】データ1件更新メソッド
	 *
	 * @throws java.lang.Exception
	 *             発生する全例外
	 *
	 * @param bean
	 *            EnglishWordBean
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
	 * @param bean
	 *            EnglishWordBean
	 *
	 * @note 全ての例外をthrowsする
	 */
	@Override
	public void delete(EnglishWordBean bean) throws Exception {
		String sql = "delete from " + table + " where " + id + " = '" + bean.getId() + "'";

		if (con != null) {
			ps = con.createStatement();
			ps.executeUpdate(sql);

			/** 変更通知をObserverへ出す */
			setChanged();
			notifyObservers(getAll());
			clearChanged();
		} else {
			throw new IllegalStateException("接続が確立されていません。");
		}
	}

	/**
	 * 【要求仕様 A】データ1件検索メソッド
	 *
	 * @throws java.lang.Exception
	 *             発生する全例外
	 *
	 * @param bean
	 *            EnglishWordBean
	 *
	 * @return searchBean EnglishWordBean取得データ1件 該当データがなかった場合nullを返す
	 * @note 検索に該当するデータが複数あった場合は、更新日時が最も新しいものを返す
	 * @note 全ての例外をthrowsする
	 */
	@Override
	public EnglishWordBean searchWord(EnglishWordBean bean) throws Exception {

		String sql;

		if (bean.getPart() == null) {

			/* 答え合わせに使うSQL文
			 * 入力値に該当する英単語、出題文の意味を含む意味に該当するデータを検索
			 */
			sql = "select * from " + table + " where word like '" + bean.getWord() + "' and mean like '%"
					+ bean.getMean().replaceAll("%", "\\%") + "%'  order by " + update + " desc limit 1 ";
		} else {


			/* 重複判定に使うSQL文
			 * 入力値に該当する英単語、品詞に該当するデータを検索
			 */
			sql = "select * from " + table + " where word like '" + bean.getWord() + "' and part like '"
					+ bean.getPart().toString() + "' order by " + update + " desc limit 1 ";
		}

		if (con != null) {
			ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery(sql);

			if (rs.next()) {
				return getBean(rs);
			}
			return null;

		} else {
			throw new IllegalStateException("接続が確立されていません。");
		}
	}

	/**
	 * 【要求仕様 A】データ1件ランダム取得メソッド
	 *
	 * @throws java.lang.Exception
	 *             発生する全例外
	 *
	 * @return randomBean EnglishWordBean取得データ1件 該当データがなかった場合nullを返す
	 *
	 * @note 全ての例外をthrowsする
	 */
	@Override
	public EnglishWordBean getRandom() throws Exception {
		String sql = "select * from " + table + " order by rand() limit 1 ";

		if (con != null) {
			ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery(sql);

			if (rs.next()) {
				return getBean(rs);
			}
			return null;

		} else {
			throw new IllegalStateException("接続が確立されていません。");
		}
	}

	/**
	 * Observerの追加
	 *
	 * @param o
	 *            Observer
	 */
	public void addObserver(Observer o) {
		super.addObserver(o);
	};

	/**
	 * sql文で受け取ったデータからデータ1件分のEnglishWordBeanのインスタンスを返す
	 *
	 * @param rs
	 *            SQL文により受けとったデータ
	 * @return EnglishWordBean データ1件を格納したBean
	 * @throws IllegalArgumentException
	 *             不正な引数を受け取った際の例外
	 * @throws SQLException
	 *             SQLに関連する例外
	 */
	public EnglishWordBean getBean(ResultSet rs) throws IllegalArgumentException, SQLException {

		return new EnglishWordBean().setId(rs.getInt(id)).setWord(rs.getString(word))
				.setPart(Part.getPart(rs.getString(part))).setMean(rs.getString(mean))
				.setUpdateTime(rs.getTimestamp(update));

	}

}
