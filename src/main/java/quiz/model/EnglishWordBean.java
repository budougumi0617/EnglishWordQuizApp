/**
 * @file 2016/12/06
 */
package quiz.model;

import quiz.CheckInput;
import quiz.CheckMean;
import quiz.CheckWord;

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

	private CheckInput checkWord = new CheckWord();
	private CheckInput checkMean = new CheckMean();


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
	 * @throws Exception
	 */
	public void setWord(String word) throws IllegalArgumentException {
		if(checkWord.existsError(word)){
			throw new IllegalArgumentException();
		}
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
	 *            Part列挙型 品詞
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
	 * @throws Exception
	 */
	public void setMean(String mean) throws IllegalArgumentException {
		if(checkMean.existsError(mean)){
			throw new IllegalArgumentException();
		}
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

		if(!updateTime.matches("^\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{1,2}:\\d{1,2}\\.[0]")){
			throw new IllegalArgumentException();
		}
		updateTime = updateTime.substring(0, updateTime.length()-2);
		this.updateTime = updateTime;
	}

}
