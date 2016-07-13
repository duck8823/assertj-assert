package com.duck8823.assertj;

/**
 * カスタムアサーション
 * Created by maeda on 7/13/2016.
 */
public class Assertions {

	public static ResourceAssert assertThat(String actual) {
		return new ResourceAssert(actual);
	}
}
