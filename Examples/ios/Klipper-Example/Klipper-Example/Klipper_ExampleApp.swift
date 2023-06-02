//
//  Klipper_ExampleApp.swift
//  Klipper-Example
//
//  Created by Andrew Reed on 22/05/2023.
//

import SwiftUI

@main
struct Klipper_ExampleApp: App {
    @UIApplicationDelegateAdaptor(AppDelegate.self) var appDelegate
    var body: some Scene {
        WindowGroup {
            MainView()
        }
    }
}
