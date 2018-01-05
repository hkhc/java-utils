# utils-core

## Setup

```gradle
    dependencies {
        implementation 'io.hkhc:utils-core:1.0'
    }
```

## Examples

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
