package com.tokopedia.testproject.problems.androidView.slidingImagePuzzle;

import android.content.Context;
import android.graphics.Bitmap;

import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.tokopedia.testproject.R;
import com.tokopedia.testproject.UtilKt;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public interface onSuccessLoadBitmap{
        void onSliceSuccess(List<Bitmap> bitmapList);
//        void onSliceFailed(Throwable throwable);
        void onSliceFailed(String throwable);
    }
    public static onSuccessLoadBitmap onPublicSuccessLoadBitmap;

    public static void sliceTo4x4(Context context, onSuccessLoadBitmap onSuccessLoadBitmap, String imageUrl) {
        ArrayList<Bitmap> bitmapList = new ArrayList<>();
        // TODO, download the image, crop, then sliced to 15 Bitmap (4x4 Bitmap). ignore the last Bitmap
        // below is stub, replace with your implementation!
//        bitmapList.add(UtilKt.toBitmap(context, R.drawable.sample11));
//        bitmapList.add(UtilKt.toBitmap(context, R.drawable.sample12));
//        bitmapList.add(UtilKt.toBitmap(context, R.drawable.sample13));
//        bitmapList.add(UtilKt.toBitmap(context, R.drawable.sample14));
//        bitmapList.add(UtilKt.toBitmap(context, R.drawable.sample21));
//        bitmapList.add(UtilKt.toBitmap(context, R.drawable.sample22));
//        bitmapList.add(UtilKt.toBitmap(context, R.drawable.sample23));
//        bitmapList.add(UtilKt.toBitmap(context, R.drawable.sample24));
//        bitmapList.add(UtilKt.toBitmap(context, R.drawable.sample31));
//        bitmapList.add(UtilKt.toBitmap(context, R.drawable.sample32));
//        bitmapList.add(UtilKt.toBitmap(context, R.drawable.sample33));
//        bitmapList.add(UtilKt.toBitmap(context, R.drawable.sample34));
//        bitmapList.add(UtilKt.toBitmap(context, R.drawable.sample41));
//        bitmapList.add(UtilKt.toBitmap(context, R.drawable.sample42));
//        bitmapList.add(UtilKt.toBitmap(context, R.drawable.sample43));
//        onSuccessLoadBitmap.onSliceSuccess(bitmapList);

        onPublicSuccessLoadBitmap = onSuccessLoadBitmap;
        downloadImage(imageUrl);
    }
    private static void downloadImage(String imageUrl) {
        GetBitmapFromURLAsync getBitmapFromURLAsync = new GetBitmapFromURLAsync();
        getBitmapFromURLAsync.execute(imageUrl);
    }

    private static class GetBitmapFromURLAsync extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... params) {
            return getBitmapFromURL(params[0]);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {

            Bitmap bmp = validateImage(bitmap);

            if (bmp != null) {
                sliceBitmap(bmp);
            }

        }
    }

    private static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            return BitmapFactory.decodeStream(input);
        } catch (IOException e) {
            e.printStackTrace();
            onPublicSuccessLoadBitmap.onSliceFailed("Error while download image");
            return null;
        }
    }

    private static Bitmap validateImage(Bitmap bitmap) {

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();


        Bitmap newBitmap;
        if (width >= 400 && height >= 400) {

            newBitmap = Bitmap.createBitmap(
                    bitmap,
                    (width - height) / 2,
                    0,
                    400,
                    400
            );
            return newBitmap;
        } else {
            onPublicSuccessLoadBitmap.onSliceFailed("Error while download image");

            return null;

        }


    }

    private static void sliceBitmap(Bitmap bitmap) {
        ArrayList<Bitmap> bitmapList = new ArrayList<>();

        bitmapList.add(Bitmap.createBitmap(bitmap, 0, 0, 100, 100));
        bitmapList.add(Bitmap.createBitmap(bitmap, 100, 0, 100, 100));
        bitmapList.add(Bitmap.createBitmap(bitmap, 200, 0, 100, 100));
        bitmapList.add(Bitmap.createBitmap(bitmap, 300, 0, 100, 100));

        bitmapList.add(Bitmap.createBitmap(bitmap, 0, 100, 100, 100));
        bitmapList.add(Bitmap.createBitmap(bitmap, 100, 100, 100, 100));
        bitmapList.add(Bitmap.createBitmap(bitmap,  200, 100, 100, 100));
        bitmapList.add(Bitmap.createBitmap(bitmap,  300, 100, 100, 100));

        bitmapList.add(Bitmap.createBitmap(bitmap,  0, 200, 100, 100));
        bitmapList.add(Bitmap.createBitmap(bitmap, 100, 200, 100, 100));
        bitmapList.add(Bitmap.createBitmap(bitmap, 200, 200, 100, 100));
        bitmapList.add(Bitmap.createBitmap(bitmap, 300, 200, 100, 100));

        bitmapList.add(Bitmap.createBitmap(bitmap, 0, 300, 100, 100));
        bitmapList.add(Bitmap.createBitmap(bitmap, 100, 300, 100, 100));
        bitmapList.add(Bitmap.createBitmap(bitmap, 200, 300, 100, 100));

        onPublicSuccessLoadBitmap.onSliceSuccess(bitmapList);

    }
}
