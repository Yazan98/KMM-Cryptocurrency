//
//  CoinChartView.swift
//  iosApp
//
//  Created by Yazan Tarifi on 30/12/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct CoinChartView: View {
    
        private let data: [Double]
        private let maxY: Double
        private let minY: Double
        private let lineColor: Color
        private let startingDate: Date
        private let endingDate: Date
        private var isPriceChangeds: Bool = false
        @State private var percentage: CGFloat = 0
        
    init(coin: CoinInfoItem, isPriceChangeds: Bool) {
            data = (coin.chart ?? []) as! [Double]
            maxY = data.max() ?? 0
            minY = data.min() ?? 0
        self.isPriceChangeds = isPriceChangeds
            
            let priceChange = (data.last ?? 0) - (data.first ?? 0)
            lineColor = priceChange > 0 ? Color.green : Color.red
            
            endingDate = Date(coinGeckoString: coin.lastUpdated ?? "")
            startingDate = endingDate.addingTimeInterval(-7*24*60*60)
        }
        
        var body: some View {
            VStack {
                chartView
                    .frame(height: 200)
                    .background(chartBackground)
                    .overlay(chartYAxis.padding(.horizontal, 4), alignment: .leading)
                
                chartDateLabels
                    .padding(.horizontal, 4)
            }
            .font(.caption)
            .foregroundColor(CoinaTheme.textColor)
            .onAppear {
                DispatchQueue.main.asyncAfter(deadline: .now() + 0.2) {
                    withAnimation(.linear(duration: 2.0)) {
                        percentage = 1.0
                    }
                }
            }
            
        }
    }

    extension CoinChartView {
        
        private var chartView: some View {
            GeometryReader { geometry in
                Path { path in
                    for index in data.indices {
                        
                        let xPosition = geometry.size.width / CGFloat(data.count) * CGFloat(index + 1)
                        
                        let yAxis = maxY - minY
                        
                        let yPosition = (1 - CGFloat((data[index] - minY) / yAxis)) * geometry.size.height
                        
                        if index == 0 {
                            path.move(to: CGPoint(x: xPosition, y: yPosition))
                        }
                        path.addLine(to: CGPoint(x: xPosition, y: yPosition))
                        
                    }
                }
                .trim(from: 0, to: percentage)
                .stroke(lineColor, style: StrokeStyle(lineWidth: 2, lineCap: .round, lineJoin: .round))
                .shadow(color: lineColor, radius: 10, x: 0.0, y: 10)
                .shadow(color: lineColor.opacity(0.5), radius: 10, x: 0.0, y: 20)
                .shadow(color: lineColor.opacity(0.2), radius: 10, x: 0.0, y: 30)
                .shadow(color: lineColor.opacity(0.1), radius: 10, x: 0.0, y: 40)
            }
        }
        
        private var chartBackground: some View {
            VStack {
                Divider()
                Spacer()
                Divider()
                Spacer()
                Divider()
            }
        }
        
        private var chartYAxis: some View {
            VStack {
                Text(maxY.formattedWithAbbreviations())
                Spacer()
                Text(((maxY + minY) / 2).formattedWithAbbreviations())
                Spacer()
                Text(minY.formattedWithAbbreviations())
            }
        }
        
        private var chartDateLabels: some View {
            HStack {
                Text(startingDate.asShortDateString())
                Spacer()
                Text(endingDate.asShortDateString())
            }
        }
        
}
