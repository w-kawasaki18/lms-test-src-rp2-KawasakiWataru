package jp.co.sss.lms.ct.f02_faq;

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
 * 結合テスト よくある質問機能
 * ケース05
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース05 キーワード検索 正常系")
public class Case05 {

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
	@DisplayName("テスト02 初回ログイン済みの受講生ユーザーでログイン")
	void test02() {
		// ログインIDを入力する
		WebElement loginId = webDriver.findElement(By.id("loginId"));
		loginId.clear();
		loginId.sendKeys("StudentAA01");

		// パスワードを入力する
		WebElement password = webDriver.findElement(By.id("password"));
		password.clear();
		password.sendKeys("Pass1234");

		// ログインボタンをクリックする
		WebElement loginButton = webDriver.findElement(By.className("btn-primary"));
		loginButton.click();

		//コース詳細画面が表示されるまで待機
		visibilityTimeout(By.className("active"), 10);

		// コース詳細画面に遷移したこと・タブ表示が仕様書通りであることを確認
		assertEquals("コース詳細 | LMS", webDriver.getTitle());

		// エビデンスを取得する
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(3)
	@DisplayName("テスト03 上部メニューの「ヘルプ」リンクからヘルプ画面に遷移")
	void test03() {
		//「機能」メニューをクリック
		WebElement MenuDropDown = webDriver.findElement(By.className("dropdown-toggle"));
		MenuDropDown.click();

		//「ヘルプ」リンクをクリック
		WebElement help = webDriver.findElement(By.linkText("ヘルプ"));
		help.click();

		//ヘルプ画面が表示されるまで待機
		visibilityTimeout(By.className("panel-title"), 10);

		// コース詳細画面に遷移したこと・タブ表示が仕様書通りであることを確認
		assertEquals("ヘルプ | LMS", webDriver.getTitle());

		// エビデンスを取得する
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「よくある質問」リンクからよくある質問画面を別タブに開く")
	void test04() {
		//「よくある質問」リンクをクリック
		WebElement faq = webDriver.findElement(By.linkText("よくある質問"));
		faq.click();

		// 新しいタブに切り替え
		String newWindow = webDriver.getWindowHandle();

		for (String handle : webDriver.getWindowHandles()) {
			if (!handle.equals(newWindow)) {
				webDriver.switchTo().window(handle);
				break;
			}
		}

		//よくある質問画面が表示されるまで待機
		visibilityTimeout(By.className("form-horizontal"), 10);

		//よくある質問画面に遷移したこと・タブ表示が仕様書通りであることを確認
		assertEquals("よくある質問 | LMS", webDriver.getTitle());

		// エビデンスを取得する
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(5)
	@DisplayName("テスト05 キーワード検索で該当キーワードを含む検索結果だけ表示")
	void test05() {
		// キーワードを入力する
		WebElement keyword = webDriver.findElement(By.id("form"));
		keyword.sendKeys("セルフ");

		// 検索ボタンをクリックする
		WebElement searchButton = webDriver.findElement(By.className("btn-primary"));
		searchButton.click();

		// 検索結果が表示されるまで待機
		visibilityTimeout(By.className("table-hover"), 10);

		// 該当する質問が表示されていることを確認
		assertTrue(webDriver.getPageSource().contains("セルフ・キャリアドック制度とは何か"));

		// エビデンス取得
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(6)
	@DisplayName("テスト06 「クリア」ボタン押下で入力したキーワードを消去")
	void test06() {
		// クリアボタンをクリック
		WebElement clearBtn = webDriver.findElement(
				By.cssSelector("input[type=\"button\"]"));
		clearBtn.click();

		// キーワード入力欄を取得
		WebElement keywordForm = webDriver.findElement(By.id("form"));

		// 画面タイトルと入力欄が空であることを確認
		assertEquals("よくある質問 | LMS", webDriver.getTitle());
		assertEquals("", keywordForm.getAttribute("value"));

		// 画面をスクロール
		scrollTo("0");

		// エビデンス取得
		getEvidence(new Object() {
		});
	}

}
