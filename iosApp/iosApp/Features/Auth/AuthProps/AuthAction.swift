//
//  AuthAction.swift
//  iosApp
//
//  Created by Yazan Tarifi on 24/12/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

class AuthAction : NSObject, CoinaAction {
    class LoginAction: AuthAction {
        private var arguments: LoginArgs? = nil
        
        init(arguments: LoginArgs) {
            self.arguments = arguments
        }
        
        public func getArguments() -> LoginArgs {
            return self.arguments ?? LoginArgs(email: "", password: "")
        }
    }
    
    struct LoginArgs {
        var email: String
        var password: String
    }
}
