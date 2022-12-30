//
//  CoinInfoState.swift
//  iosApp
//
//  Created by Yazan Tarifi on 29/12/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

public class CoinInfoState: NSObject, CoinaState {
    public class LoadingState: CoinInfoState {}
    public class ListState: CoinInfoState {
        var items: [CoinInfoItem]
        init(items: [CoinInfoItem]) {
            self.items = items
        }
    }
    
    public class ErrorState: CoinInfoState {
        var message: String
        init(message: String) {
            self.message = message
        }
    }
    
}
