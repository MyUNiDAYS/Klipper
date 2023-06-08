//
//  ContentView.swift
//  Klipper-Example
//
//  Created by Andrew Reed on 22/05/2023.
//

import SwiftUI
import Foundation
import klipper
import FlipperKit


struct Fact : Codable {
    let fact: String
    let length: Int
}

struct NativeView: View {
    
    let userDefaults: UserDefaults = UserDefaults.init(suiteName: "klipper_example")!
        
    var body: some View {
        VStack {
            Image(systemName: "globe")
                .imageScale(.large)
                .foregroundColor(.accentColor)
            Button(action: {
                let flipperClient = FlipperClientKt.sharedClient()
                flipperClient.start()
                let network = NetworkFlipperPluginKt.doInitWithNetworkAdapter(networkAdapter: SKNetworkAdapterKt.create())
                flipperClient.addPlugin(plugin: network as! FlipperPlugin)
                let userDefaults = FKUserDefaultsPluginKt.doInitWithName(name: "klipper_example")
                flipperClient.addPlugin(plugin: userDefaults as! FlipperPlugin)
            }, label: {
                Text("Connect to flipper")
            })
            Button(action: {
                guard let url = URL(string: "https://catfact.ninja/fact") else { fatalError("Missing URL") }
                let urlRequest = URLRequest(url: url)
                let dataTask = URLSession.shared.dataTask(with: urlRequest) { (data, response, error) in
                    if let error = error {
                        print("Request error: ", error)
                        return
                    }
                    guard let response = response as? HTTPURLResponse else { return }
                    if response.statusCode == 200 {
                        guard let data = data else { return }
                        DispatchQueue.main.async {
                            userDefaults.set(data.prettyPrintedJSONString, forKey: "fact")
                        }
                    }
                }
                
                dataTask.resume()
            }, label: {
                Text("Make network request!")
            })

        }.onDisappear {
            FlipperClientKt.sharedClient().stop()
        }
        .padding()
    }
}

struct NativeView_Previews: PreviewProvider {
    static var previews: some View {
        NativeView()
    }
}
