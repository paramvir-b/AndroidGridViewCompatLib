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

Benefits
--------

* Needless to say, you will get multi-selection.
* Only one class file, so make it easy to integrate with projects.
* It uses reflection so you don't have to compile with higher android library. This is helpful, if you don't get it why, think deeper...:)

