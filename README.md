AndroidGridViewCompatLib
========================

Multi-select grid view for android API level 7 and above. (NOTE: if you are running against API level 4 try using version 1.0.0 tag)

Motivation
----------

Working on one project I need to use the multi-selection *GridView*. I looked into the android docs and [I found it](http://developer.android.com/reference/android/widget/AbsListView.html#setChoiceMode(int)). I tried few hours with it and realized that the documentation is wrong and all multi-selection related apis came after API level 10 to *GridView* (even though docs says API level 1). Initially these apis are part of *ListView* and later after API level 10 they are moved to *AbsListView*. There is an [issue](http://code.google.com/p/android/issues/detail?id=16571) also opened for the same.

So I sat down and wrote this class to support *GridView* with multi-selection for even older platforms.

Usage for project
-----------------

* One way to use is to have your project use it as a library. (Pls refer [referencing library](http://developer.android.com/guide/developing/projects/projects-eclipse.html#ReferencingLibraryProject))
* Second way is to copy the *GridViewCompat* file to which ever package you want to and change the package of the class file to your package.

Usage inside project
--------------------

Since ADT 21 complains if you are compiling against higher API level and using apis available only in API level above your minSdkVersion, we can make GridViewCompat a drop-in replacement for GridView. So no more 'C' suffixed apis. Just change the import packages and you are good. But to support this your project needs to be compiled against API 11 or higher. Also need ADT 21 and above version.

Example
-------

There is a sample project also which can be helpful.

_layout.xml file_

```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="fill_parent"
    android:layout_height="fill_parent" >

    <Button
        android:id="@+id/doneButton"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignParentBottom="true"
        android:layout_alignParentLeft="true"
        android:layout_alignParentRight="true"
        android:enabled="false"
        android:text="Done" />

    <com.rokoder.android.lib.support.v4.widget.GridViewCompat
        android:id="@+id/gridView"
        android:layout_width="fill_parent"
        android:layout_height="fill_parent"
        android:layout_above="@id/doneButton"
        android:layout_alignParentLeft="true"
        android:layout_alignParentRight="true"
        android:layout_alignParentTop="true"
        android:choiceMode="multipleChoice"
        android:columnWidth="120dp"
        android:gravity="center"
        android:horizontalSpacing="10dp"
        android:numColumns="auto_fit"
        android:stretchMode="columnWidth"
        android:verticalSpacing="10dp" >
    </com.rokoder.android.lib.support.v4.widget.GridViewCompat>

</RelativeLayout>
```

_YourActivity.java_
```java
@Override
protected void onCreate(Bundle saveInstance) {
    ...
    ...
    gridView = (GridViewCompat) findViewById(R.id.gridView);

    // NOTE: We are using setChoiceMode, as I said, its a drop-in replacement
    gridView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
    gridView.setAdapter(imageAdapter);
    ...
    ...
}
```

Benefits
--------

* Needless to say, you will get multi-selection.
* Only one class file, so make it easy to integrate with projects.
* Thanks to ADT 21 its now drop in replacement for GridView. NOTE: you need to compile your project against API 11 or higher. If you want reflection based solution which does not depend upon the API level try tag 'v1.0.0'. Also needs ADT 21 and above.
* If the apis are available it will shift to native ones. So compatibility mode is only when needed.
* It does not require any compatibility jars also.

Caveats
-------

* As of now, the only way you can *setChoiceMode* is from the code. XML attribute is NOT supported.
* Does NOT support *CHOICE_MODE_MULTIPLE_MODAL* for *setChoiceMode*. Which should not be a problem as such.
* If you see lint errors in your code, you might need to use [@TargetAPi](http://developer.android.com/reference/android/annotation/TargetApi.html) to suppress it.
  Example:
  Lint Error - Call requires API level 11 (current min is 4): android.widget.GridView#getCheckedItemPositions
  Try (As we are using in the sample app):
```java
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public View getView(int position, View convertView, ViewGroup parent) {
    ...
    ...
}
```
