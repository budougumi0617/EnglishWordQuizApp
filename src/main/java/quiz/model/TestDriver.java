/**
 * @file test 2016/12/07
 */
package quiz.model;

import java.util.ArrayList;

/**
 * @author Yuka Yoshikawa
 *
 */
public class TestDriver {

	/**
	 * @param args
	 */

	public static void main(String[] args) {
		SqlDataStore sds = new SqlDataStore();

		try {
			sds.open();
			ArrayList<EnglishWordBean> allData = sds.getAll();
			sds.close();

			/** 全データ表示 */
			for (int i = 0; i < allData.size(); i++) {
				System.out.println("ID：" + allData.get(i).getId() + "  品詞：" + allData.get(i).getPart() + "  意味："
						+ allData.get(i).getMean() + "  更新日時：" + allData.get(i).getUpdateTime());

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
