# assertj-assert
[![Build Status](https://travis-ci.org/duck8823/assertj-assert.svg?branch=master)](https://travis-ci.org/duck8823/assertj-assert)
[![Coverage Status](https://coveralls.io/repos/github/duck8823/assertj-assert/badge.svg?branch=master)](https://coveralls.io/github/duck8823/assertj-assert?branch=master)
[![MIT License](http://img.shields.io/badge/license-MIT-blue.svg?style=flat)](LICENSE)  
  
文字列に対してリソースの中身と比較検証するAssertion

## Usage
```java
@Test
public void リソースの検証に成功する() {
	assertThat("Hello\nWorld.").isEqualToResource("expect");
}
@Test
public void ファイルの検証に成功する() {
	assertThat("Hello\nWorld.").isEqualToFile(new File("path/to/expect"));
}
```
## LICENSE
MIT License