
/**
 * Created by dmytros on 08/05/16.
 */

package org.qtproject.example.AdMob;


import android.util.Log;
import android.util.DisplayMetrics;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;


class AdMobView {
    private static final int BANNER = 0;
    private static final int FULL_BANNER = 1;
    private static final int LARGE_BANNER = 2;
    private static final int LEADERBOARD = 3;
    private static final int MEDIUM_RECTANGLE = 4;
    private static final int WIDE_SKYSCRAPER = 5;
    private static final int SMART_BANNER = 6;

    private AdView view = null;
    private Activity context = null;
    private DisplayMetrics metrics = null;
    private int x = 0;
    private int y = 0;
    private int width = 0;
    private int height = 0;
    private boolean isUpdateRequested = false;
    private boolean isAdUnitAdSet = false;
    private boolean isAdSizeSet = false;
    private FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams( width, height);

    public AdMobView(final Activity context) {
        this.context = context;
        this.metrics = context.getResources().getDisplayMetrics();
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                synchronized(AdMobView.this) {
                    view = new AdView(context);
                    ViewGroup viewgroup = (ViewGroup) context.getWindow().getDecorView().findViewById(android.R.id.content);
                    view.setVisibility(View.VISIBLE);
                    requestLayout();
                    view.setAdListener(new ToastAdListener(context));
                    viewgroup.addView(view);
                }
            }
        });
    }
    
    public synchronized void  removeFromParent() {
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                synchronized(AdMobView.this) {
                    view = new AdView(context);
                    ViewGroup viewgroup = (ViewGroup) context.getWindow().getDecorView().findViewById(android.R.id.content);
                    viewgroup.removeView(view);
                }
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
                    if(AdMobView.this.isAdSizeSet == true && AdMobView.this.isAdUnitAdSet == true) {
                        AdRequest adRequest = new AdRequest.Builder()
                          .build();
                        view.loadAd( adRequest);
                    }
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
        AdMobView.this.x = (int)(x * metrics.density);
        requestLayout();
        
    }
    
    public synchronized void setY(final int y) {
        Log.i("test log", "AdMobView setY called " + y);
        AdMobView.this.y = (int)(y * metrics.density);
        requestLayout();
    }
    public synchronized void setWidth(final int width) {

        Log.i("test log", "AdMobView setWidth called " + width);
        AdMobView.this.width = (int)(width * metrics.density);
        requestLayout();
    }
    public synchronized void setHeight(final int height) {
        Log.i("test log", "AdMobView setHeight called " + height);
        AdMobView.this.height = (int)(height * metrics.density);
        requestLayout();
    }

    public synchronized void setAdUnitId(final String adUnitId) {
        Log.i("test log", "AdMobView setAdUnitId called " + adUnitId);
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                synchronized(AdMobView.this) {

                    view.setAdUnitId(adUnitId);
                    AdMobView.this.isAdUnitAdSet = true;
                    if(AdMobView.this.isAdSizeSet == true) {
                        AdRequest adRequest = new AdRequest.Builder()
                          .build();
                        view.loadAd( adRequest);
                    }
                }
            }
        });

    }
    public synchronized void setAdSize(final int adSize) {

        Log.i("test log", "AdMobView setAdSize called " + adSize);
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                synchronized(AdMobView.this) {
                    switch(adSize) {

                        case BANNER:
                        {
                            view.setAdSize(AdSize.BANNER);
                        }
                        break;
                        case FULL_BANNER:
                        {
                            view.setAdSize(AdSize.FULL_BANNER);
                        }
                        break;
                        case LARGE_BANNER:
                        {
                            view.setAdSize(AdSize.LARGE_BANNER);
                        }
                        break;
                        case LEADERBOARD:
                        {
                            view.setAdSize(AdSize.LEADERBOARD);
                        }
                        break;
                        case MEDIUM_RECTANGLE:
                        {
                            view.setAdSize(AdSize.MEDIUM_RECTANGLE);
                        }
                        break;
                        case WIDE_SKYSCRAPER:
                        {
                            view.setAdSize(AdSize.WIDE_SKYSCRAPER);
                        }
                        break;
                        case SMART_BANNER:
                        {
                            view.setAdSize(AdSize.SMART_BANNER);
                        }
                        break;
                    }
                    AdMobView.this.isAdSizeSet = true;
                    if(AdMobView.this.isAdUnitAdSet == true) {
                        AdRequest adRequest = new AdRequest.Builder()
                          .build();
                        view.loadAd( adRequest);
                    }
                }
            }
        });
    }

}
