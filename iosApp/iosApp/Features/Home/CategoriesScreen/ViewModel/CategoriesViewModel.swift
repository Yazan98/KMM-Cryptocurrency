//
//  CategoriesViewModel.swift
//  iosApp
//
//  Created by Yazan Tarifi on 29/12/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

@MainActor public class CategoriesViewModel: ObservableObject, CoinaStateListener {
    
    @Published var listState: [CategoryModel] = []
    @Published var loadingState: Bool = false
    var viewModelInstance: CategoriesViewModelImplementation? = nil
    
    func addViewModelInstance(instance: CategoriesViewModelImplementation) {
        if viewModelInstance == nil {
            viewModelInstance = instance
            viewModelInstance?.addStateListener(listener: self)
        }
    }
    
    func onStatetriggered(state: CoinaState) {
        if state is CategoriesState.ListState {
            let categories = (state as! CategoriesState.ListState).list
            listState = categories
        }
        
        if state is CategoriesState.ErrorState {
            let errorMessage = (state as! CategoriesState.ErrorState).message
            CoinaApplicationUtils.showMessage(errorMessage: errorMessage)
        }
    }
    
    func onLoadingState(isLoading: Bool) {
        loadingState = isLoading
    }
    
}
