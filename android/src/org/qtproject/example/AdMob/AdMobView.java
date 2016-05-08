
/**
 * Created by dmytros on 08/05/16.
 */

package org.qtproject.example.AdMob;


import android.util.Log;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;


class AdMobView {
    private AdView view = null;
    private Activity context = null;
    private int x = 0;
    private int y = 0;
    private int width = 0;
    private int height = 0;
    private boolean isUpdateRequested = false;
    private FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams( width, height);

    public AdMobView(final Activity context) {
        this.context = context;
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                view = new AdView(context);
                ViewGroup viewgroup = (ViewGroup) context.getWindow().getDecorView().findViewById(android.R.id.content);
                view.setVisibility(View.VISIBLE);
                requestLayout();
                viewgroup.addView(view);

                //temp
                view.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
                view.setAdListener(new ToastAdListener(context));
                view.setAdSize(AdSize.BANNER);
                AdRequest adRequest = new AdRequest.Builder()
                  .build();
                view.loadAd( adRequest);
            }
        });
    }
    
    public synchronized void  removeFromParent() {
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                view = new AdView(context);
                ViewGroup viewgroup = (ViewGroup) context.getWindow().getDecorView().findViewById(android.R.id.content);
                viewgroup.removeView(view);
            }
        });
    }
    public synchronized void requestLayout() {
        if(isUpdateRequested == true)
            return;
        isUpdateRequested = true;
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                synchronized(AdMobView.this) {
                    Log.i("test log", "requestLayout(" + AdMobView.this.width + ", " + AdMobView.this.height + ", " +
                                                         AdMobView.this.x + ", " + AdMobView.this.y + ")");
                    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams( AdMobView.this.width, AdMobView.this.height);
                    layoutParams.topMargin = AdMobView.this.y;
                    layoutParams.leftMargin = AdMobView.this.x;
                    view.setLayoutParams(layoutParams);
                    isUpdateRequested = false;
                }
            }
        });
    }
    
    public synchronized void setVisibility(final boolean visible) {

        Log.i("test log", "AdMobView setVisibility called isVisible=" +visible);
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                view.setVisibility(visible? View.VISIBLE: View.INVISIBLE);
            }
        });
    }
    public synchronized void setX(int x) {

        Log.i("test log", "AdMobView setX called " + x);
        AdMobView.this.x = x;
        requestLayout();
        
    }
    
    public synchronized void setY(final int y) {
        Log.i("test log", "AdMobView setY called " + y);
        AdMobView.this.y = y;
        requestLayout();
    }
    public synchronized void setWidth(final int width) {

        Log.i("test log", "AdMobView setWidth called " + width);
        AdMobView.this.width = width;
        requestLayout();
    }
    public synchronized void setHeight(final int height) {
        Log.i("test log", "AdMobView setHeight called " + height);
        AdMobView.this.height = height;
        requestLayout();
    }
}
