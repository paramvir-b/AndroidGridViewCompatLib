AndroidGridViewCompatLib
========================

Multi-select grid view for android API level 4 and above

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

The only thing you have to take care is that when calling the compatible methods use the one which are suffixed by 'C' and not the default ones. Also you can look in the comments.

Example
-------

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

    // NOTE: We are using setChoiceModeC with suffix 'C'
    gridView.setChoiceModeC(ListView.CHOICE_MODE_MULTIPLE);
    gridView.setAdapter(imageAdapter);
    ...
    ...
}
```

Benefits
--------

* Needless to say, you will get multi-selection.
* Only one class file, so make it easy to integrate with projects.
* It uses reflection so you don't have to compile with higher android library. This is helpful, if you don't get it why, think deeper...:)
* If the apis are available it will shift to native ones. So compatibility mode is only when needed.
* It does not require any compatibility jars also.

Caveats
-------

* As of now, the only way you can *setChoiceMode* is from the code. XML attribute is NOT supported.
* Does NOT support *CHOICE_MODE_MULTIPLE_MODAL* for *setChoiceMode*. Which should not be a problem as such.
