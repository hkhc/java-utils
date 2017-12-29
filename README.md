# Just my own helper classes

This project consists of two components.

- utils-core: Simple helper classes to simplify daily coding. Pure java in nature, 
should be usable in any Java code.
- utils-android: Android-specific helper classes.

## utils-core

```gradle
    dependencies {
        implementation 'io.hkhc:utils-core:1.0'
    }
```

- Autoboxing elements int array

```java
    Integer[] integerArray = ArrayUtils.box(new int[] {1,2,3});
```

- Empty check of array

```java
    assert(ArrayUtils.isEmpty(null)); 
    assert(ArrayUtils.isEmpty(new int[] {})); 
    assert(!ArrayUtils.isEmpty(new int[] {1})); 
```

- FIll an `int` or `short` to byte array

``` java
    byte[] bytes = new byte[100];
    // fill integer starting from offset 10 of array
    ByteUtils.fillInt(byte, 10, 0x1234); 
    ByteUtils.fillShort(byte, 13, 0x12);
```

- Read file to string

```java
    String str = FileUtils.readFileToString("i-am-a-text-file.txt");
```

- Read file to byte array

```java
    byte[] bytes = FileUtils.readFileToByteArray("i-am-binary-file.dat");
```

- Write `String` to file

```java
    FileUtils.writeStringToFile("i-am-text-file.txt", "UTF-8", "Content to be written");
```

- Write input stream to output stream

```java
    FileUtils.writeStreamToStream(outputStream, inputStream);
```

- Create directory if needed, to ensure the directory exists

```java
    FileUtils.ensureDirectory("myproject/workingDir");
```

- Delete a directory, all of sub-directories and files

```java
    FileUtils.deleteFolder("directory-to-be-removed");
```

- Empty check of `CharSequence`

```java
    assert(StringUtils.isEmpty(null));
    assert(StringUtils.isEmpty(""));
    assert(!StringUtils.isEmpty("Hello"));
```

- Ensure non-null string is used

```java
    assert("".equals(StringsUtils.optional("")));
    assert("".equals(StringsUtils.optional(null)));
    assert("Hello".equals(StringsUtils.optional("Hello")));
```

- Check if the `CharSequence` length is in a specified range (inclusive)

```java
    assert(StringUtils.lengthInRange("Hello", 3, 5));
    assert(!StringUtils.lengthInRange("Hello", 1, 2));
```

- `CharSequence` equality check with null check

```java
    assert(StringUtils.equalCharSequence("Hello", "Hello"));
    assert(!StringUtils.equalCharSequence(null, "Hello"));
    assert(StringUtils.equalCharSequence(null, null));
```

- Ideographic character check, with pre-jdk7 fallback

```java
    IdeographicChecker checker = StringUtils.getIdeographicChaecker();
    assert(checker.isIdeographic('a'));
    assert(checker.isIdeographic('山'));
    assert(!checker.isIdeographic('*'));
```

- Number check without exception

```java

    boolean isNumber = StringUtils.isNumber("123");
    boolean isNumber = StringUtils.isNumber("xxx"); // exception is not thrown
    assert(isNumber==false);
```

- Check if all `CharSequence`s are empty

```java
    assert(StringUtils.allEmpty("",null));
    assert(!StringUtils.allEmpty("", "", "Hello"));
```

- Check if all `CharSequence`s are not empty

```java
    assert(StringUtils.allNotEmpty("Hello", "World"));
    assert(!StringUtils.allNotEmpty("", "", "Hello"));
```

- Check if any of `CharSequence`s are empty

```java
    assert(StringUtils.anyEmpty("Hello", ""));
    assert(!StringUtils.anyEmpty("Hello", "World"));
```

- Check if any of `CharSequence`s are not empty

```java
    assert(StringUtils.anyNotEmpty("Hello", ""));
    assert(!StringUtils.anyNotEmpty("", ""));
```

- Logger

```java
    L l = LogFactory.newLog("TAG");
    l.d("Minimalistic log");
    l.d("MYTAG", "log to voerride default tag");
    l.e("error log with exception dump", new Exception());
    l.i("dump a file to log", new File("file-to-be-dump"));
```

- Customize target of log

```java
    Logger.setLogFactory(new PrintWriterLog.Factory());
    Logger.setLogFactory(new NullLog.Factory());
    Logger.setLogFactory(new CompositeLog.Factory(factory1, factory2, ...));
```

## utils-android

```gradle
    dependencies {
        implementation 'io.hkhc:utils-android:1.0'
    }
```

- Get document path

```java
    String path = AndroidFileUtils.getDocumentPath();
```

- Open Uri as InputStream

```java
    // get file at assets
    InputStream is = AppUtils.openUriAsInputStream("file://android_asset/hello.txt");
```

```java
    // resolve by content provider that register scheme 'custom_scheme'
    InputStream is = AppUtils.openUriAsInputStream("custom_scheme://data");
```

- Get app version

```
    // given R.string.versionTemplate refers to "ver %1$s"
    // and version name is "1.0"
    // return "ver 1.0"
    String ver = AppUtils.getVersion(context, R.string.versionTemplate);
```

- Show play store to rate app or Google Play web page if play store app is not presented

```java
    AppUtils.rateApp(context);
```

- Add a pair of brackets to part of String in `TextView`

```java
    SpannableString str = new SpannableString("Hello World");
    str.setSpan(new BracketSpan(), 6, 11, 0);
    textView.setText(str); // see "Hello (World)" on screen
    assert("Hello World".equals(str.toString()));
```

- Hide part of String in `TextView`

```java
    SpannableString str = new SpannableString("Hello World");
    str.setSpan(new BracketSpan(), 5, 11, 0);
    textView.setText(str); // see "Hello" on screen
    assert("Hello World".equals(str.toString()));
```

- Allow or ban ideographic text in `EditText`

```java
    // false for allow only ideographic characters
    // true for disallow all ideographic characters
    editText.setFilters(new InputFilter[] {new IdeographicInputFilter(false)});
```

- Check if a `Spanned` object contains particular span object.

```java
    Spanned spanned = new SpannableString("Hello World");
    spanned.setSpan(new BracketSpan(), 5, 11, 0);
    assert(SpanUtils.hasSpan(spanned, BracketSpan()));
```

- Check if a `Spanned` object of `EditText` is in composing mode of soft keyboard

```java
   boolean isComposing = SpanUtils.isComposing(editText.getText());
```

- warp Android logcat into "`L`" logger
 
```java
    Logger.setLogFactory(new AndroidLog.Factory());
```