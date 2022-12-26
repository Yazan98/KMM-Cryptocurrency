//
//  HomeScreen.swift
//  iosApp
//
//  Created by Yazan Tarifi on 25/12/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct HomeScreen: View {
    var body: some View {
        NavigationView {
            ZStack {
                CoinaTheme.backgroundColor.ignoresSafeArea()
                TabView {
                    CoinsListScreen()
                        .clipped()
                        .tabItem {
                            Image(systemName: "house")
                            Text("Coins")
                        }
                    CategoriesScreen()
                        .tabItem {
                            Image(systemName: "list.bullet")
                            Text("Categories")
                        }
                    ExchangesScreen()
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
