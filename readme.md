## Setup
Simplest is to import the project to android studio.
To run through command line jdk path should be available in env for gradle to work.
 
## Languages, libraries and tools used
* Android Support Libraries
* [Kotlin](https://kotlinlang.org/)
* [RxJava3](https://github.com/ReactiveX/RxJava)
* [Dagger](https://github.com/google/dagger)
* [Gson](https://github.com/google/gson)
* [Mockito](http://site.mockito.org/)
* [JUnit](https://junit.org/junit4/)
* [Room](https://developer.android.com/jetpack/androidx/releases/room)

## System Requirements
* JDK 1.8
* [Android SDK](https://developer.android.com/studio/index.html)
* Android O ([API 30](https://developer.android.com/studio/releases/platforms))
* Latest Android SDK Tools and build tools.

### Presentation
This layer is to handled presentation of user interface. Each Presenter extends PresenterViewRefHolder<MVPView>(), and presenter's Interface

### Domain
The domain layer contains Use Cases to retrieve data from the Data layer and pass it onto the Presentation layer. 

### Data
The data layer deals with creating data source in this case Asset File and Room Database.
Implements repositories defined in domain.

### buildSrc
This introduces gradle dsl plugin and maintains libraries and their versions.

### Unit Tests
Package test.interview has sub directories which contain all unit tests for the project.