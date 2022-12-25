//
//  AuthViewModel.swift
//  iosApp
//
//  Created by Yazan Tarifi on 24/12/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared
import MaterialComponents.MaterialSnackbar

@MainActor class AuthViewModel : ObservableObject, CoinaStateListener {
    
    @Published var isUserLoggedIn: Bool = false
    @Published var loadingState: Bool = false
    @Published var email: String = ""
    @Published var password: String = ""
    
    var viewModelImplementation: AuthViewModelImplementation? = nil
    
    func addViewModelImplementatio(impl: AuthViewModelImplementation) {
        self.viewModelImplementation = impl
        self.viewModelImplementation?.addStateListener(listener: self)
    }
    
    func onStatetriggered(state: CoinaState) {
        if state is AuthState.SuccessState {
            isUserLoggedIn = true
            CoinaApplicationUtils.updateUserLoggedInStatus(newStatus: true)
        }
        
        if state is AuthState.ErrorState {
            let errorMessage = (state as! AuthState.ErrorState).message
            let action = MDCSnackbarMessageAction()
            let message = MDCSnackbarMessage()
            message.text = errorMessage
            let actionHandler = {() in
//              let answerMessage = MDCSnackbarMessage()
//              answerMessage.text = "Fascinating"
//              MDCSnackbarManager.show(answerMessage)
            }
            action.handler = actionHandler
            action.title = "OK"
            MDCSnackbarManager.default.show(message)
        }
    }
    
    func onLoadingState(isLoading: Bool) {
        loadingState = isLoading
    }
    
}
