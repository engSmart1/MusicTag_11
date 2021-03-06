package musictag.hytham1.com.musictag;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by Hytham on 2/15/2017.
 */

public class LruBitmapCache extends LruCache<String,Bitmap> implements ImageLoader.ImageCache {
    public static int getDefaultLruCacheSize(){
     final int maxMemory = (int) (Runtime.getRuntime().maxMemory() /1024);
     final int cacheSize = maxMemory / 8 ;
  return cacheSize;

    }
    public LruBitmapCache(){
     this(getDefaultLruCacheSize());

    }
    public LruBitmapCache (int sizeInKiloBytes){
     super(sizeInKiloBytes);
    }
    protected int sizeOf(String Key , Bitmap value){
     return value.getRowBytes() * value.getHeight() / 1024;
    }

    @Override
    public Bitmap getBitmap(String url) {
        return null;
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {

    }
}
