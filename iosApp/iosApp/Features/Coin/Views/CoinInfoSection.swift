//
//  CoinInfoSection.swift
//  iosApp
//
//  Created by Yazan Tarifi on 29/12/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct CoinInfoSection: View {
    
    var item: CoinInfoItem
    init(item: CoinInfoItem) {
        self.item = item
    }
    
    var body: some View {
        HStack {
            Text(item.title)
                .foregroundColor(CoinaTheme.textColor)
            
            Spacer()
            
            Text(item.value)
                .foregroundColor(CoinaTheme.textColor)
        }
        .padding()
    }
}
