//
//  CoinaSingletonUtils.swift
//  iosApp
//
//  Created by Yazan Tarifi on 25/12/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

class CoinaSingletonUtils {
    
    private static var applicationApiServiceInstance: ApplicationApiManager? = nil
    private static var coinsDataSource: CoinsDataSource? = nil
    
    static func getApiManagerInstance() -> ApplicationApiManager {
        if applicationApiServiceInstance == nil {
            applicationApiServiceInstance = ApplicationApiManager().addHttpClient(httpClient: HttpBaseClient().httpClient)
        }
        
        return applicationApiServiceInstance ?? ApplicationApiManager().addHttpClient(httpClient: HttpBaseClient().httpClient)
    }
    
    static func getCoinsDataSourceInstance() -> CoinsDataSource {
        if coinsDataSource == nil {
            coinsDataSource = CoinsDataSource()
        }
        
        return coinsDataSource ?? CoinsDataSource()
    }
    
}
