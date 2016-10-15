package forezp.com.douyalibrary.utils;



import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Bitmap 操作类 ,
 * Created by b508a on 2015/12/28.
 */
public class BitmapUtils {

//    public static Bitmap casualStroke(Context context, int drawableId, int color) {
//        Bitmap mode = ((BitmapDrawable) context.getResources().getDrawable(
//                drawableId)).getBitmap();
//        Bitmap bitmap = mode.copy(Bitmap.Config.ARGB_8888, true);
//        Canvas canvas = new Canvas();
//        canvas.setBitmap(bitmap);
//        Paint paintUnder = new Paint();
//        paintUnder.setColor(color);
//        canvas.drawPaint(paintUnder);
//        Paint paint = new Paint();
//        paint.setFilterBitmap(true);
//        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
//        canvas.drawBitmap(mode, 0, 0, paint);
//        return bitmap;
//    }

    /**
     * 根据本地获取url 获取bitmap ，根据options去decode,
     * 像素为128*128
     * @param
     * @return
     */
    public static Bitmap loadBitmap(String imageFile){

        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imageFile, opts);

        opts.inSampleSize = computeSampleSize(opts, -1, 128*128);
        opts.inJustDecodeBounds = false;
        Bitmap bmp = null;
        try {
            bmp = BitmapFactory.decodeFile(imageFile, opts);

        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
        return bmp;
    }



    /**
     * 根据像素和本地url加载bitmap
     * @param maxNumOfPixels
     * @param imgpath
     * @return
     */
    public static Bitmap loadBitmap(int maxNumOfPixels, String imgpath) {
        Bitmap bitmap = null;
        try {
            FileInputStream f = new FileInputStream(new File(imgpath));

            // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(imgpath, options);
            // 调用上面定义的方法计算inSampleSize值
            if (0 == maxNumOfPixels) {
                maxNumOfPixels = 128 * 128;
            }
            options.inSampleSize = computeSampleSize(options, -1,
                    maxNumOfPixels);
            // 使用获取到的inSampleSize值再次解析图片
            options.inJustDecodeBounds = false;
            bitmap = BitmapFactory.decodeStream(f, null, options);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
        return bitmap;
    }


    /**'
     * drawable 转bitmap
     * @param drawable
     * @return
     */
    public static Bitmap drawableToBitmap(Drawable drawable) {

        Bitmap bitmap = Bitmap.createBitmap(

                drawable.getIntrinsicWidth(),

                drawable.getIntrinsicHeight(),

                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888

                        : Bitmap.Config.RGB_565);

        Canvas canvas = new Canvas(bitmap);

        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight());

        drawable.draw(canvas);

        return bitmap;

    }

    /**
     * 缩放bitmpap
     * @param bm
     * @param w
     * @param h
     * @return
     */
    public static Bitmap zoomBitmap(Bitmap bm, int w, int h) {

        Bitmap newBitmap;
        if (bm == null) {
            return null;
        }
        float scaleWidth = bm.getWidth() * 1.0f / w;
        float scaleHeight = bm.getHeight() * 1.0f / h;
        float scale = scaleWidth > scaleHeight ? scaleWidth : scaleHeight;
        if (scale > 1.01) {
            newBitmap = Bitmap.createScaledBitmap(bm,
                    (int) (bm.getWidth() / scale),
                    (int) (bm.getHeight() / scale), false);
        } else {
            newBitmap = bm;
        }
        return newBitmap;
    }

    /**
     * 从内存卡的图片转换成bitmap
     * @param filePath
     * @return
     */
    public static Bitmap loadFromSdCard(String filePath) {
        File file = new File(filePath);
        Bitmap bmp = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            bmp = BitmapFactory.decodeStream(fis);
            if (bmp != null) {
                return bmp;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     *
     * @param bm 要保存的bitmap
     * @param picName  保存文件的名字；默认存储在mediapath路径下。
     */
    public static void saveBitmap(Bitmap bm, String picName) {
        Log.e("", "保存图片");
        try {
            File f = new File(FileHelper.getInstance().getMediaPath(), picName + ".JPEG");
            if (f.exists()) {
                f.delete();
            }
            FileOutputStream out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
            Log.e("", "已经保存");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取圆角bitmap
     * @param bitmap
     * @return
     */
    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = 12;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        if (null != bitmap) {
            bitmap.recycle();
            bitmap = null;
        }

        return output;
    }

    /**
     * 转换图片成圆形
     *
     * @param bitmap
     *            传入Bitmap对象
     * @return
     */
    public static Bitmap toRoundBitmap(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float roundPx;
        float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;
        if (width <= height) {
            roundPx = width / 2;
            top = 0;
            bottom = width;
            left = 0;
            right = width;
            height = width;
            dst_left = 0;
            dst_top = 0;
            dst_right = width;
            dst_bottom = width;
        } else {
            roundPx = height / 2;
            float clip = (width - height) / 2;
            left = clip;
            right = width - clip;
            top = 0;
            bottom = height;
            width = height;
            dst_left = 0;
            dst_top = 0;
            dst_right = height;
            dst_bottom = height;
        }
        Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect src = new Rect((int) left, (int) top, (int) right,
                (int) bottom);
        final Rect dst = new Rect((int) dst_left, (int) dst_top,
                (int) dst_right, (int) dst_bottom);
        final RectF rectF = new RectF(dst);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, src, dst, paint);

        if(null != bitmap) {
            bitmap.recycle();
            bitmap = null;
        }
        return output;
    }

    /**
     * 字节流转换bitmap
     * @param b
     * @return
     */
    public static Bitmap Bytes2Bimap(byte[] b) {
        if (b.length != 0) {
            return BitmapFactory.decodeByteArray(b, 0, b.length);
        } else {
            return null;
        }
    }
    /**
     * 压缩bitmap,返回字节流
     * @param maxNumOfPixels
     * @param imgpath
     * @return
     */
    public static byte[] compressBitmap(int maxNumOfPixels, String imgpath) {
        double maxSize = 100.00;
        Bitmap bitmap = loadBitmap(maxNumOfPixels, imgpath);
        if (null != bitmap) {
            byte[] bBitmap = convertBitmap(bitmap);
            if (bitmap != null) {
                bitmap.recycle();
                bitmap = null;
            }
            double mid = bBitmap.length / 1024;
            if (mid > maxSize) {
                double i = mid / maxSize;
                bBitmap = compressBitmap((int) (maxNumOfPixels / Math.abs(i)),
                        imgpath);
            }
            return bBitmap;
        } else {
            return null;
        }
    }

    public static byte[] convertBitmap(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        int options = 100;
//		LogUtil.e("===baos.toByteArray().length===" + baos.toByteArray().length);
//		LogUtil.e("===baos.size===" + baos.size());
        while (baos.size() / 1024 > 100) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();// 重置baos即清空baos
            options -= 10;// 每次都减少10
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中

        }
        try {
            baos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (bitmap != null) {
                bitmap.recycle();
                bitmap = null;
            }
        }
        return baos.toByteArray();

    }



    public static int computeSampleSize(BitmapFactory.Options options,

                                        int minSideLength, int maxNumOfPixels) {

        int initialSize = computeInitialSampleSize(options, minSideLength,

                maxNumOfPixels);

        int roundedSize;

        if (initialSize <= 8) {

            roundedSize = 1;

            while (roundedSize < initialSize) {

                roundedSize <<= 1;

            }

        } else {

            roundedSize = (initialSize + 7) / 8 * 8;

        }

        return roundedSize;
    }

    private static int computeInitialSampleSize(BitmapFactory.Options options,



                                                int minSideLength, int maxNumOfPixels) {

        double w = options.outWidth;

        double h = options.outHeight;

        int lowerBound = (maxNumOfPixels == -1) ? 1 :

                (int) Math.ceil(Math.sqrt(w * h / maxNumOfPixels));

        int upperBound = (minSideLength == -1) ? 128 :

                (int) Math.min(Math.floor(w / minSideLength),

                        Math.floor(h / minSideLength));

        if (upperBound < lowerBound) {

            // return the larger one when there is no overlapping zone.

            return lowerBound;

        }

        if ((maxNumOfPixels == -1) &&

                (minSideLength == -1)) {

            return 1;

        } else if (minSideLength == -1) {

            return lowerBound;

        } else {

            return upperBound;

        }
    }



}
