//
//  CoinInfoTitleView.swift
//  iosApp
//
//  Created by Yazan Tarifi on 29/12/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct CoinInfoTitleView: View {
    
    var item: CoinInfoItem
    init(item: CoinInfoItem) {
        self.item = item
    }
    
    var body: some View {
        HStack {
            Text(item.title)
                .foregroundColor(CoinaTheme.textColor)
            
            Spacer()
            
            HStack {
                Text(item.value.uppercased())
                    .foregroundColor(CoinaTheme.textColor)
                
                RemoteImageView(
                    url: URL(string: item.image ?? "")!,
                    placeholder: {
                      Image("placeholder").frame(width: 24)
                    },
                    image: {
                        $0 .resizable().frame(width: 24, height: 24).scaledToFit().clipShape(Circle())
                    }
                )
                .padding(.leading, 5)
            }
        }
        .padding()
    }
}
