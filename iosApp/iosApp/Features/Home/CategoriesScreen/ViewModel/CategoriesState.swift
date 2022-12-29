//
//  CategoriesState.swift
//  iosApp
//
//  Created by Yazan Tarifi on 29/12/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

public class CategoriesState : NSObject, CoinaState {
    public class LoadingState: CategoriesState {}
    public class ErrorState: CategoriesState {
        var message: String
        init(message: String) {
            self.message = message
        }
    }
    
    public class ListState: CategoriesState {
        var list: [CategoryModel]
        init(list: [CategoryModel]) {
            self.list = list
        }
    }
}
