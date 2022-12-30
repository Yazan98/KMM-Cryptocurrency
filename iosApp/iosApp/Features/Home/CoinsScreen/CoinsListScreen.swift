//
//  CoinsListScreen.swift
//  iosApp
//
//  Created by Yazan Tarifi on 25/12/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct CoinsListScreen: View {
    
    @StateObject var viewModel: CoinsListViewModel = CoinsListViewModel()
    var categoryTarget: String = ""
    var categoryName: String = ""
    
    init(categoryTarget: String, categoryName: String) {
        self.categoryTarget = categoryTarget
        self.categoryName = categoryName
    }
    
    var body: some View {
        
        VStack {
            if viewModel.loadingState {
                ProgressView().progressViewStyle(CircularProgressViewStyle())
            } else {
                    if categoryTarget.isEmpty == true {
                        let binding = Binding<String>(get: {
                                    viewModel.searchQuery
                                }, set: {
                                    viewModel.searchQuery = $0
                                    viewModel.viewModelImplemenetation?.executeAction(action: CoinsListAction.GetCoinsBySearchQuery(query: viewModel.searchQuery))
                                })
                        
                        TextField("Search By Coin Symbol or Name", text: binding)
                            .padding()
                            .overlay(RoundedRectangle(cornerRadius: 8.0).strokeBorder(CoinaTheme.textColor, style: StrokeStyle(lineWidth: 1.0)))
                            .foregroundColor(CoinaTheme.textColor)
                            .padding(.trailing, 20)
                            .padding(.leading, 20)
                    } else {
                        Text("")
                            .navigationTitle(categoryName)
                            .navigationBarTitleDisplayMode(.inline)
                    }
                    
                    if viewModel.coinsList.isEmpty {
                        Spacer()
                    } else {
                        ScrollView {
                            LazyVStack {
                                ForEach(viewModel.coinsList, id: \.self) { item in
                                    CoinRowView(coin: item)
                                }
                            }
                        }
                    }
                }
                
        }
        .clipped()
        .onAppear {
                if viewModel.viewModelImplemenetation == nil {
                    viewModel.addViewModelImplementationInstance(instance: CoinsListViewModelImplementation())
                }
                
                if categoryTarget.isEmpty {
                    viewModel.viewModelImplemenetation?.executeAction(action: CoinsListAction.GetCoinsList())
                } else {
                    viewModel.viewModelImplemenetation?.executeAction(action: CoinsListAction.GetCoinsListByCategoryName(categoryName: categoryTarget))
                }
        }
        
        
    }
}
