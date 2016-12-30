//
//  UmengAnalytics.h
//  UmengAnalytics
//
//  Created by leveme on 16/12/30.
//
//

#import "RCTBridgeModule.h"

@interface UmengAnalytics : NSObject<RCTBridgeModule>

+ (void)init:(NSString *)appKey channel:(NSString *)channel version:(NSString *)version;

@end
