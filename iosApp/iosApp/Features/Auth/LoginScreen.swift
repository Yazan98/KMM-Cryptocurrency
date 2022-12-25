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
        VStack {
            TextField("Email", text: $viewModel.email)
                .padding()
            
            SecureField("Password", text: $viewModel.password)
                .padding()

            Button(action: {
                viewModel.viewModelImplementation?.executeAction(action: AuthAction.LoginAction(arguments: AuthAction.LoginArgs(email: viewModel.email, password: viewModel.password)))
            }) {
                Text("Log In")
            }
            .padding()
        }   
    }
}

struct LoginScreen_Previews: PreviewProvider {
    static var previews: some View {
        LoginScreen()
    }
}
