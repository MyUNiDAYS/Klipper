<h1 align="left">Klipper
<img alt="GitHub last commit" src="https://img.shields.io/github/last-commit/MyUNiDAYS/template-kotlin-library?style=flat-square"> <a href="https://git.live"><img src="https://img.shields.io/badge/collaborate-on%20gitlive-blueviolet?style=flat-square"></a>
</h1>

Kotlin + Flipper = Klipper

Kotlin Multiplatform Flipper Support

## Installation

### KMM

```
implementation("com.myunidays:package:0.0.1")
```

### Android

```
implementation("com.myunidays:package-android:0.0.1")
```

### iOS

Add to the binary to your swift package like this:

```swift
        .binaryTarget(
            name: "project",
            url: "https://github.com/MyUNiDAYS/klipper/releases/download/0.0.1/0.0.1.zip",
            checksum: "8c35293a410f4ec5d150c4f5464f6b5cf04a1a15d1ae9c29126bb0b7a7dc2a54"
        ),
```

Where 0.0.1 is the release number, you will also need to change the checksum, xcode will tell you the different checksum if its wrong and just update that from the error message.

## How to use

### KMM

```kotlin
    val flipperClient = FlipperClient.getInstance(context)
    flipperClient.start()
```

### Android

```kotlin
    val client = AndroidFlipperClient.getInstance(context)
    client.start()
```

### iOS

```swift
    let flipperClient = FlipperClientKt.sharedClient()
    flipperClient.start()
```

### Plugins

Out of the box we support the following plugins:

- Flipper Network Plugin
- Flipper UserDefaults Plugin

To use these plugins you will need to add them to your project like this:

#### KMM

```kotlin
    val flipperClient = FlipperClient.getInstance(context)
    flipperClient.addPlugin(NetworkFlipperPlugin())
    flipperClient.addPlugin(UserDefaultsFlipperPlugin())
    flipperClient.start()
```

#### Android

```kotlin
    val client = AndroidFlipperClient.getInstance(context)
    client.addPlugin(NetworkFlipperPlugin())
    client.addPlugin(UserDefaultsFlipperPlugin())
    client.start()
```

#### iOS

```swift
    let flipperClient = FlipperClientKt.sharedClient()
    flipperClient.addPlugin(NetworkFlipperPluginKt.networkFlipperPlugin())
    flipperClient.addPlugin(UserDefaultsFlipperPluginKt.userDefaultsFlipperPlugin())
    flipperClient.start()
```

## Examples

Please find examples in the [examples](./examples) folder.

## Future

We would like to write our own implementation of the message bus, this way we could then inject the url of the flipper host.

## Contributing

This project is set up as an open source project. As such, if there are any suggestions that you have for features, for improving the code itself, or you have come across any problems; you can raise them and/or suggest changes in implementation.

If you are interested in contributing to this codebase, please follow the contributing guidelines. This contains guides on both contributing directly and raising feature requests or bug reports. Please adhere to our code of conduct when doing any of the above.
