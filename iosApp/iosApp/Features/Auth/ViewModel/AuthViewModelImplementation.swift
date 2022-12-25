//
//  AuthViewModelImplementation.swift
//  iosApp
//
//  Created by Yazan Tarifi on 24/12/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

class AuthViewModelImplementation : CoinaViewModel<AuthAction, AuthState>, CoinaStateListener<AuthState>, CoinaLoadingStateListener {
    
    override init() {
        registerStateListener(targetStateListener: self)
        registerLoadingStateListener(targetStateListener: self)
    }
    
    override func onNewAction(action: AuthAction) {
        <#code#>
    }
    
    func onUpdateState(newState: CoinaState) {
        <#code#>
    }
    
    func onLoadingStateChanged(newState: Bool) {
        <#code#>
    }
    
    
}
