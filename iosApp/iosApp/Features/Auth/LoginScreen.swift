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
            ZStack {
                CoinaTheme.backgroundColor.ignoresSafeArea()
                VStack {
                    if (viewModel.loadingState) {
                        ProgressView().progressViewStyle(CircularProgressViewStyle())
                    } else {
                        Spacer()
                        
                        Group {
                            Text("Welcome Back!")
                                .fontWeight(.bold)
                                .multilineTextAlignment(.center)
                                .foregroundColor(CoinaTheme.textColor)
                                .font(.system(size: 25))
                                .padding(.bottom, 5)
                            
                            Text("We Missed You")
                                .multilineTextAlignment(.center)
                                .foregroundColor(CoinaTheme.textColor)
                                .padding(.bottom, 25)
                        }
                        
                        Group {
                            TextField("Email", text: $viewModel.email)
                                .padding()
                                .overlay(RoundedRectangle(cornerRadius: 8.0).strokeBorder(CoinaTheme.textColor, style: StrokeStyle(lineWidth: 1.0)))
                                .foregroundColor(CoinaTheme.textColor)
                                .padding(.trailing, 20)
                                .padding(.leading, 20)
                            
                            SecureField("Password", text: $viewModel.password)
                                .padding()
                                .overlay(RoundedRectangle(cornerRadius: 8.0).strokeBorder(CoinaTheme.textColor, style: StrokeStyle(lineWidth: 1.0)))
                                .foregroundColor(CoinaTheme.textColor)
                                .padding(.trailing, 20)
                                .padding(.leading, 20)
                            
                            HStack {
                                Spacer()
                                Text("Forget Password ?")
                                    .padding(.trailing, 20)
                                    .padding(.leading, 20)
                                    .foregroundColor(CoinaTheme.textColor)
                                    .font(.system(size: 15))
                            }
                            
                            Button(action: {
                                if viewModel.viewModelImplementation == nil {
                                    viewModel.addViewModelImplementatio(impl: AuthViewModelImplementation())
                                }
                                
                                viewModel.viewModelImplementation?.executeAction(action: LoginAction(arguments: LoginArgs(email: viewModel.email, password: viewModel.password)))
                            }) {
                                Text("Login")
                                    .multilineTextAlignment(.center)
                                    .padding()
                                    .foregroundColor(Color.white)
                                    .frame(maxWidth: .infinity)
                                
                            }
                            .frame(maxWidth: .infinity)
                            .background(CoinaTheme.applicationColor)
                            .cornerRadius(8.0)
                            .padding(.trailing, 20)
                            .padding(.leading, 20)
                            .padding(.top, 10)
                        }
                        
                        HStack {
                            Spacer()
                            Rectangle().fill(CoinaTheme.textColor).frame(width: 55, height: 1, alignment: .center)
                            
                            Text("Continue With")
                                .foregroundColor(CoinaTheme.textColor)
                                .font(.system(size: 15))
                                .padding(.leading, 10)
                                .padding(.trailing, 10)
                            
                            Rectangle().fill(CoinaTheme.textColor).frame(width: 55, height: 1, alignment: .center)
                            Spacer()
                        }
                        .padding()
                        .padding(.top, 10)
                        
                        HStack {
                            Spacer()
                            Button(action: {
                                //Place your action here
                            }) {
                                Image("apple_logo")
                                    .resizable()
                                    .frame(width: 30, height: 30)
                                    .foregroundColor(.white)
                                    .padding(10)
                                    .background(Color.white)
                                    .clipShape(Circle())
                                
                            }
                            .padding()
                            .clipShape(Circle())
                            .shadow(color: Color.black.opacity(0.2), radius: 10.0)
                            
                            Button(action: {
                                //Place your action here
                            }) {
                                Image("facebook_image")
                                    .resizable()
                                    .frame(width: 30, height: 30)
                                    .foregroundColor(.white)
                                    .padding(10)
                                    .background(Color.white)
                                    .clipShape(Circle())
                                
                            }
                            .padding()
                            .clipShape(Circle())
                            .shadow(color: Color.black.opacity(0.2), radius: 10.0)
                            
                            Spacer()
                        }
                        
                        HStack {
                            Spacer()
                            Text("Not a Member ?")
                                .padding(.leading, 20)
                                .foregroundColor(CoinaTheme.textColor)
                                .font(.system(size: 15))
                            
                            Text("Register Now")
                                .foregroundColor(Color.blue)
                                .font(.system(size: 15))
                            
                           Spacer()
                        }
                        .padding()
                        .padding(.top, 10)
                        
                        Spacer()
                    }
                }
            }
            .navigationTitle("Coina")
            .navigationBarTitleDisplayMode(.inline)
        }
        .navigate(to: HomeScreen(), when: $viewModel.isUserLoggedIn)
    }
}

struct LoginScreen_Previews: PreviewProvider {
    static var previews: some View {
        LoginScreen()
    }
}
