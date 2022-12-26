//
//  CoinsListViewModelImplementation.swift
//  iosApp
//
//  Created by Yazan Tarifi on 25/12/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

@MainActor class CoinsListViewModelImplementation : CoinaViewModel<CoinsListAction, CoinsListState> {
    
    private var stateListener: CoinaStateListener? = nil
    private var coinsUseCase = GetCoinsUseCase().addDependencies(
        apiManager: CoinaSingletonUtils.getApiManagerInstance(),
        database: CoinaSingletonUtils.getCoinsDataSourceInstance()
    )
    
    override init() {
        super.init()
        self.initializeViewModel()
    }
    
    func addStateListener(listener: CoinaStateListener) {
        self.stateListener = listener
    }
    
    override func executeAction(action: CoinsListAction) {
        if action is CoinsListAction.GetCoinsList {
            self.getCoinsList()
        }
    }
    
    private func getCoinsList() {
        self.coinsUseCase.run(args: KotlinUnit())
    }
    
    override func onListenerTriggered(key: String, value: CoinaApplicationState<AnyObject>) {
        if key == coinsUseCase.getUseCaseKey() {
            value.handleResult(onSuccess: { result in
                let coinsList = result as! [CoinModel]
                self.stateListener?.onStatetriggered(state: CoinsListState.ListState(list: coinsList))
            }, onError: { exception in
                self.stateListener?.onStatetriggered(state: CoinsListState.ErrorState(message: exception.exception?.message ?? ""))
            }, onLoading: { loadingState in
                self.stateListener?.onLoadingState(isLoading: loadingState as! Bool)
            })
        }
    }
    
    override func onExceptionListenerTriggered(key: String, value: KotlinThrowable) {
    
    }
    
    override func getSupportedUseCases() -> NSMutableArray {
        return [coinsUseCase]
    }
    
    deinit {
        clear_()
        self.stateListener = nil
    }
    
}
