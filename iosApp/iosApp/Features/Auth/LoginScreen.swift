//
//  LoginScreen.swift
//  iosApp
//
//  Created by Yazan Tarifi on 24/12/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct LoginScreen: View {
    
    @StateObject private var viewModel = AuthViewModel()

    var body: some View {
        NavigationView {
            VStack {
                if (viewModel.loadingState) {
                    ProgressView().progressViewStyle(CircularProgressViewStyle())
                } else {
                    TextField("Email", text: $viewModel.email)
                        .padding()
                    
                    SecureField("Password", text: $viewModel.password)
                        .padding()

                    Button(action: {
                        if viewModel.viewModelImplementation == nil {
                            viewModel.addViewModelImplementatio(impl: AuthViewModelImplementation())
                        }
                        
                        viewModel.viewModelImplementation?.executeAction(action: LoginAction(arguments: LoginArgs(email: viewModel.email, password: viewModel.password)))
                    }) {
                        Text("Log In")
                    }
                    .padding()
                }
            }   
        }
        .navigationTitle("Coina")
        .navigationBarTitleDisplayMode(.inline)
        .navigate(to: HomeScreen(), when: $viewModel.isUserLoggedIn)
    }
}

struct LoginScreen_Previews: PreviewProvider {
    static var previews: some View {
        LoginScreen()
    }
}
