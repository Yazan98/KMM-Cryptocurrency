//
//  AuthAction.swift
//  iosApp
//
//  Created by Yazan Tarifi on 24/12/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

public class AuthAction : NSObject, CoinaAction {}

public class LoginAction: AuthAction {

    public var arguments: LoginArgs? = nil
    
    init(arguments: LoginArgs) {
        self.arguments = arguments
    }
    
    public func getArguments() -> LoginArgs {
        return self.arguments ?? LoginArgs(email: "", password: "")
    }
}

public struct LoginArgs {
    public var email: String
    public var password: String
}
