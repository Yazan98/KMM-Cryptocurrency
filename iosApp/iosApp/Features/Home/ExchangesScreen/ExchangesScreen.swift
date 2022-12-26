//
//  ExchangesScreen.swift
//  iosApp
//
//  Created by Yazan Tarifi on 25/12/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct ExchangesScreen: View {
    
    @StateObject private var viewModel: ExchangesViewModel = ExchangesViewModel()
    
    var body: some View {
        VStack {
            if viewModel.loadingState {
                ProgressView().progressViewStyle(CircularProgressViewStyle())
            } else {
                if viewModel.contentState.isEmpty == false {
                    ScrollView {
                        LazyVStack {
                            ForEach(viewModel.contentState, id: \.self) { item in
                                ExchangeView(exchange: item)
                            }
                        }
                    }
                }
            }
        }
        .clipped()
        .onAppear {
            viewModel.initViewModelInstance(viewModelInstance: ExchangesListViewModel())
            viewModel.viewModelInstance?.executeAction(action: ExchangesAction.GetExchangesList())
        }
    }
}

struct ExchangesScreen_Previews: PreviewProvider {
    static var previews: some View {
        ExchangesScreen()
    }
}
