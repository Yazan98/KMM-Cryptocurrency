//
//  ExchangesListViewModel.swift
//  iosApp
//
//  Created by Yazan Tarifi on 26/12/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

@MainActor class ExchangesListViewModel : CoinaViewModel<ExchangesAction, ExchangesState> {
    
    private var stateListener: CoinaStateListener? = nil
    private var exchangesUseCase = GetExchangesUseCase().addDependencies(
        apiManager: CoinaSingletonUtils.getApiManagerInstance(),
        database: CoinaSingletonUtils.getExchangesDataSourceInstance()
    )
    
    override init() {
        super.init()
        self.initializeViewModel()
    }
    
    func addStateListener(listener: CoinaStateListener) {
        self.stateListener = listener
    }
    
    override func executeAction(action: ExchangesAction) {
        if action is ExchangesAction.GetExchangesList {
            self.getExchangesList()
        }
    }
    
    private func getExchangesList() {
        exchangesUseCase.run(args: KotlinUnit())
    }
    
    override func onListenerTriggered(key: String, value: CoinaApplicationState<AnyObject>) {
        if key == exchangesUseCase.getUseCaseKey() {
            value.handleResult(onSuccess: { result in
                let exchangesList = result as! [ExchangeModel]
                self.stateListener?.onStatetriggered(state: ExchangesState.ListState(exchanges: exchangesList))
            }, onError: { exception in
                self.stateListener?.onStatetriggered(state: ExchangesState.ErrorState(message: exception.exception?.message ?? ""))
            }, onLoading: { loadingState in
                self.stateListener?.onLoadingState(isLoading: loadingState as! Bool)
            })
        }
    }
    
    override func onExceptionListenerTriggered(key: String, value: KotlinThrowable) {
    
    }
    
    override func getSupportedUseCases() -> NSMutableArray {
        return [exchangesUseCase]
    }
    
    deinit {
        clear_()
        self.stateListener = nil
    }
    
}
