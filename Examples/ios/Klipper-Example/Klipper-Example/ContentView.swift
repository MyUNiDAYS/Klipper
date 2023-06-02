//
//  ContentView.swift
//  Klipper-Example
//
//  Created by Andrew Reed on 22/05/2023.
//

import SwiftUI
import Foundation

struct Fact : Codable {
    let fact: String
    let length: Int
}

struct ContentView: View {
    
    let userDefaults: UserDefaults = UserDefaults.init(suiteName: "klipper_example")!
    
    var body: some View {
        VStack {
            Image(systemName: "globe")
                .imageScale(.large)
                .foregroundColor(.accentColor)
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
                            do {
                                let decodedFact = try JSONDecoder().decode(Fact.self, from: data)
                                userDefaults.set(data.prettyPrintedJSONString, forKey: "fact")
                            } catch let error {
                                print("Error decoding: ", error)
                            }
                        }
                    }
                }
                
                dataTask.resume()
            }, label: {
                Text("Make network request!")
            })

        }
        .padding()
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
