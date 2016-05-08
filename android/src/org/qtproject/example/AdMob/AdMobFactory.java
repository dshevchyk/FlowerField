
package org.qtproject.example.AdMob;

import android.app.Activity;
import java.util.ArrayList;

public class AdMobFactory{
    private static Activity context = null;
    static ArrayList<AdMobView> views = new ArrayList<AdMobView>();
    
    public static synchronized void setContext(Activity context) {
        AdMobFactory.context = context;
        for(AdMobView view: views) {
            view.removeFromParent();
        }
        views.clear();
    }
    
    public static synchronized AdMobView createView() {
        AdMobView view = new AdMobView(context);
        views.add(new AdMobView(context));
        return view;
    }
    public static synchronized void destroyView(AdMobView view){
        view.removeFromParent();
        views.remove(view);
    }
}
