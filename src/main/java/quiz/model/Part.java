/**
 * @file 2016/12/06
 */
package quiz.model;

/**
 * 品詞の列挙型を定義
 *
 * @author Yuka Yoshikawa
 *
 */
public enum Part {

	// 名詞, 代名詞, 動詞, 形容詞, 副詞, 冠詞, 助動詞, 前置詞, 疑問詞, 接続詞,

	noun("名詞"), pronoun("代名詞"), verb("動詞"), adjective("形容詞"), adverb("副詞"), article("冠詞"), modalverb(
			"助動詞"), preposition("前置詞"), interrogatives("疑問詞"), conjunction("接続詞"),;

	/** メンバ変数の定義 */
	private String name;

	/** コンストラクタの実装 */
	private Part(String name) {
		this.name = name;
	}

	/** メソッドのオーバーライド */
	public String toString() {
		return name;
	}

	/**
	 * 品詞名に一致するPart変数を返す
	 *
	 * @param input
	 *            入力された品詞名
	 * @return 入力値に一致するPart変数、該当しなかった場合はnullを返す
	 */
	public static Part getPart(String input) {
		Part[] parts = Part.values();
		for (Part part : parts) {
			if (part.toString().equals(input)) {
				return part;
			}
		}
		return null;
	}

}