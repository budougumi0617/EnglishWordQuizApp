/**
 * @file 2016/12/06
 */
package quiz.model;

/**
 * レコードデータ格納クラス
 *
 * @author Yuka Yoshikawa
 *
 */
public class EnglishWordBean {

	/** ID */
	private int id;
	/** 英単語 */
	private String word;
	/** 品詞 */
	private Part part;

	/** 英単語意味 */
	private String mean;
	/** 更新日時 */
	private String updateTime;

	/**
	 * IDのゲッターメソッド
	 *
	 * @return id String型 ID
	 */
	public int getId() {
		return id;
	}

	/**
	 * IDのセッターメソッド
	 *
	 * @param id
	 *            String型 ID
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 英単語のゲッターメソッド
	 *
	 * @return word String型 英単語
	 */
	public String getWord() {
		return word;
	}

	/**
	 * 英単語のセッターメソッド
	 *
	 * @param word
	 *            String型 英単語
	 */
	public void setWord(String word) {
		this.word = word;
	}

	/**
	 * 品詞のゲッターメソッド
	 *
	 * @return part Int型 品詞
	 */
	public Part getPart() {
		return part;
	}

	/**
	 * 品詞のセッターメソッド
	 *
	 * @param part
	 *            Int型 品詞
	 */
	public void setPart(Part part) {
		this.part = part;
	}

	/**
	 * 英単語意味のゲッターメソッド
	 *
	 * @return mean String型 英単語意味
	 */
	public String getMean() {
		return mean;
	}

	/**
	 * 英単語意味のセッターメソッド
	 *
	 * @param mean
	 *            String型 英単語意味
	 */
	public void setMean(String mean) {
		this.mean = mean;
	}

	/**
	 * 更新日時のゲッターメソッド
	 *
	 * @return updateTime String型 更新日時
	 */
	public String getUpdateTime() {
		return updateTime;
	}

	/**
	 * 更新日時のセッターメソッド
	 *
	 * @param updateTime
	 *            String型 更新日時
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

}
