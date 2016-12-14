/**
 * @file 2016/12/05
 */
package quiz;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.io.OutputStream;
import java.lang.reflect.Field;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import gnu.io.CommPort;
import gnu.io.SerialPort;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ SendSerialData.class, PortIdentifierWrapper.class })

/**
 * SendSerialDataクラス テスト
 *
 * @author Yuka Yoshikawa
 *
 */
public class SendSerialDataTest {

	@InjectMocks
	private SendSerialData ssd;

	@Mock
	private CommPort mockCommPort;
	@Mock
	private SerialPort mockSerialPort;
	@Mock
	private OutputStream mockOutputStream;

	private Field field;

	/**
	 * 初期設定
	 *
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		ssd = new SendSerialData();

		/** Mockitの作成 */
		mockCommPort = mock(CommPort.class);
		mockSerialPort = mock(SerialPort.class);
		mockOutputStream = mock(OutputStream.class);

		MockitoAnnotations.initMocks(this);
	}

	/**
	 * 終了時動作
	 *
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		ssd = null;
		mockCommPort = null;
		mockSerialPort = null;
		mockOutputStream = null;
	}

	/**
	 * 正常系テスト {@link quiz.SendSerialData#open()}
	 *
	 * @note 処理が全て通るか判定する
	 */
	@Test
	public void testOpen() {
		try {

			ssd.setResultMessage("correct!");
			ssd.setAnswerWord("goodbye");
			ssd.setCommPort("COM1");

			/* CommPortIdentifierのstaticメソッドを呼び出すためにMock化する */
			PowerMockito.mockStatic(PortIdentifierWrapper.class);
			when(PortIdentifierWrapper.getCommPort((String) anyObject())).thenReturn(mockSerialPort);

			doNothing().when(mockSerialPort).setSerialPortParams(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt(),
					Mockito.anyInt());
			doNothing().when(mockSerialPort).setFlowControlMode(Mockito.anyInt());

			ssd.open();

			PowerMockito.verifyStatic(Mockito.times(1));

			ssd.close();

		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}

	/**
	 * 正常系テスト {@link quiz.SendSerialData#stream()}
	 *
	 * @note 処理が全て通るか判定する
	 */
	@Test
	public void testStream() {
		try {
			ssd.setResultMessage("correct!");
			ssd.setAnswerWord("goodbye");
			ssd.setCommPort("COM1");

			when(mockSerialPort.getOutputStream()).thenReturn(mockOutputStream);

			ssd.stream();

		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}

	/**
	 * 正常系テスト {@link quiz.SendSerialData#close()}
	 *
	 * @note 例外を排出せずに処理が通るか判定する
	 */
	@Test
	public void testClose() {
		try {
			ssd.setResultMessage("correct!");
			ssd.setAnswerWord("goodbye");
			ssd.setCommPort("COM1");

			ssd.close();

		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}

	/**
	 * 正常系テスト {@link quiz.SendSerialData#setCommPort()}
	 *
	 * @note 引数で受け取った値をメンバにセットできるか判定する
	 */
	@Test
	public void testSetCommPort() {
		try {
			field = SendSerialData.class.getDeclaredField("commPort");
			field.setAccessible(true);

			ssd.setCommPort("COM1");
			assertThat((String) field.get(ssd), is("COM1"));
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}

	/**
	 * 正常系テスト {@link quiz.SendSerialData#setResultMessage(String)}
	 *
	 * @note 引数で受け取った値をメンバにセットできるか判定する
	 */
	@Test
	public void testSetResultMessage() {
		try {
			field = SendSerialData.class.getDeclaredField("result");
			field.setAccessible(true);

			ssd.setResultMessage("correct!");
			assertThat((String) field.get(ssd), is("correct!"));
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}

	/**
	 * 正常系テスト {@link quiz.SendSerialData#setAnswerWord(String)}
	 *
	 * @note 引数で受け取った値をメンバにセットできるか判定する
	 */
	@Test
	public void testSetAnswerWord() {
		try {
			field = SendSerialData.class.getDeclaredField("answer");
			field.setAccessible(true);

			ssd.setAnswerWord("goodbye");
			assertThat((String) field.get(ssd), is("goodbye"));
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}

}
