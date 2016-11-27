import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * @file
 * @par File Name:
 * SampleTest.java
 * @author budougumi0617
 * @date Created on 2016/11/28
 */

/**
 * @author budougumi0617
 * @note no comment
 */
public class SampleTest {

	@Test
	public void test() {
		assertThat(Sample.sampleMethod(), is(10));
	}

}
