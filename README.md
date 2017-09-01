# AndroidCircleImageView

An Android Circle ImageView with scale and its sample app.

一个简单的带缩放的 Android 圆形图片，以及使用例子 app。


#### Usage(用法)：

```
<com.enmingx.CircleImageView
    android:id="@+id/contact_circle_image"
    android:layout_width="200dp"
    android:layout_height="200dp"
    android:adjustViewBounds="true"
    android:layout_centerInParent="true"
    android:scaleType="centerCrop"
    android:src="@drawable/avatar"/>
```

You can set the width and height of image to be different, but the circle will always be scaled to have the same width and height.

你可以设置图片宽高不相等，但是生成的圆形图片宽高还是会一样。
