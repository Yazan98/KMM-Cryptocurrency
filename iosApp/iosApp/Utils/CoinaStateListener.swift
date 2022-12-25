//
//  CoinaStateListener.swift
//  iosApp
//
//  Created by Yazan Tarifi on 25/12/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

@MainActor protocol CoinaStateListener {
    
    func onLoadingState(isLoading: Bool)
    
    func onStatetriggered(state: CoinaState)
    
}
