//
//  CategoriesScreen.swift
//  iosApp
//
//  Created by Yazan Tarifi on 25/12/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct CategoriesScreen: View {
    
    @StateObject private var viewModel: CategoriesViewModel = CategoriesViewModel()
    
    var body: some View {
        VStack {
            if viewModel.loadingState {
                ProgressView().progressViewStyle(CircularProgressViewStyle())
            } else {
                if viewModel.listState.isEmpty == false {
                    ScrollView {
                        LazyVStack {
                            ForEach(Array(viewModel.listState.enumerated()), id: \.offset) { index, element in
                                NavigationLink(destination: CoinsListScreen(categoryTarget: element.id ?? "", categoryName: element.name ?? "")) {
                                    CategoryView(category: element, index: index)
                                }
                            }
                        }
                    }
                }
            }
        }
        .clipped()
        .onAppear {
            viewModel.addViewModelInstance(instance: CategoriesViewModelImplementation())
            viewModel.viewModelInstance?.executeAction(action: CategoriesAction.GetCategoriesAction())
        }
    }
}
