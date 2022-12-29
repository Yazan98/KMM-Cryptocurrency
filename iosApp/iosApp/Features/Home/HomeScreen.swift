//
//  HomeScreen.swift
//  iosApp
//
//  Created by Yazan Tarifi on 25/12/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct HomeScreen: View {
    
    private let coinsListScreen = CoinsListScreen(categoryTarget: "", categoryName: "")
    private let categoriesListScreen = CategoriesScreen()
    private let exchangesListScreen = ExchangesScreen()
    
    var body: some View {
        NavigationView {
            ZStack {
                CoinaTheme.backgroundColor.ignoresSafeArea()
                TabView {
                    coinsListScreen
                        .clipped()
                        .tabItem {
                            Image(systemName: "house")
                            Text("Coins")
                        }
                    categoriesListScreen
                        .tabItem {
                            Image(systemName: "list.bullet")
                            Text("Categories")
                        }
                    exchangesListScreen
                        .tabItem {
                            Image(systemName: "star.fill")
                            Text("Exchanges")
                        }
                }
                .navigationBarTitle("Coina")
                .navigationBarTitleDisplayMode(.inline)
            }
        }
        
    }
}

struct HomeScreen_Previews: PreviewProvider {
    static var previews: some View {
        HomeScreen()
    }
}
