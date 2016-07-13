# assertj-assert
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
