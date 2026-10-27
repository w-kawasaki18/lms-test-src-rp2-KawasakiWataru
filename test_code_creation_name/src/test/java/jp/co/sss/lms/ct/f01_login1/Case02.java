package jp.co.sss.lms.ct.f01_login1;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.junit.Assert.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * 結合テスト ログイン機能①
 * ケース02
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース02 受講生 ログイン 認証失敗")
public class Case02 {

	/** 前処理 */
	@BeforeAll
	static void before() {
		createDriver();
	}

	/** 後処理 */
	@AfterAll
	static void after() {
		closeDriver();
	}

	@Test
	@Order(1)
	@DisplayName("テスト01 トップページURLでアクセス")
	void test01() {
		//トップページにアクセス
		goTo("http://localhost:8080/lms/");

		//画面表示
		assertEquals("ログイン | LMS", webDriver.getTitle());

		//エビデンス取得
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(2)
	@DisplayName("テスト02 DBに登録されていないユーザーでログイン")
	void test02() {
		// ログインIDを入力する
		WebElement loginId = webDriver.findElement(By.id("loginId"));
		loginId.clear();
		loginId.sendKeys("testID999");

		// パスワードを入力する
		WebElement password = webDriver.findElement(By.id("password"));
		password.clear();
		password.sendKeys("pass999");

		// ログインボタンをクリックする
		WebElement loginButton = webDriver.findElement(By.className("btn-primary"));
		loginButton.click();

		//エラーメッセージが表示されるまで待つ
		visibilityTimeout(By.className("error"), 10);
		WebElement errorMsg = webDriver.findElement(By.className("error"));
		
		//ログイン画面に戻ったことを確認
		assertEquals("ログイン | LMS", webDriver.getTitle());
		
		//エラーメッセージの表示を確認
		assertTrue(errorMsg.isDisplayed());
		
		// エビデンスを取得する
		getEvidence(new Object() {
		});
	}
}
