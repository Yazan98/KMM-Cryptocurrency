//
//  CoinaApplicationUtils.swift
//  iosApp
//
//  Created by Yazan Tarifi on 24/12/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

class CoinaApplicationUtils {
    
    static func isUserLoggedIn() -> Bool {
        let storageProvider = CoinaStorageProvider(provider: CoinaStorageKeyValue(context: UserDefaults()))
        return storageProvider.isUserLoggedIn()
    }
    
    static func updateUserLoggedInStatus(newStatus: Bool) {
        let storageProvider = CoinaStorageProvider(provider: CoinaStorageKeyValue(context: UserDefaults()))
        storageProvider.updateLoggedInUser(newState: newStatus)
    }
    
}
