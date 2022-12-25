//
//  AuthViewModelImplementation.swift
//  iosApp
//
//  Created by Yazan Tarifi on 24/12/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

@MainActor class AuthViewModelImplementation : CoinaViewModel<AuthAction, AuthState> {
    
    private var stateListener: CoinaStateListener? = nil
    private var authUseCase: AuthUseCase = AuthUseCase().addDependencies(apiManager: CoinaSingletonUtils.getApiManagerInstance(), database: CoinaSingletonUtils.getCoinsDataSourceInstance())
    
    override init() {
        super.init()
        self.initializeViewModel()
    }
    
    func addStateListener(listener: CoinaStateListener) {
        self.stateListener = listener
    }
    
    override func executeAction(action: AuthAction) {
        if (action is LoginAction) {
            onLoginUserAction(action: action)
        }
    }
    
    private func onLoginUserAction(action: AuthAction) {
        let loginAction = action as! LoginAction
        self.authUseCase.run(args: AuthUseCase.Args(email: loginAction.getArguments().email, password: loginAction.getArguments().password))
    }
    
    override func onListenerTriggered(key: String, value: CoinaApplicationState<AnyObject>) {
        if key == self.authUseCase.getUseCaseKey() {
            value.handleResult(onSuccess: { payload in
                self.stateListener?.onStatetriggered(state: AuthState.SuccessState())
            }, onError: { exception in
                self.stateListener?.onStatetriggered(state: AuthState.ErrorState(message: exception.exception?.message ?? ""))
            }, onLoading: { loadingState in
                self.stateListener?.onLoadingState(isLoading: loadingState as! Bool)
            })
        }
    }
    
    override func onExceptionListenerTriggered(key: String, value: KotlinThrowable) {
        print("Exception Triggered")
        self.stateListener?.onStatetriggered(state: AuthState.ErrorState(message: value.message ?? ""))
    }
    
    override func getSupportedUseCases() -> NSMutableArray {
        return [authUseCase]
    }
    
    deinit {
        clear_()
        self.stateListener = nil
    }
    
}
