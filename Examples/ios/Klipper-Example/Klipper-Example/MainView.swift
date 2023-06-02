//
//  MainView.swift
//  Klipper-Example
//
//  Created by Andrew Reed on 02/06/2023.
//

import Foundation
import SwiftUI

struct MainView: View {
    
    var body: some View {
        NavigationView {
            VStack {
                Spacer()
                NavigationLink("Native", destination: NativeView())
                NavigationLink("KMM", destination: KMMView())
                Spacer()
            }
            .padding()
        }
    }
}
