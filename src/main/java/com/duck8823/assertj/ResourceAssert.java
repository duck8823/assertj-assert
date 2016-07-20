package com.duck8823.assertj;

import org.assertj.core.api.AbstractAssert;

import java.io.*;
import java.util.*;

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
	public ResourceAssert isEqualToResource(String name) throws IOException {
		name = name.replaceFirst("^/", "");
		return isEqualTo(this.getClass().getClassLoader().getResourceAsStream(name));
	}

	/**
	 * ファイルの内容と一致しているか検証する
	 * @param file ファイル
	 * @return ResourceAssert
	 */
	public ResourceAssert isEqualToFile(File file) throws IOException {
		return isEqualTo(new FileInputStream(file));
	}

	protected ResourceAssert isEqualTo(InputStream stream) throws IOException {
		this.isNotNull();

		List<String> actual = Arrays.asList(this.actual.split("\r\n|\r|\n"));
		List<String> expect = new ArrayList<>();
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		for (String line; (line = reader.readLine()) != null; ) {
			expect.add(line);
		}

		Set<String[]> fails = new LinkedHashSet<>();
		for(int i = 0; i < actual.size() || i < expect.size(); i++) {
			if(i >= actual.size()) {
				fails.add(new String[]{Integer.toString(i + 1), "", expect.get(i)});
			} else if(i >= expect.size()) {
				fails.add(new String[]{Integer.toString(i + 1), actual.get(i), ""});
			} else if(!actual.get(i).equals(expect.get(i))){
				fails.add(new String[]{Integer.toString(i + 1), actual.get(i), expect.get(i)});
			}
		}

		if(!fails.isEmpty()){
			StringBuilder sb = new StringBuilder();
			sb.append("内容が一致しません.\n");
			for(String[] fail : fails){
				sb.append(fail[0]).append("行目:\t").append(fail[1]).append(" << ").append(fail[2]).append("\n");
			}
			this.failWithMessage(sb.toString());
		}

		return this;
	}

}
