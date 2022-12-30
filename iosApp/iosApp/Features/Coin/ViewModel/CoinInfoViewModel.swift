//
//  CoinInfoViewModel.swift
//  iosApp
//
//  Created by Yazan Tarifi on 29/12/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

public class CoinInfoViewModel : ObservableObject, CoinaStateListener {
    
    @Published var screenContent: [CoinInfoItem] = []
    @Published var loadingState: Bool = false
    var viewModelInstance: CoinInfoViewModelImplementation? = nil
    
    func addViewModelInstance(instance: CoinInfoViewModelImplementation) {
        if viewModelInstance == nil {
            viewModelInstance = CoinInfoViewModelImplementation()
            viewModelInstance?.addStateListener(listener: self)
        }
    }
    
    func onStatetriggered(state: CoinaState) {
        if state is CoinInfoState.ListState {
            let list = (state as! CoinInfoState.ListState).items
            screenContent = list
        }
        
        if state is CoinInfoState.ErrorState {
            var errorMessage = (state as! CoinInfoState.ErrorState).message
            CoinaApplicationUtils.showMessage(errorMessage: errorMessage)
        }
    }
    
    func onLoadingState(isLoading: Bool) {
        loadingState = isLoading
    }
    
    
}
