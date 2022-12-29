//
//  CategoryView.swift
//  iosApp
//
//  Created by Yazan Tarifi on 29/12/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct CategoryView: View {
    
    var category: CategoryModel
    var index: Int
    init(category: CategoryModel, index: Int) {
        self.category = category
        self.index = index
    }
    
    var body: some View {
        HStack(alignment: .firstTextBaseline) {
            Text(verbatim: "\(index + 1)")
                .foregroundColor(CoinaTheme.textColor)
                .padding(.trailing, 3)
                .frame(width: 30)
            
            HStack(spacing: 0) {
                let images = category.topCoins as! [String]
                ForEach(images, id: \.self) { item in
                    RemoteImageView(
                        url: URL(string: item)!,
                        placeholder: {
                          Image("placeholder").frame(width: 17)
                        },
                        image: {
                            $0.resizable().frame(width: 17, height: 17).scaledToFit().clipShape(Circle())
                        }
                    )
                }
            }
            .padding(.trailing, 10)
            .frame(width: 70)
            
            Text(category.name ?? "")
                .foregroundColor(CoinaTheme.textColor)
                .lineLimit(1)
            
            Spacer()
        }
        .padding(5)
    }
}
