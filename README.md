# React Native Umeng Analytics

## Installation

1. `$ npm install react-native-umeng-analytics-bridge --save`
2. `$ react-native link react-native-umeng-analytics-bridge`
3. iOS: Add Umeng denpendencies following the [Doc](http://dev.umeng.com/analytics/ios-doc/integration#1_3_1)


## Usage

###### Import
```
import UmengAnalytics from 'react-native-umeng-analytics-bridge';
```

###### Initialization
```typescript
UmengAnalytics.init(appId: string, channel: string, version: string);
```
