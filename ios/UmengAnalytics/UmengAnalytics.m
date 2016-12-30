//
//  UmengAnalytics.m
//  UmengAnalytics
//
//  Created by leveme on 16/12/30.
//
//

#import "UmengAnalytics.h"
#import "UMMobClick/MobClick.h"

@implementation UmengAnalytics

+ (void)init:(NSString *)appKey channel:(NSString *)channel version:(NSString *)version {
    [MobClick setAppVersion:version];
    UMConfigInstance.appKey = appKey;
    UMConfigInstance.channelId = channel;
    [MobClick startWithConfigure:UMConfigInstance];
}

- (NSDictionary<NSString *, id> *)constantsToExport
{
    return @{ @"E_UM_NORMAL": @(E_UM_NORMAL),
              @"E_UM_GAME": @(E_UM_GAME)};
}

RCT_EXPORT_MODULE();

RCT_EXPORT_METHOD(init:(NSString *)appKey channel:(NSString *)channel version:(NSString *)version)
{
    [UmengAnalytics init:appKey channel:channel version:version];
}

RCT_EXPORT_METHOD(setScenarioType:(NSInteger)type)
{
    UMConfigInstance.eSType = type;
}

RCT_EXPORT_METHOD(setEncryptEnabled:(BOOL)value)
{
    [MobClick setEncryptEnabled:value];
}

RCT_EXPORT_METHOD(onProfileSignIn:(NSString *)puid provider:(NSString *)provider)
{
    if (provider == nil) {
        [MobClick profileSignInWithPUID:puid];
    } else {
        [MobClick profileSignInWithPUID:puid provider:provider];
    }
}

RCT_EXPORT_METHOD(onProfileSignOff)
{
    [MobClick profileSignOff];
}

RCT_EXPORT_METHOD(onPageStart:(NSString *) page)
{
    [MobClick beginLogPageView:page];
}

RCT_EXPORT_METHOD(onPageStop:(NSString *) page)
{
    [MobClick endLogPageView:page];
}

RCT_EXPORT_METHOD(onEvent:(NSString *)eventId attributes:(NSDictionary *)attributes)
{
    [MobClick event:eventId attributes:attributes];
}

RCT_EXPORT_METHOD(onEventValue:(NSString *)eventId attributes:(NSDictionary *)attributes counter:(int)counter)
{
    [MobClick event:eventId attributes:attributes counter:counter];
}

RCT_EXPORT_METHOD(onEventCC:(NSArray *)keyPath value:(int)value label:(NSString *)label)
{
    [MobClick event:keyPath value:value label:label];
}

@end
