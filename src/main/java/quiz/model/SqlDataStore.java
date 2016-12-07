/**
 *
 */
package quiz.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * MySQLデータベース接続クラス
 *
 * @author Yuka Yoshikawa
 *
 */
public class SqlDataStore implements DataStore {

	/** セッション */
	Connection con = null;
	/** プリコンパイルされたSQL文 */
	PreparedStatement ps = null;

	/**
	 * 【要求仕様 A】
	 *
	 * @see quiz.model.DataStore#open()
	 */
	@Override
	public void open() throws Exception {

		/* JDBCドライバをロード */
		Class.forName("com.mysql.jdbc.Driver");

		/* DBに接続 */
		con = DriverManager.getConnection(
				"jdbc:mysql://localhost/englishwordquiz?autoReconnect = true & use SSL = false", "root", "root");

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

	}

	/**
	 * 【要求仕様 A】
	 *
	 * @see quiz.model.DataStore#close()
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
	 * 【要求仕様 A】
	 *
	 * @see quiz.model.DataStore#getAll()
	 */
	@Override
	public ArrayList<EnglishWordBean> getAll() throws Exception {

		String sql = "select * from englishword ";

		ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery(sql);

		ArrayList<EnglishWordBean> allData = new ArrayList<EnglishWordBean>();

		/* データ表示 */
		while (rs.next()) {
			EnglishWordBean bean = new EnglishWordBean();

			bean.setId(rs.getInt("id"));
			bean.setWord(rs.getString("word"));
			bean.setPart(Part.valueOf(rs.getString("part")));
			bean.setMean(rs.getString("mean"));
			bean.setUpdateTime(rs.getString("updated_at"));

			allData.add(bean);
		}

		return allData;
	}

	/**
	 * 【要求仕様 A】
	 *
	 * @see quiz.model.DataStore#insert(quiz.model.EnglishWordBean)
	 */
	@Override
	public void insert(EnglishWordBean bean) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

	}

	/**
	 * 【要求仕様 C】
	 *
	 * @see quiz.model.DataStore#update(quiz.model.EnglishWordBean)
	 */
	@Override
	public void update(EnglishWordBean bean) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

	}

	/**
	 * 【要求仕様 B】 (非 Javadoc)
	 *
	 * @see quiz.model.DataStore#delete(quiz.model.EnglishWordBean)
	 */
	@Override
	public void delete(EnglishWordBean bean) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

	}

	/**
	 * 【要求仕様 A】
	 *
	 * @see quiz.model.DataStore#searchWord(quiz.model.EnglishWordBean)
	 */
	@Override
	public EnglishWordBean searchWord(EnglishWordBean bean) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	/**
	 * 【要求仕様 A】
	 *
	 * @see quiz.model.DataStore#getRandom()
	 */
	@Override
	public EnglishWordBean getRandom() throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
