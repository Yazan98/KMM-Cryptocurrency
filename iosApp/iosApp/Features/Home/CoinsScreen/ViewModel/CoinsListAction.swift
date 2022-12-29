//
//  CoinsListAction.swift
//  iosApp
//
//  Created by Yazan Tarifi on 25/12/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

public class CoinsListAction : NSObject, CoinaAction {
    public class GetCoinsList: CoinsListAction {}
    public class GetCoinsListByCategoryName: CoinsListAction {
        public var categoryName: String
        init(categoryName: String) {
            self.categoryName = categoryName
        }
    }
}
