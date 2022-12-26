//
//  CoinsListState.swift
//  iosApp
//
//  Created by Yazan Tarifi on 25/12/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

class CoinsListState : CoinaState {
    class LoadingState: CoinsListState {}
    class ErrorState: CoinsListState {
        var message: String = ""
        init(message: String) {
            self.message = message
        }
    }
    
    class ListState: CoinsListState {
        var list: [CoinModel]
        init(list: [CoinModel]) {
            self.list = list
        }
    }
}
