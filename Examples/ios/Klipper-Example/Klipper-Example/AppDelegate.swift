//
//  AppDelegate.swift
//  Klipper-Example
//
//  Created by Andrew Reed on 22/05/2023.
//

import Foundation
import UIKit
import klipper
import FlipperKit

class AppDelegate: NSObject, UIApplicationDelegate {
    func application(
        _ application: UIApplication,
        didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil
    ) -> Bool {
        let flipperClient = FlipperClientKt.sharedClient()
        flipperClient.start()
        let network = NetworkFlipperPluginKt.doInitWithNetworkAdapter(networkAdapter: SKNetworkAdapterKt.create())
        flipperClient.addPlugin(plugin: network as! FlipperPlugin)
        let userDefaults = FKUserDefaultsPluginKt.doInitWithName(name: "your_suitename")
        flipperClient.addPlugin(plugin: userDefaults as! FlipperPlugin)
        
        return true
    }
}
