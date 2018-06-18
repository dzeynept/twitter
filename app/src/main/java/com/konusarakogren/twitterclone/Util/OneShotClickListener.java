package com.konusarakogren.twitterclone.Util;

import android.content.Context;
import android.os.SystemClock;
import android.view.View;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * Dinleme egzersizlerinde bilinmeyen kelimelere art arda tıklamaları engellemek için bekleme süresi
 * eklemek için kullanılan ClickListener'dır.
 */
public abstract class OneShotClickListener implements View.OnClickListener {

    private final long minimumInterval;
    private Map<View, Long> lastClickMap;

    /**
     * Click islemlerimizi artik buraya yazacagiz implements
     *
     * @param v
     *          The view that was clicked
     */
    public abstract void onOneShotClick(View v);

    /**
     * Bu constructor sayesinde iki click arası min bekleme zamanı bırakıyoruz.
     *
     * @param minimumIntervalMsec
     *          izin vereceğimiz min click suresidir
     */
    protected OneShotClickListener(long minimumIntervalMsec) {
        this.minimumInterval = minimumIntervalMsec;
        this.lastClickMap = new WeakHashMap<>();
    }

    public OneShotClickListener(long minimumIntervalMsec, Context pContext) {
        this.minimumInterval = minimumIntervalMsec;
        this.lastClickMap = new WeakHashMap<>();
        Context context = pContext;
    }

    @Override
    public void onClick(View clickedView) {
        Long previousClickTimestamp = lastClickMap.get(clickedView);
        long currentTimestamp = SystemClock.uptimeMillis();

        lastClickMap.put(clickedView, currentTimestamp);
        if (previousClickTimestamp == null || (currentTimestamp - previousClickTimestamp > minimumInterval)) {

            // clickedView.setAnimation(ViewEffector.getFadeEffect());
            onOneShotClick(clickedView);
        }
    }
}
