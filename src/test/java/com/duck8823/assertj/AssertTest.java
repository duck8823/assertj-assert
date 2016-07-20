package com.duck8823.assertj;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static com.duck8823.assertj.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * {@link ResourceAssert}のテスト
 * Created by maeda on 7/13/2016.
 */
public class AssertTest {

	@Test
	public void コンストラクタ() {
		try {
			new Assertions();
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void リソースの検証に成功する() throws IOException {
		assertThat("Hello\nNew\rWorld.").isEqualToResource("expect");
	}

	@Test
	public void ファイルの検証に成功する() throws IOException {
		assertThat("Hello\r\nNew\nWorld.").isEqualToFile(new File("./src/test/resources/expect"));
	}

	@Test
	public void 文字列が異なるのでリソースの検証に失敗する() {
		try {
			assertThat("Hello\nnew\nWorld.").isEqualToResource("/expect");
			throw new Exception();
		} catch (AssertionError ignore) {
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void actualの方が行数が多いのでリソースの検証に失敗する() {
		try {
			assertThat("Hello\nWorld.").isEqualToResource("/expect");
			throw new Exception();
		} catch (AssertionError ignore) {
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void expectの方が行数が多いのでリソースの検証に失敗する() {
		try {
			assertThat("Hello\nNew\nNew\nWorld.").isEqualToResource("/expect");
			throw new Exception();
		} catch (AssertionError ignore) {
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void ファイルの検証に失敗する() {
		try {
			assertThat("Hello\nWorld.").isEqualToFile(new File("./src/test/resources/expect"));
			throw new Exception();
		} catch (AssertionError ignore) {
		} catch (Exception e) {
			fail();
		}
	}
}
