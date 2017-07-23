# Zest
A testing framework, wrapping Mockito, AssertJ, Hamcrest and my own project Recall. Designed to follow a BDD requirement patter tests can be written using GIVEN, WHEN and THEN coversations.

A custom JUnit test runner is used to inject the required ZestContext object, hence wrappers are needed for common runners such as the Mockito JUnit runner. It is also suggested that tests extend ZestTest however, if this is not possible, one may add the appropriate ZestContext object to there test class manually.

```java
@WiredZestContext
private ZestContext context;
```

Extending ZestTest will provide access to the wrapper methods
```java
given()
when() / when(AnyRef)
then()
```

which can be used for testing as shown in the following

```java
@RunWith(ZestMockitoRunner.class)
public class ExampleTest2 extends ZestTest {

	@Mock
	private AnyRef anyRef;

	@InjectMocks
	private AnyRef parentRef;

	public void test() {

		...

		given().nothingHappens()		.isUsedWhenCalling(anyRef).method();
		given().theRealMethod()			.isUsedWhenCalling(anyRef).method();
		given().theValue("abc")			.isUsedWhenCalling(anyRef).method();
		given().theThrowable(EXCEPTION)	.isThrownWhenCalling(anyRef).method();
		given().theAnswer(ANSWER<T>)	.isUsedWhenCalling(anyRef).method();

		...

		when(anyRef).calls().method();
		AnyRef proxy = when(anyRef).calls().returningMethod();

		...

		then().assertThat(proxy, is(equalTo(VALUE)));
		then().assertUsing(StringAssert.class).that(proxy).startsWith(VALUE);
	}
}
```

