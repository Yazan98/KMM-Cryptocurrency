//
//  CategoriesViewModelImplementation.swift
//  iosApp
//
//  Created by Yazan Tarifi on 29/12/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

@MainActor public class CategoriesViewModelImplementation : CoinaViewModel<CategoriesAction, CategoriesState> {
    
    private var stateListener: CoinaStateListener? = nil
    private var categoriesUseCase = GetCategoriesUseCase().addDependencies(
        apiManager: CoinaSingletonUtils.getApiManagerInstance(),
        database: CoinaSingletonUtils.getCategoriesDataSource()
    )
    
    override init() {
        super.init()
        self.initializeViewModel()
    }
    
    func addStateListener(listener: CoinaStateListener) {
        self.stateListener = listener
    }
    
    public override func executeAction(action: CategoriesAction) {
        if action is CategoriesAction.GetCategoriesAction {
            self.getCategoriesList()
        }
    }
    
    private func getCategoriesList() {
        self.categoriesUseCase.run(args: KotlinUnit())
    }
    
    public override func onListenerTriggered(key: String, value: CoinaApplicationState<AnyObject>) {
        if key == categoriesUseCase.getUseCaseKey() {
            value.handleResult(onSuccess: { result in
                let categoriesList = result as! [CategoryModel]
                self.stateListener?.onStatetriggered(state: CategoriesState.ListState(list: categoriesList))
            }, onError: { exception in
                self.stateListener?.onStatetriggered(state: CategoriesState.ErrorState(message: exception.exception?.message ?? ""))
            }, onLoading: { loadingState in
                self.stateListener?.onLoadingState(isLoading: loadingState as! Bool)
            })
        }
    }
    
    public override func onExceptionListenerTriggered(key: String, value: KotlinThrowable) {
    
    }
    
    public override func getSupportedUseCases() -> NSMutableArray {
        return [categoriesUseCase]
    }
    
    deinit {
        clear_()
        self.stateListener = nil
    }
    
}
