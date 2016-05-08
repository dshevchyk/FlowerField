 package org.qtproject.example;
 import com.google.android.gms.ads.AdRequest;
 import com.google.android.gms.ads.AdSize;
 import com.google.android.gms.ads.AdView;
 import com.google.android.gms.ads.AdListener;
 import android.os.Bundle;
 import android.view.View;
 import android.view.ViewGroup;
 import android.util.Log;
 import org.qtproject.example.AdMob.*;

 public class MainActivity extends org.qtproject.qt5.android.bindings.QtActivity
 {
   private static ViewGroup viewGroup;
   private AdView mAdView;
   private boolean adAdded = false;
   @Override
   public void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     mAdView = new AdView(this);
     mAdView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
     mAdView.setAdSize(AdSize.BANNER);
     View view = getWindow().getDecorView().getRootView();
     if (view instanceof ViewGroup) {
         Log.i("test log", "view instanceof ViewGroup SUCCESS");
       viewGroup = (ViewGroup) view;
       ViewGroup.LayoutParams ad_layout_params = new ViewGroup.LayoutParams( ViewGroup.LayoutParams.FILL_PARENT, 150);
       mAdView.setLayoutParams(ad_layout_params);
       viewGroup.addView( mAdView);

       mAdView.setAdListener(new ToastAdListener(this));
       AdRequest adRequest = new AdRequest.Builder()
         .build();
       mAdView.loadAd( adRequest);
     }
     else {

         Log.i("test log", "view instanceof ViewGroup FAIl");
     }

   }
   @Override
   public void onPause() {
     mAdView.pause();
     super.onPause();
   }
   @Override
   public void onResume() {
     super.onResume();
     mAdView.resume();
   }
   @Override
   public void onDestroy() {
     mAdView.destroy();
     super.onDestroy();
   }
 }
