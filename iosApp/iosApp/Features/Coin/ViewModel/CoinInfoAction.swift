//
//  CoinInfoAction.swift
//  iosApp
//
//  Created by Yazan Tarifi on 29/12/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

public class CoinInfoAction: NSObject, CoinaAction {
    public class GetInformationById: CoinInfoAction {
        var id: String
        init(id: String) {
            self.id = id
        }
    }
}
