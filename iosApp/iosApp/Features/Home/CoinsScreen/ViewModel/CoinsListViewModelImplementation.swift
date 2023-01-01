//
//  CoinsListViewModelImplementation.swift
//  iosApp
//
//  Created by Yazan Tarifi on 25/12/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

class CoinsListViewModelImplementation : CoinaViewModel<CoinsListAction, CoinsListState> {
    
    private var stateListener: CoinaStateListener? = nil
    private var categoryCoinsUseCase = GetCategoryCoinsUseCase().addDependencies(apiManager: CoinaSingletonUtils.getApiManagerInstance())
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
        DispatchQueue.global(qos: .background).async {
            if action is CoinsListAction.GetCoinsList {
                self.getCoinsList()
            }
            
            if action is CoinsListAction.GetCoinsListByCategoryName {
                let categoryName = (action as! CoinsListAction.GetCoinsListByCategoryName).categoryName
                self.getCoinsByCategoryName(categoryName: categoryName)
            }
            
            if action is CoinsListAction.GetCoinsBySearchQuery {
                let query = (action as! CoinsListAction.GetCoinsBySearchQuery).query
                self.getCoinsBySearchQuery(query: query)
            }
        }
    }
    
    private func getCoinsBySearchQuery(query: String) {
        let dataSourceResult = CoinaSingletonUtils.getCoinsDataSourceInstance().getCoinsBySearchQuery(query: query)
        DispatchQueue.main.async {
            self.stateListener?.onStatetriggered(state: CoinsListState.ListState(list: dataSourceResult as! [CoinModel]))
        }
    }
    
    private func getCoinsByCategoryName(categoryName: String) {
        self.categoryCoinsUseCase.run(args: GetCategoryCoinsUseCase.Args(categoryName: categoryName))
    }
    
    private func getCoinsList() {
        self.coinsUseCase.run(args: KotlinUnit())
    }
    
    override func onListenerTriggered(key: String, value: CoinaApplicationState<AnyObject>) {
        DispatchQueue.main.async {
            if key == self.coinsUseCase.getUseCaseKey() || key == self.categoryCoinsUseCase.getUseCaseKey() {
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
    }
    
    override func getSupportedUseCases() -> NSMutableArray {
        return [coinsUseCase, categoryCoinsUseCase]
    }
    
    override func getInitialState() -> CoinsListState {
        let dataSource = CoinaSingletonUtils.getCoinsDataSourceInstance()
        if dataSource.isDataSourceEmpty() {
            return CoinsListState.LoadingState()
        } else {
            return CoinsListState.ListState(list: dataSource.getCoinsBySearchQuery(query: "") as! [CoinModel])
        }
    }
    
    deinit {
        clear_()
        self.stateListener = nil
    }
    
}
