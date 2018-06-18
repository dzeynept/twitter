package com.konusarakogren.twitterclone.activity.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import butterknife.ButterKnife;

/**
 * Base Activity tüm activity sınıflarından extends edilebilecek şekilde oluşturulmuş soyut sınıftır.
 * Gerekli olan metodları kod tekrarı olmaması için buradan çağırarak kullanılmıştır.
 */
public abstract class BaseActivity extends AppCompatActivity {
    public static final String TAG = "BaseActivity";

    /**
     * Her Activity'nin ait olan layoutların id si Activity'lerde override edilmiştir. Activity'lerde
     * Layout kimliğini döndürür.
     *
     * @return Activity Layout kimliği
     */
    protected abstract int getLayoutResourceId();

    /**
     * Açık Activity'nin Context'ini metodu çağırana gönderir.
     *
     * @return açık Activity Context'i
     */
    protected abstract Context getContext();

    /**
     * Activity'nin görünümlerini başlatır.
     */
    protected abstract void initViews();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());
        ButterKnife.bind(this);
        initViews();
        Log.d(TAG, "onCreate:" + getContext());
    }

//    /**
//     * Dönen yükleme diyaloğu gösterir.
//     */
//    public void showDialog() {
//        dialog = new Dialog(this);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(R.layout.dialog_layout);
//        dialog.getWindow()
//                .setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//        RotateLoading rotateLoading = dialog.findViewById(R.id.rotateloading_from_dialog_layout);
//        rotateLoading.start();
//        dialog.setCancelable(false);
//        dialog.show();
//    }
}