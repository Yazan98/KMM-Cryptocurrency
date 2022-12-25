//
//  AuthViewModelImplementation.swift
//  iosApp
//
//  Created by Yazan Tarifi on 24/12/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

class AuthViewModelImplementation : CoinaViewModel<AuthAction, AuthState> {
    
    override init() {
        super.init()
        self.initializeViewModel()
    }
    
    override func executeAction(action: AuthAction) {
        if (action is AuthAction.LoginAction) {
            onLoginUserAction(email: "", password: "")
        }
    }
    
    private func onLoginUserAction(email: String, password: String) {
        
    }
    
    override func onListenerTriggered(key: String, value: CoinaApplicationState<AnyObject>) {
        
    }
    
    override func onExceptionListenerTriggered(key: String, value: KotlinThrowable) {
        print("Exception : \(String(describing: value.message))")
    }
    
    override func getSupportedUseCases() -> NSMutableArray {
        return [AuthUseCase().addDependencies(apiManager: ApplicationApiManager().addHttpClient(httpClient: HttpBaseClient().httpClient), database: CoinsDataSource())]
    }
    
    
    
}
