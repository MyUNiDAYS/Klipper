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
    
    var body: some View {
        VStack {
            Image(systemName: "globe")
                .imageScale(.large)
                .foregroundColor(.accentColor)
            Button(action: {
                let viewModel = CatFactsViewModel()
                viewModel.makeNetworkRequest()
            }, label: {
                Text("Make network request!")
            })
        }.onAppear {
        }
        .padding()
    }
}
