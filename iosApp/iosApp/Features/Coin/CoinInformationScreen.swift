//
//  CoinInformationScreen.swift
//  iosApp
//
//  Created by Yazan Tarifi on 29/12/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct CoinInformationScreen: View {
    
    private var coinId: String = ""
    private var name: String = ""
    private var isPriceChanges: Bool = false
    @StateObject private var viewModel: CoinInfoViewModel = CoinInfoViewModel()
    
    init(coinId: String, name: String, isPriceChanges: Bool) {
        self.coinId = coinId
        self.name = name
        self.isPriceChanges = isPriceChanges
    }
    
    var body: some View {
        ZStack {
            CoinaTheme.backgroundColor.ignoresSafeArea()
            VStack(spacing: 8) {
                Text("")
                    .navigationTitle(name)
                    .navigationBarTitleDisplayMode(.inline)
                
                if viewModel.loadingState {
                    ProgressView().progressViewStyle(CircularProgressViewStyle())
                } else {
                    ScrollView {
                        LazyVStack {
                            ForEach(viewModel.screenContent, id: \.self) { item in
                                
                                if item.type == CoinInfoItem.companion.TYPE_TITLE {
                                    CoinInfoTitleView(item: item)
                                }
                                
                                if item.type == CoinInfoItem.companion.TYPE_SECTION_TITLE {
                                    CoinInfoTitleSection(item: item)
                                }
                                
                                if item.type == CoinInfoItem.companion.TYPE_SECTION {
                                    CoinInfoSection(item: item)
                                }
                                
                                if item.type == CoinInfoItem.companion.TYPE_DESCRIPTION {
                                    CoinInfoDescription(item: item)
                                }
                                
                                if item.type == CoinInfoItem.companion.TYPE_CHART {
                                    CoinChartView(coin: item, isPriceChangeds: isPriceChanges)
                                }
                            }
                        }
                    }
                }
            }
        }.onAppear {
            viewModel.addViewModelInstance(instance: CoinInfoViewModelImplementation())
            viewModel.viewModelInstance?.executeAction(action: CoinInfoAction.GetInformationById(id: coinId))
        }
    }
    
}
