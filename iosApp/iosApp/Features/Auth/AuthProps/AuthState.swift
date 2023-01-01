//
//  AuthState.swift
//  iosApp
//
//  Created by Yazan Tarifi on 24/12/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

class AuthState : NSObject, CoinaState {
    class LoadingState: AuthState {}
    class SuccessState: AuthState {}
    class EmptyState: AuthState {}
    class ErrorState: AuthState {
        var message: String = ""
        init(message: String) {
            self.message = message
        }
    }
}
