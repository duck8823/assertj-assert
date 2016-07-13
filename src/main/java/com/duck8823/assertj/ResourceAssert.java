package com.duck8823.assertj;

import org.assertj.core.api.AbstractAssert;

import java.io.*;

/**
 * 文字列とリソースの内容を検証する.
 * Created by maeda on 7/13/2016.
 */
public class ResourceAssert extends AbstractAssert<ResourceAssert, String> {

	public ResourceAssert(String actual) {
		super(actual, ResourceAssert.class);
	}

	/**
	 * リソースの内容と一致しているか検証する
	 * @param name リソース
	 * @return ResourceAssert
	 */
	public ResourceAssert isEqualToResource(String name) {
		return isEqualTo(this.getClass().getClassLoader().getResourceAsStream(name));
	}

	/**
	 * ファイルの内容と一致しているか検証する
	 * @param file ファイル
	 * @return ResourceAssert
	 */
	public ResourceAssert isEqualToFile(File file) {
		try {
			return isEqualTo(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			throw new IllegalStateException("ファイルが存在しません.");
		}
	}

	private ResourceAssert isEqualTo(InputStream stream) {
		this.isNotNull();

		StringBuilder sb = new StringBuilder();
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
			for (String line; (line = reader.readLine()) != null; ) {
				sb.append(line).append("\n");
			}
			sb.delete(sb.lastIndexOf("\n"), sb.length());
		} catch (IOException e) {
			throw new IllegalStateException("ファイルを読み込めませんでした.");
		}

		if(!this.actual.equals(sb.toString())){
			this.failWithMessage("内容が一致しません.\n(実際の値=\"%s\")\n（期待値=\"%s\"）", this.actual, sb.toString());
		}

		return this;
	}

}
