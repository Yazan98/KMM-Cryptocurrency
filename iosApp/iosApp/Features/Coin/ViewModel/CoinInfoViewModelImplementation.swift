//
//  CoinInfoViewModelImplementation.swift
//  iosApp
//
//  Created by Yazan Tarifi on 29/12/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

public class CoinInfoViewModelImplementation : CoinaViewModel<CoinInfoAction, CoinInfoState> {
    
    private var stateListener: CoinaStateListener? = nil
    private var coinsUseCase = CoinInfoUseCase().addDependencies(apiManager: CoinaSingletonUtils.getApiManagerInstance())
        
    override init() {
        super.init()
        self.initializeViewModel()
    }
    
    func addStateListener(listener: CoinaStateListener) {
        self.stateListener = listener
    }
    
    public override func executeAction(action: CoinInfoAction) {
        DispatchQueue.global(qos: .background).async {
            if action is CoinInfoAction.GetInformationById {
                let coinId = (action as! CoinInfoAction.GetInformationById).id
                self.getCoinInfo(coinId: coinId)
            }
        }
    }
    
    private func getCoinInfo(coinId: String) {
        self.coinsUseCase.run(args: CoinInfoUseCase.Args(key: coinId))
    }
    
    public override func onListenerTriggered(key: String, value: CoinaApplicationState<AnyObject>) {
        DispatchQueue.main.async {
            if key == self.coinsUseCase.getUseCaseKey() {
                value.handleResult(onSuccess: { result in
                    let info = (result as! CoinInformation)
                    let coinsList: [CoinInfoItem] = CoinInfoItem.companion.getScreenItems(coinInformation: info) as! [CoinInfoItem]
                    self.stateListener?.onStatetriggered(state: CoinInfoState.ListState(items: coinsList))
                }, onError: { exception in
                    self.stateListener?.onStatetriggered(state: CoinInfoState.ErrorState(message: exception.exception?.message ?? ""))
                }, onLoading: { loadingState in
                    self.stateListener?.onLoadingState(isLoading: loadingState as! Bool)
                })
            }
        }
    }
    
    public override func getInitialState() -> CoinInfoState {
        return CoinInfoState.LoadingState()
    }
    
    public override func getSupportedUseCases() -> NSMutableArray {
        return [coinsUseCase]
    }
    
    deinit {
        clear_()
        self.stateListener = nil
    }
    
}
