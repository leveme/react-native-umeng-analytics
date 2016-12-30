package cn.leveme.react.modules.umeng.analytics;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

/**
 * Created by leveme on 16/12/6.
 */

public class UmengAnalyticsModule extends ReactContextBaseJavaModule {

    public UmengAnalyticsModule(ReactApplicationContext reactContext, String key, String channel) {
        super(reactContext);
        MobclickAgent.openActivityDurationTrack(false);
        Application app = (Application) getReactApplicationContext().getApplicationContext();
        app.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            }

            @Override
            public void onActivityStarted(Activity activity) {
            }

            @Override
            public void onActivityResumed(Activity activity) {
                MobclickAgent.onResume(activity);
            }

            @Override
            public void onActivityPaused(Activity activity) {
                MobclickAgent.onPause(activity);
            }

            @Override
            public void onActivityStopped(Activity activity) {
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
            }
        });
        init(key, channel, null);
    }

    @Override
    public String getName() {
        return "UmengAnalytics";
    }

    @Override
    public Map<String, Object> getConstants() {
        Map<String, Object> constants = new HashMap<>();
        constants.put("E_UM_NORMAL", MobclickAgent.EScenarioType.E_UM_NORMAL.ordinal());
        constants.put("E_UM_GAME", MobclickAgent.EScenarioType.E_UM_GAME.ordinal());
        constants.put("E_UM_ANALYTICS_OEM", MobclickAgent.EScenarioType.E_UM_ANALYTICS_OEM.ordinal());
        constants.put("E_UM_GAME_OEM", MobclickAgent.EScenarioType.E_UM_GAME_OEM.ordinal());
        return constants;
    }

    @ReactMethod
    public void init(String key, String channel, String version) {
        if (key != null) {
            MobclickAgent.startWithConfigure(new MobclickAgent.UMAnalyticsConfig(getReactApplicationContext(), key, channel));
        }
    }

    @ReactMethod
    public void setScenarioType(int type) {
        MobclickAgent.setScenarioType(getReactApplicationContext(),
                getEnum(MobclickAgent.EScenarioType.values(), type));
    }

    @ReactMethod
    public void setEncryptEnabled(boolean enable) {
        MobclickAgent.enableEncrypt(enable);
    }

    @ReactMethod
    public void onProfileSignIn(String id, String provider) {
        if (provider == null) {
            MobclickAgent.onProfileSignIn(id);
        } else {
            MobclickAgent.onProfileSignIn(provider, id);
        }
    }

    @ReactMethod
    public void onProfileSignOff() {
        MobclickAgent.onProfileSignOff();
    }

    @ReactMethod
    public void onPageStart(String page) {
        MobclickAgent.onPageStart(page);
    }

    @ReactMethod
    public void onPageStop(String page) {
        MobclickAgent.onPageEnd(page);
    }

    @ReactMethod
    public void onEvent(String id, ReadableMap attributes) {
        MobclickAgent.onEvent(getReactApplicationContext(), id, toMap(attributes));
    }

    @ReactMethod
    public void onEventValue(String id, ReadableMap attributes, int du) {
        MobclickAgent.onEventValue(getReactApplicationContext(), id, toMap(attributes), du);
    }

    @ReactMethod
    public void onEventCC(ReadableArray keyPath, int value, String label) {
        List<String> path = new ArrayList<>();
        final int count = keyPath.size();
        for (int i = 0; i < count; i++) {
            switch (keyPath.getType(i)) {
                case Null:
                    path.add(null);
                    break;
                case Boolean:
                    path.add(String.valueOf(keyPath.getBoolean(i)));
                    break;
                case Number:
                    path.add(String.valueOf(keyPath.getInt(i)));
                    break;
                case String:
                    path.add(keyPath.getString(i));
                    break;
            }
        }
        MobclickAgent.onEvent(getReactApplicationContext(), path, value, label);
    }

    private static <T extends Enum<T>> T getEnum(T[] values, int ordinal) {
        for (T value : values) {
            if (ordinal == value.ordinal()) {
                return value;
            }
        }

        return null;
    }

    private static Map<String, String> toMap(ReadableMap src) {
        if (src == null) {
            return null;
        }

        Map<String, String> res = new HashMap<>();
        ReadableMapKeySetIterator iterator = src.keySetIterator();
        while (iterator.hasNextKey()) {
            String key = iterator.nextKey();
            switch (src.getType(key)) {
                case Null:
                    res.put(key, null);
                    break;
                case Boolean:
                    res.put(key, String.valueOf(src.getBoolean(key)));
                    break;
                case Number:
                    res.put(key, String.valueOf(src.getInt(key)));
                    break;
                case String:
                    res.put(key, src.getString(key));
                    break;
            }
        }
        return res;
    }
}
