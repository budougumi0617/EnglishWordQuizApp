/**
 * @file 2016/12/15
 */
package quiz.controller;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JButton;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.UnsupportedCommOperationException;
import quiz.model.DataStore;
import quiz.model.EnglishWordBean;
import quiz.model.SqlDataStore;
import quiz.output.SendSerialData;
import quiz.view.AddDialog;
import quiz.view.DeleteDialog;
import quiz.view.ErrorDialog;
import quiz.view.MainFrame;
import quiz.view.ManageDialog;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ Controller.class, ErrorDialog.class })
/**
 * Controllerクラス テスト
 *
 * @author Yuka Yoshikawa
 *
 */
public class ControllerTest {

	@InjectMocks
	private Controller ctrl;

	@Mock
	private DataStore mockDataStore;
	@Mock
	private SqlDataStore mockSqlDataStore;
	@Mock
	private ActionEvent mockActionEvent;
	@Mock
	private MainFrame mockMainFrame;
	@Mock
	private ManageDialog mockManageDialog;
	@Mock
	private AddDialog mockAddDialog;
	@Mock
	private DeleteDialog mockDeleteDialog;
	@Mock
	private JButton mockJButton;

	/**
	 * 初期設定
	 *
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

		ctrl = new Controller(mockSqlDataStore);

		/** Mockitの作成 */
		mockDataStore = mock(DataStore.class);
		mockSqlDataStore = mock(SqlDataStore.class);
		mockActionEvent = mock(ActionEvent.class);
		mockMainFrame = mock(MainFrame.class);
		mockManageDialog = mock(ManageDialog.class);
		mockAddDialog = mock(AddDialog.class);
		mockDeleteDialog = mock(DeleteDialog.class);
		mockJButton = mock(JButton.class);

		MockitoAnnotations.initMocks(this);
	}

	/**
	 * 終了時動作
	 *
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		ctrl = null;
		mockDataStore = null;
		mockSqlDataStore = null;
		mockActionEvent = null;
		mockMainFrame = null;
		mockManageDialog = null;
		mockAddDialog = null;
		mockDeleteDialog = null;
		mockJButton = null;
	}

	/**
	 * 正常系テスト
	 * {@link quiz.controller.Controller#Controller(quiz.model.DataStore)}
	 *
	 * @note 処理が全て通るか判定する
	 */
	@Test
	public void testController() {
		try {
			ctrl = new Controller(mockSqlDataStore);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/**
	 * 正常系テスト
	 * {@link quiz.controller.Controller#btMakeQuizAction(java.awt.event.ActionEvent)}
	 *
	 * @note 処理が全て通るか判定する
	 */
	@Test
	public void testBtMakeQuizAction() {
		try {
			when(mockActionEvent.getSource()).thenReturn(mockJButton);
			when(mockJButton.getParent()).thenReturn(mockMainFrame);

			ctrl.btMakeQuizAction(mockActionEvent);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/**
	 * 異常系テスト
	 * {@link quiz.controller.Controller#btMakeQuizAction(java.awt.event.ActionEvent)}
	 *
	 * @note SQLException発生時にErrorDialogクラスのstatic show()メソッドが1度呼ばれているか判定
	 */
	@Test
	public void testBtMakeQuizActionCatchSQLException() {
		try {
			doThrow(new SQLException()).when(mockDataStore).getRandom();

			PowerMockito.mockStatic(ErrorDialog.class);

			ctrl.btMakeQuizAction(mockActionEvent);

		} catch (SQLException e) {
			PowerMockito.verifyStatic(Mockito.times(1));

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/**
	 * 異常系テスト
	 * {@link quiz.controller.Controller#btMakeQuizAction(java.awt.event.ActionEvent)}
	 *
	 * @note NullPointerException発生時にErrorDialogクラスのstatic
	 *       show()メソッドが1度呼ばれているか判定
	 */
	@Test
	public void testBtMakeQuizActionCatchNullPointerException() {
		try {
			doThrow(new NullPointerException()).when(mockDataStore).getRandom();

			PowerMockito.mockStatic(ErrorDialog.class);

			ctrl.btMakeQuizAction(mockActionEvent);

		} catch (NullPointerException e) {
			PowerMockito.verifyStatic(Mockito.times(1));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/**
	 * 異常系テスト
	 * {@link quiz.controller.Controller#btMakeQuizAction(java.awt.event.ActionEvent)}
	 *
	 * @note Exception発生時にErrorDialogクラスのstatic show()メソッドが1度呼ばれているか判定
	 */
	@Test
	public void testBtMakeQuizActionCatchException() {
		try {
			doThrow(new Exception()).when(mockDataStore).getRandom();

			PowerMockito.mockStatic(ErrorDialog.class);

			ctrl.btMakeQuizAction(mockActionEvent);

		} catch (Exception e) {
			PowerMockito.verifyStatic(Mockito.times(1));
		}
	}

	/**
	 * 正常系テスト
	 * {@link quiz.controller.Controller#btManageAction(java.awt.event.ActionEvent)}
	 *
	 * @note 処理が全て通るか判定する
	 */
	@Test
	public void testBtManageAction() {
		try {
			when(mockActionEvent.getSource()).thenReturn(mockJButton);
			when(mockJButton.getParent()).thenReturn(mockMainFrame);

			ctrl.btMakeQuizAction(mockActionEvent);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/**
	 * 異常系テスト
	 * {@link quiz.controller.Controller#btManageAction(java.awt.event.ActionEvent)}
	 *
	 * @note SQLException発生時にErrorDialogクラスのstatic show()メソッドが1度呼ばれているか判定
	 */
	@Test
	public void testBtManageActionCatchSQLException() {
		try {
			doThrow(new SQLException()).when(mockDataStore).getRandom();

			PowerMockito.mockStatic(ErrorDialog.class);

			ctrl.btMakeQuizAction(mockActionEvent);

		} catch (SQLException e) {
			PowerMockito.verifyStatic(Mockito.times(1));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/**
	 * 異常系テスト
	 * {@link quiz.controller.Controller#btManageAction(java.awt.event.ActionEvent)}
	 *
	 * @note Exception発生時にErrorDialogクラスのstatic show()メソッドが1度呼ばれているか判定
	 */
	@Test
	public void testBtManageActionCatchException() {
		try {
			doThrow(new Exception()).when(mockDataStore).getAll();

			PowerMockito.mockStatic(ErrorDialog.class);

			ctrl.btManageAction(mockActionEvent);

		} catch (Exception e) {
			PowerMockito.verifyStatic(Mockito.times(1));
		}
	}

	/**
	 * 正常系テスト
	 * {@link quiz.controller.Controller#btAnswerAction(java.awt.event.ActionEvent)}
	 *
	 * @note 処理が全て通るか判定する
	 */
	@Test
	public void testBtAnswerAction() {
		try {
			when(mockActionEvent.getSource()).thenReturn(mockJButton);
			when(mockJButton.getParent()).thenReturn(mockMainFrame);

			ctrl.btMakeQuizAction(mockActionEvent);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/**
	 * 異常系テスト
	 * {@link quiz.controller.Controller#btAnswerAction(java.awt.event.ActionEvent)}
	 *
	 * @note NoSuchPortException発生時にErrorDialogクラスのstatic show()メソッドが1度呼ばれているか判定
	 */
	@Test
	public void testBtAnswerActionCatchNoSuchPortException() {
		try {

			when(mockActionEvent.getSource()).thenReturn(mockJButton);
			when(mockJButton.getParent()).thenReturn(mockMainFrame);

			SendSerialData mockSendSerialData = Mockito.mock(SendSerialData.class);
			PowerMockito.whenNew(SendSerialData.class).withNoArguments().thenReturn(mockSendSerialData);

			doThrow(new NoSuchPortException()).when(mockSendSerialData).open();

			PowerMockito.mockStatic(ErrorDialog.class);

			ctrl.btAnswerAction(mockActionEvent);

		} catch (NoSuchPortException e) {
			PowerMockito.verifyStatic(Mockito.times(1));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/**
	 * 異常系テスト
	 * {@link quiz.controller.Controller#btAnswerAction(java.awt.event.ActionEvent)}
	 *
	 * @note PortInUseException発生時にErrorDialogクラスのstatic show()メソッドが1度呼ばれているか判定
	 */
	@Test
	public void testBtAnswerActionCatchPortInUseException() {
		try {

			when(mockActionEvent.getSource()).thenReturn(mockJButton);
			when(mockJButton.getParent()).thenReturn(mockMainFrame);

			SendSerialData mockSendSerialData = Mockito.mock(SendSerialData.class);
			PowerMockito.whenNew(SendSerialData.class).withNoArguments().thenReturn(mockSendSerialData);
			doThrow(new PortInUseException()).when(mockSendSerialData).open();

			PowerMockito.mockStatic(ErrorDialog.class);

			ctrl.btAnswerAction(mockActionEvent);

		} catch (PortInUseException e) {
			PowerMockito.verifyStatic(Mockito.times(1));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/**
	 * 異常系テスト
	 * {@link quiz.controller.Controller#btAnswerAction(java.awt.event.ActionEvent)}
	 *
	 * @note UnsupportedCommOperationException発生時にErrorDialogクラスのstatic
	 *       show()メソッドが1度呼ばれているか判定
	 */
	@Test
	public void testBtAnswerActionCatchUnsupportedCommOperationException() {
		try {

			when(mockActionEvent.getSource()).thenReturn(mockJButton);
			when(mockJButton.getParent()).thenReturn(mockMainFrame);

			SendSerialData mockSendSerialData = Mockito.mock(SendSerialData.class);
			PowerMockito.whenNew(SendSerialData.class).withNoArguments().thenReturn(mockSendSerialData);
			doThrow(new UnsupportedCommOperationException()).when(mockSendSerialData).open();

			PowerMockito.mockStatic(ErrorDialog.class);

			ctrl.btAnswerAction(mockActionEvent);

		} catch (UnsupportedCommOperationException e) {
			PowerMockito.verifyStatic(Mockito.times(1));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/**
	 * 異常系テスト
	 * {@link quiz.controller.Controller#btAnswerAction(java.awt.event.ActionEvent)}
	 *
	 * @note SQLException発生時にErrorDialogクラスのstatic show()メソッドが1度呼ばれているか判定
	 */
	@Test
	public void testBtAnswerActionCatchSQLException() {
		try {

			when(mockActionEvent.getSource()).thenReturn(mockJButton);
			when(mockJButton.getParent()).thenReturn(mockMainFrame);

			SendSerialData mockSendSerialData = Mockito.mock(SendSerialData.class);
			PowerMockito.whenNew(SendSerialData.class).withNoArguments().thenReturn(mockSendSerialData);
			doThrow(new SQLException()).when(mockSendSerialData).stream();

			PowerMockito.mockStatic(ErrorDialog.class);

			ctrl.btAnswerAction(mockActionEvent);

		} catch (SQLException e) {
			PowerMockito.verifyStatic(Mockito.times(1));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/**
	 * 異常系テスト
	 * {@link quiz.controller.Controller#btAnswerAction(java.awt.event.ActionEvent)}
	 *
	 * @note NumberFormatException発生時にErrorDialogクラスのstatic
	 *       show()メソッドが1度呼ばれているか判定
	 */
	@Test
	public void testBtAnswerActionCatchNumberFormatException() {
		try {

			when(mockActionEvent.getSource()).thenReturn(mockJButton);
			when(mockJButton.getParent()).thenReturn(mockMainFrame);

			SendSerialData mockSendSerialData = Mockito.mock(SendSerialData.class);
			PowerMockito.whenNew(SendSerialData.class).withNoArguments().thenReturn(mockSendSerialData);
			doThrow(new NumberFormatException()).when(mockSendSerialData).setCommPort((String) anyObject());

			PowerMockito.mockStatic(ErrorDialog.class);

			ctrl.btAnswerAction(mockActionEvent);

			PowerMockito.verifyStatic(Mockito.times(1));

		} catch (NumberFormatException e) {
			PowerMockito.verifyStatic(Mockito.times(1));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/**
	 * 異常系テスト
	 * {@link quiz.controller.Controller#btAnswerAction(java.awt.event.ActionEvent)}
	 *
	 * @note InterruptedException発生時にErrorDialogクラスのstatic
	 *       show()メソッドが1度呼ばれているか判定
	 */
	@Test
	public void testBtAnswerActionCatchInterruptedException() {
		try {

			when(mockActionEvent.getSource()).thenReturn(mockJButton);
			when(mockJButton.getParent()).thenReturn(mockMainFrame);

			SendSerialData mockSendSerialData = Mockito.mock(SendSerialData.class);
			PowerMockito.whenNew(SendSerialData.class).withNoArguments().thenReturn(mockSendSerialData);
			doThrow(new InterruptedException()).when(mockSendSerialData).stream();

			PowerMockito.mockStatic(ErrorDialog.class);

			ctrl.btAnswerAction(mockActionEvent);

		} catch (InterruptedException e) {
			PowerMockito.verifyStatic(Mockito.times(1));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/**
	 * 異常系テスト
	 * {@link quiz.controller.Controller#btAnswerAction(java.awt.event.ActionEvent)}
	 *
	 * @note IOException発生時にErrorDialogクラスのstatic show()メソッドが1度呼ばれているか判定
	 */
	@Test
	public void testBtAnswerActionCatchIOException() {
		try {

			when(mockActionEvent.getSource()).thenReturn(mockJButton);
			when(mockJButton.getParent()).thenReturn(mockMainFrame);

			SendSerialData mockSendSerialData = Mockito.mock(SendSerialData.class);
			PowerMockito.whenNew(SendSerialData.class).withNoArguments().thenReturn(mockSendSerialData);
			doThrow(new IOException()).when(mockSendSerialData).stream();

			PowerMockito.mockStatic(ErrorDialog.class);

			ctrl.btAnswerAction(mockActionEvent);

		} catch (IOException e) {
			PowerMockito.verifyStatic(Mockito.times(1));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/**
	 * 異常系テスト
	 * {@link quiz.controller.Controller#btAnswerAction(java.awt.event.ActionEvent)}
	 *
	 * @note IllegalArgumentException発生時にErrorDialogクラスのstatic
	 *       show()メソッドが1度呼ばれているか判定
	 */
	@Test
	public void testBtAnswerActionCatchIllegalArgumentException() {
		try {

			when(mockActionEvent.getSource()).thenReturn(mockJButton);
			when(mockJButton.getParent()).thenReturn(mockMainFrame);

			EnglishWordBean mockEnglishWordBean = Mockito.mock(EnglishWordBean.class);
			PowerMockito.whenNew(EnglishWordBean.class).withNoArguments().thenReturn(mockEnglishWordBean);

			doThrow(new IllegalArgumentException()).when(mockEnglishWordBean).setWord((String) anyObject());

			PowerMockito.mockStatic(ErrorDialog.class);

			ctrl.btAnswerAction(mockActionEvent);

		} catch (IllegalArgumentException e) {
			PowerMockito.verifyStatic(Mockito.times(1));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/**
	 * 異常系テスト
	 * {@link quiz.controller.Controller#btAnswerAction(java.awt.event.ActionEvent)}
	 *
	 * @note Exception発生時にErrorDialogクラスのstatic show()メソッドが1度呼ばれているか判定
	 */
	@Test
	public void testBtAnswerActionCatchException() {
		try {

			when(mockActionEvent.getSource()).thenReturn(mockJButton);
			when(mockJButton.getParent()).thenReturn(mockMainFrame);

			SendSerialData mockSendSerialData = Mockito.mock(SendSerialData.class);
			PowerMockito.whenNew(SendSerialData.class).withNoArguments().thenReturn(mockSendSerialData);
			doThrow(new Exception()).when(mockSendSerialData).stream();

			PowerMockito.mockStatic(ErrorDialog.class);

			ctrl.btAnswerAction(mockActionEvent);

		} catch (Exception e) {
			PowerMockito.verifyStatic(Mockito.times(1));
		}
	}

	/**
	 * 正常系テスト
	 * {@link quiz.controller.Controller#btAddDialogAction(java.awt.event.ActionEvent)}
	 *
	 * @note 処理が全て通るか判定する
	 */
	@Test
	public void testBtAddDialogAction() {
		try {

			when(mockActionEvent.getSource()).thenReturn(mockJButton);
			when(mockJButton.getParent()).thenReturn(mockMainFrame);

			ctrl.btAddDialogAction(mockActionEvent);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/**
	 * {@link quiz.controller.Controller#btEditDialogAction(java.awt.event.ActionEvent)}
	 * のためのテスト・メソッド。
	 */
	@Ignore
	public void testBtEditDialogAction() {
		fail("まだ実装されていません"); // TODO
	}

	/**
	 * {@link quiz.controller.Controller#btDeleteDialogAction(java.awt.event.ActionEvent)}
	 * のためのテスト・メソッド。
	 */
	@Ignore
	public void testBtDeleteDialogAction() {
		fail("まだ実装されていません"); // TODO
	}

	/**
	 * 正常系テスト
	 * {@link quiz.controller.Controller#btAddAction(java.awt.event.ActionEvent)}
	 *
	 * @note 処理が全て通るか判定する
	 */
	@Test
	public void testBtAddAction() {
		try {
			ctrl.btAddAction(mockActionEvent);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/**
	 * 異常系テスト
	 * {@link quiz.controller.Controller#btAddAction(java.awt.event.ActionEvent)}
	 *
	 * @note SQLException発生時にErrorDialogクラスのstatic show()メソッドが1度呼ばれているか判定
	 */
	@Test
	public void testBtAddActionCatchSQLException() {
		try {

			doThrow(new SQLException()).when(mockDataStore).insert((EnglishWordBean) anyObject());

			PowerMockito.mockStatic(ErrorDialog.class);

			ctrl.btAddAction(mockActionEvent);

		} catch (SQLException e) {
			PowerMockito.verifyStatic(Mockito.times(1));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/**
	 * 異常系テスト
	 * {@link quiz.controller.Controller#btAddAction(java.awt.event.ActionEvent, quiz.view.MainFrame)}
	 *
	 * @note IOException発生時にErrorDialogクラスのstatic show()メソッドが1度呼ばれているか判定
	 */
	@Test
	public void testBtAddActionCatchIOException() {
		try {

			doThrow(new IOException()).when(mockDataStore).insert((EnglishWordBean) anyObject());

			PowerMockito.mockStatic(ErrorDialog.class);

			ctrl.btAddAction(mockActionEvent);

		} catch (IOException e) {
			PowerMockito.verifyStatic(Mockito.times(1));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/**
	 * 異常系テスト
	 * {@link quiz.controller.Controller#btAddAction(java.awt.event.ActionEvent, quiz.view.MainFrame)}
	 *
	 * @note IllegalArgumentException発生時にErrorDialogクラスのstatic
	 *       show()メソッドが1度呼ばれているか判定
	 */
	@Test
	public void testBtAddActionCatchIllegalArgumentException() {
		try {

			doThrow(new IllegalArgumentException()).when(mockDataStore).insert((EnglishWordBean) anyObject());

			PowerMockito.mockStatic(ErrorDialog.class);

			ctrl.btAddAction(mockActionEvent);

		} catch (IllegalArgumentException e) {
			PowerMockito.verifyStatic(Mockito.times(1));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/**
	 * 異常系テスト
	 * {@link quiz.controller.Controller#btAddAction(java.awt.event.ActionEvent, quiz.view.MainFrame)}
	 *
	 * @note Exception発生時にErrorDialogクラスのstatic show()メソッドが1度呼ばれているか判定
	 */
	@Test
	public void testBtAddActionCatchException() {
		try {

			doThrow(new Exception()).when(mockDataStore).insert((EnglishWordBean) anyObject());

			PowerMockito.mockStatic(ErrorDialog.class);

			ctrl.btAddAction(mockActionEvent);

		} catch (Exception e) {
			PowerMockito.verifyStatic(Mockito.times(1));
		}
	}

	/**
	 * {@link quiz.controller.Controller#btEditAction(java.awt.event.ActionEvent)}
	 * のためのテスト・メソッド。
	 */
	@Ignore
	public void testBtEditAction() {
		fail("まだ実装されていません"); // TODO
	}

	/**
	 * 正常系テスト
	 * {@link quiz.controller.Controller#btDeleteAction(java.awt.event.ActionEvent)}
	 *
	 * @note 処理が全て通るか判定する
	 */
	@Test
	public void testBtDeleteAction() {
		try {
			EnglishWordBean mockEnglishWordBean = Mockito.mock(EnglishWordBean.class);
			PowerMockito.whenNew(EnglishWordBean.class).withNoArguments().thenReturn(mockEnglishWordBean);

			ctrl.btDeleteAction(mockEnglishWordBean);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/**
	 * 異常系テスト
	 * {@link quiz.controller.Controller#btDeleteAction(java.awt.event.ActionEvent)}
	 *
	 * @note SQLException発生時にErrorDialogクラスのstatic show()メソッドが1度呼ばれているか判定
	 */
	@Test
	public void testBtDeleteCatchSQLException() {
		try {
			doThrow(new SQLException()).when(mockDataStore).insert((EnglishWordBean) anyObject());

			PowerMockito.mockStatic(ErrorDialog.class);

			EnglishWordBean mockEnglishWordBean = Mockito.mock(EnglishWordBean.class);
			PowerMockito.whenNew(EnglishWordBean.class).withNoArguments().thenReturn(mockEnglishWordBean);

			ctrl.btDeleteAction(mockEnglishWordBean);

		} catch (SQLException e) {
			PowerMockito.verifyStatic(Mockito.times(1));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/**
	 * 異常系テスト
	 * {@link quiz.controller.Controller#btDeleteAction(java.awt.event.ActionEvent, quiz.view.MainFrame)}
	 *
	 * @note IOException発生時にErrorDialogクラスのstatic show()メソッドが1度呼ばれているか判定
	 */
	@Test
	public void testBtDeleteCatchIOException() {
		try {
			doThrow(new IOException()).when(mockDataStore).insert((EnglishWordBean) anyObject());

			PowerMockito.mockStatic(ErrorDialog.class);

			EnglishWordBean mockEnglishWordBean = Mockito.mock(EnglishWordBean.class);
			PowerMockito.whenNew(EnglishWordBean.class).withNoArguments().thenReturn(mockEnglishWordBean);

			ctrl.btDeleteAction(mockEnglishWordBean);

		} catch (IOException e) {
			PowerMockito.verifyStatic(Mockito.times(1));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/**
	 * 異常系テスト
	 * {@link quiz.controller.Controller#btDeleteAction(java.awt.event.ActionEvent, quiz.view.MainFrame)}
	 *
	 * @note IllegalArgumentException発生時にErrorDialogクラスのstatic
	 *       show()メソッドが1度呼ばれているか判定
	 */
	@Test
	public void testBtDeleteCatchIllegalArgumentException() {
		try {
			doThrow(new IllegalArgumentException()).when(mockDataStore).insert((EnglishWordBean) anyObject());

			PowerMockito.mockStatic(ErrorDialog.class);

			EnglishWordBean mockEnglishWordBean = Mockito.mock(EnglishWordBean.class);
			PowerMockito.whenNew(EnglishWordBean.class).withNoArguments().thenReturn(mockEnglishWordBean);

			ctrl.btDeleteAction(mockEnglishWordBean);

		} catch (IllegalArgumentException e) {
			PowerMockito.verifyStatic(Mockito.times(1));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/**
	 * 異常系テスト
	 * {@link quiz.controller.Controller#btDeleteAction(java.awt.event.ActionEvent, quiz.view.MainFrame)}
	 *
	 * @note Exception発生時にErrorDialogクラスのstatic show()メソッドが1度呼ばれているか判定
	 */
	@Test
	public void testBtDeleteActionCatchException() {
		try {
			doThrow(new Exception()).when(mockDataStore).insert((EnglishWordBean) anyObject());

			PowerMockito.mockStatic(ErrorDialog.class);

			EnglishWordBean mockEnglishWordBean = Mockito.mock(EnglishWordBean.class);
			PowerMockito.whenNew(EnglishWordBean.class).withNoArguments().thenReturn(mockEnglishWordBean);

			ctrl.btDeleteAction(mockEnglishWordBean);

		} catch (Exception e) {
			PowerMockito.verifyStatic(Mockito.times(1));
		}
	}
}
