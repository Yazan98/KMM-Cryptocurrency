//
//  ExchangesViewModel.swift
//  iosApp
//
//  Created by Yazan Tarifi on 26/12/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

@MainActor class ExchangesViewModel : ObservableObject, CoinaStateListener {
    
    @Published var loadingState: Bool = false
    @Published var contentState: [ExchangeModel] = []
    var viewModelInstance: ExchangesListViewModel? = nil
    
    func initViewModelInstance(viewModelInstance: ExchangesListViewModel) {
        if self.viewModelInstance == nil {
            self.viewModelInstance = viewModelInstance
            self.viewModelInstance?.addStateListener(listener: self)
        }
    }
    
    func onStatetriggered(state: CoinaState) {
        if (state is ExchangesState.ListState) {
            let list = (state as! ExchangesState.ListState).exchanges
            contentState = list
        }
        
        if (state is ExchangesState.ErrorState) {
            let errorMessage = (state as! ExchangesState.ErrorState).message
            CoinaApplicationUtils.showMessage(errorMessage: errorMessage)
        }
    }
    
    func onLoadingState(isLoading: Bool) {
        self.loadingState = isLoading
    }
    
}
