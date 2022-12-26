//
//  ExchangeView.swift
//  iosApp
//
//  Created by Yazan Tarifi on 26/12/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ExchangeView: View {
    
    var exchange: ExchangeModel
    init(exchange: ExchangeModel) {
        self.exchange = exchange
    }
    
    var body: some View {
        HStack {
            HStack {
                RemoteImageView(
                    url: URL(string: exchange.image ?? "")!,
                    placeholder: {
                      Image("placeholder").frame(width: 50)
                    },
                    image: {
                        $0 .resizable().frame(width: 50, height: 50).scaledToFit().clipShape(Circle())
                    }
                )
                .padding(.trailing, 5)
                
                VStack {
                    Text(exchange.name ?? "")
                        .multilineTextAlignment(.leading)
                        .frame(maxWidth: .infinity, alignment: .leading)
                        .foregroundColor(CoinaTheme.textColor)
                    Text(exchange.getCountryText())
                        .multilineTextAlignment(.leading)
                        .frame(maxWidth: .infinity, alignment: .leading)
                        .foregroundColor(Color.gray)
                }
            }
            
            var year = exchange.yearEstablished ?? 0
            VStack(alignment: .trailing) {
                if year != 0 {
                    Text(verbatim: "\(year)")
                        .frame(maxWidth: .infinity, alignment: .trailing)
                        .foregroundColor(CoinaTheme.textColor)
                }
                
            }
            
        }
        .padding()
    }
}
