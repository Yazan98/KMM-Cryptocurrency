//
//  ExchangesState.swift
//  iosApp
//
//  Created by Yazan Tarifi on 26/12/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

public class ExchangesState : NSObject, CoinaState {
    public class ErrorState: ExchangesState {
        var message: String
        init(message: String) {
            self.message = message
        }
    }
    
    public class ListState: ExchangesState {
        var exchanges: [ExchangeModel]
        init(exchanges: [ExchangeModel]) {
            self.exchanges = exchanges
        }
    }
}
