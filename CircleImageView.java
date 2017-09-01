package com.enmingx;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * @author Enming XIE
 *
 * <p>Copyright 2017</p>
 */
public class CircleImageView extends ImageView {

  private Paint paint;
  // The radius of the circle image
  private int radius;
  // Scale rate of the image
  private float scale;

  public CircleImageView(Context context) {
    super(context);
  }

  public CircleImageView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    // keep width and height of the circle image the same
    int size = Math.min(getMeasuredWidth(), getMeasuredHeight());
    radius = size / 2;
    setMeasuredDimension(size, size);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    Drawable drawable = getDrawable();

    if (drawable == null) {
      return;
    }
    if (getWidth() == 0 || getHeight() == 0) {
      return;
    }

    paint = new Paint();

    Bitmap bitmap = drawableToBitmap(drawable);

    BitmapShader bitmapShader = new BitmapShader(bitmap, TileMode.CLAMP, TileMode.CLAMP);

    // Calculate scale rate
    scale = (radius * 2.0f) / Math.min(bitmap.getWidth(), bitmap.getHeight());

    Matrix matrix = new Matrix();
    matrix.setScale(scale, scale);

    bitmapShader.setLocalMatrix(matrix);

    paint.setShader(bitmapShader);

    canvas.drawCircle(radius, radius, radius, paint);

  }

  private Bitmap drawableToBitmap(Drawable drawable) {
    if (drawable instanceof BitmapDrawable) {
      BitmapDrawable bitmapDrawable = (BitmapDrawable)drawable;
      return bitmapDrawable.getBitmap();
    }

    int width = drawable.getIntrinsicWidth();
    int height = drawable.getIntrinsicHeight();

    Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(bitmap);
    drawable.setBounds(0, 0, width, height);
    drawable.draw(canvas);

    return bitmap;
  }
}
