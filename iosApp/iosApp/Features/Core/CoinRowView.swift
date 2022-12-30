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
        NavigationLink(destination: CoinInformationScreen(coinId: coin.id ?? "", name: coin.name ?? "")) {
            HStack {
                HStack {
                    RemoteImageView(
                        url: URL(string: coin.image ?? "")!,
                        placeholder: {
                          Image("placeholder").frame(width: 50)
                        },
                        image: {
                            $0 .resizable().frame(width: 50, height: 50).scaledToFit().clipShape(Circle())
                        }
                    )
                    .padding(.trailing, 5)
                    
                    VStack {
                        Text(coin.name ?? "")
                            .multilineTextAlignment(.leading)
                            .frame(maxWidth: .infinity, alignment: .leading)
                            .foregroundColor(CoinaTheme.textColor)
                        Text(coin.symbol ?? "")
                            .multilineTextAlignment(.leading)
                            .frame(maxWidth: .infinity, alignment: .leading)
                            .textCase(.uppercase)
                            .foregroundColor(Color.gray)
                    }
                }
                
                Spacer()
                
                VStack(alignment: .trailing) {
                    Text("$\(PriceExt().formatDecimalSeparator(price: Double(truncating: coin.price!)))")
                        .frame(maxWidth: .infinity, alignment: .trailing)
                        .foregroundColor(CoinaTheme.textColor)
                    
                    let priceChange: Double = Double(truncating: coin.percentChange!)
                    
                    HStack {
                        if priceChange > 0.0 {
                            Image(systemName: "chevron.up")
                                .renderingMode(.template)
                                .foregroundColor(.green)
                            
                            Text("\(coin.getPriceChangeText()) %")
                                .foregroundColor(Color.green)
                                .frame(maxWidth: .infinity, alignment: .trailing)
                        } else {
                            Image(systemName: "chevron.down")
                                .renderingMode(.template)
                                .foregroundColor(.red)
                                
                            
                            Text("\(coin.getPriceChangeText()) %")
                                .foregroundColor(Color.red)
                                .frame(maxWidth: .infinity, alignment: .trailing)
                        }
                    }
                    .frame(width: 90)
                }
                
            }
            .padding()
        }
    }
}


struct CoinRowView_Previews: PreviewProvider {
    static var previews: some View {
        CoinRowView(coin: CoinModel(id: "", symbol: "BTC", name: "BitCoin", image: "", price: 0.0, marketGapRank: 1, percentChange: 0.0))
    }
}
