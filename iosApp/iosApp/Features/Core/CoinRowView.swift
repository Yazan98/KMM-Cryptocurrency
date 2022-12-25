//
//  CoinRowView.swift
//  iosApp
//
//  Created by Yazan Tarifi on 25/12/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct CoinRowView: View {
    var coin: CoinModel
    var body: some View {
        HStack {
            RemoteImageView(
                url: URL(string: coin.image ?? "")!,
                placeholder: {
                  Image("placeholder").frame(width: 50) // etc.
                },
                image: {
                    $0 .resizable().frame(width: 50, height: 50).scaledToFit().clipShape(Circle()) // etc.
                }
            )
            .padding(.trailing, 5)
            
            VStack {
                Text(coin.name ?? "")
                    .multilineTextAlignment(.leading)
                Text(coin.symbol ?? "")
                    .multilineTextAlignment(.leading)
                    .textCase(.uppercase)
                    .frame(width: 80)
            }
            .frame(width: 80)
            
            Spacer()
            
            VStack {
                Text("$\(PriceExt().formatDecimalSeparator(price: Double(truncating: coin.price!)))")
                let priceChange: Double = Double(truncating: coin.percentChange!)
                if priceChange > 0.0 {
                    Text("\(coin.getPriceChangeText()) %")
                        .foregroundColor(Color.green)
                } else {
                    Text("\(coin.getPriceChangeText()) %")
                        .foregroundColor(Color.red)
                }
            
            }
        }
        .padding()
    }
}
