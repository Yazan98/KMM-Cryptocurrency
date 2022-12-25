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
    
    var body: some View {
            VStack {
                TextField("Search By Coin Symbol or Name", text: $viewModel.searchQuery)
                    .padding()
                    .overlay(RoundedRectangle(cornerRadius: 8.0).strokeBorder(CoinaTheme.textColor, style: StrokeStyle(lineWidth: 1.0)))
                    .foregroundColor(CoinaTheme.textColor)
                    .padding(.trailing, 20)
                    .padding(.leading, 20)
                
                if viewModel.coinsList.isEmpty {
                    Spacer()
                } else {
                    ScrollView {
                        LazyVStack {
                            ForEach(viewModel.coinsList, id: \.self) { item in
                                Text(item.name ?? "")
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
                
                viewModel.viewModelImplemenetation?.executeAction(action: CoinsListAction.GetCoinsList())
            }
        }
}

struct CoinsListScreen_Previews: PreviewProvider {
    static var previews: some View {
        CoinsListScreen()
    }
}
