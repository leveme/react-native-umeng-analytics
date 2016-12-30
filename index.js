'use strict';

import { NativeModules } from 'react-native';

const Umeng = NativeModules.UmengAnalytics;

export default class UmengAnalytics {
  static E_UM_NORMAL = Umeng.E_UM_NORMAL;
  static E_UM_GAME = Umeng.E_UM_GAME;

  static init(appKey, channel, version, type = UmengAnalytics.E_UM_NORMAL) {
    Umeng.setScenarioType(type);
    Umeng.init(appKey, channel, version);
  }

  static setEncryptEnabled(value) {
    Umeng.setEncryptEnabled(value);
  }

  static onProfileSignIn(id, provider) {
    Umeng.onProfileSignIn(id, provider);
  }

  static onProfileSignOff() {
    Umeng.onProfileSignOff();
  }
 
  static onPageStart(page) {
    Umeng.onPageStart(page);
  }

  static onPageStop(page) {
    Umeng.onPageStop(page);
  }

  static onEvent(eventId, attributes = {}, value = undefined) {
    if (value) {
      Umeng.onEventValue(eventId, attributes, value);
    } else {
      Umeng.onEvent(eventId, attributes);
    }
  }

  static onCcEvent(keyPath, value, label) {
    Umeng.onEventCC(keyPath, value, label);
  }
}
