package io.github.changjiashuai.tinkerdemo;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.support.multidex.MultiDex;

import com.tencent.tinker.anno.DefaultLifeCycle;
import com.tencent.tinker.loader.app.DefaultApplicationLike;
import com.tencent.tinker.loader.shareutil.ShareConstants;

import io.github.changjiashuai.tinkerdemo.utils.TinkerManager;

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 2017/1/7 11:21.
 */
@DefaultLifeCycle(application = "io.github.changjiashuai.tinkerdemo.App",
        flags = ShareConstants.TINKER_ENABLE_ALL,
        loadVerifyFlag = false)
public class AppLike extends DefaultApplicationLike {
    private static final String TAG = "AppLike";

    public AppLike(Application application, int tinkerFlags, boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime, long applicationStartMillisTime, Intent tinkerResultIntent, Resources[] resources, ClassLoader[] classLoader, AssetManager[] assetManager) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent, resources, classLoader, assetManager);
    }

    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
        MultiDex.install(base);
        TinkerManager.setTinkerApplicationLike(this);
        TinkerManager.initFastCrashProtect();
        TinkerManager.setUpgradeRetryEnable(true);
        TinkerManager.installTinker(this);
    }
}
