//
//  KMMView.swift
//  Klipper-Example
//
//  Created by Andrew Reed on 02/06/2023.
//

import Foundation
import SwiftUI
import library

struct KMMView: View {
    
    let viewModel = CatFactsViewModel(context: nil)
    
    var body: some View {
        VStack {
            Image(systemName: "globe")
                .imageScale(.large)
                .foregroundColor(.accentColor)
            Button(action: {
                viewModel.startClient()
            }, label: {
                Text("Connect to flipper")
            })
            Button(action: {
                viewModel.makeNetworkRequest()
            }, label: {
                Text("Make network request!")
            })
        }.onDisappear {
            viewModel.closeClient()
        }
        .padding()
    }
}
