# Mad App Modules
Mad App modules are common components used in mad apps. These are unpolished useful components made 
to build common ui for all Compose sample apps.  

> Note: These modules are required to build and contribute for mad apps 
> (till they are polished enough to be merged into madifiers.)

# Components
- Animated Scaffold
- Interactive Button
- Themed Layouts

# Build
- Run the cmd in root repo
```groovy
./gradlew publishReleasePublicationToMavenLocal
```

- to verify the installation locate the generated `pom` at the location.
> Windows: C:\Users\<User_Name>\.m2

> Linux: /home/<User_Name>/.m2

> Mac: /Users/<user_name>/.m2

# How to use
- Once location is verified add the following line to `settings.gradle.kts` and sync.
```kotlin
dependencyResolutionManagement {
    ...
    repositories {
        ...
        mavenLocal()
    }
}
```

- add the dependency
```kotlin
implementation("pro.jayeshseth.madappmodules:commonModules:0.0.8")
```