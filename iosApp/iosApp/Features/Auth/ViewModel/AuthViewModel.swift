//
//  AuthViewModel.swift
//  iosApp
//
//  Created by Yazan Tarifi on 24/12/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation

@MainActor class AuthViewModel : ObservableObject {
    
    @Published var isUserLoggedIn: Bool = false
    @Published var email: String = ""
    @Published var password: String = ""
    
    var viewModelImplementation: AuthViewModelImplementation? = nil
    
    init(viewModelImplementation: AuthViewModelImplementation? = nil) {
        self.viewModelImplementation = viewModelImplementation
    }
    
}
