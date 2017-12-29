
# utils-android

## Setup

```gradle
    dependencies {
        implementation 'io.hkhc:utils-android:1.0'
    }
```

## Examples

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