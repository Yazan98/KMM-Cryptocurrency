//
//  CoinsListViewModel.swift
//  iosApp
//
//  Created by Yazan Tarifi on 25/12/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

@MainActor class CoinsListViewModel : ObservableObject, CoinaStateListener {
    
    @Published var searchQuery: String = ""
    @Published var coinsList: [CoinModel] = []
    
    var viewModelImplemenetation: CoinsListViewModelImplementation? = nil
    
    func addViewModelImplementationInstance(instance: CoinsListViewModelImplementation) {
        self.viewModelImplemenetation = instance
        self.viewModelImplemenetation?.addStateListener(listener: self)
    }
    
    func onStatetriggered(state: CoinaState) {
        if state is CoinsListState.ListState {
            let coinsList = (state as! CoinsListState.ListState).list
            self.coinsList = coinsList
        }
    }
    
    func onLoadingState(isLoading: Bool) {
        
    }
    
}
