package cn.leveme.react.modules.umeng.analytics;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by leveme on 16/12/6.
 */

public class UmengAnalyticsPackage implements ReactPackage {

    private final String key;
    private final String channel;

    public UmengAnalyticsPackage() {
        this(null, null);
    }

    public UmengAnalyticsPackage(String key, String channel) {
        this.key = key;
        this.channel = channel;
    }

    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        List<NativeModule> modules = new ArrayList<>();
        modules.add(new UmengAnalyticsModule(reactContext, key, channel));
        return modules;
    }

    @Override
    public List<Class<? extends JavaScriptModule>> createJSModules() {
        return Collections.emptyList();
    }

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        return Collections.emptyList();
    }
}
