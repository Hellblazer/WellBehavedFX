WellBehavedFX
=============

This project tries to help with two (related) aspects of JavaFX development:

 * defining and overriding event handlers (e.g. keyboard shortcuts); and
 * implementing skins for JavaFX controls according to the MVC pattern.


Builder Pattern for Event Handlers
-----------------------------------

WellBehavedFX provides builder-style API to define `EventHandler`s.

```java
import static javafx.scene.input.KeyCode.*;
import static javafx.scene.input.KeyCombination.*;
import static org.fxmisc.wellbehaved.input.EventPattern.*;

EventHandler<? super KeyEvent> keyPressedHandler = EventHandlerHelper
        .on(keyPressed(O, CONTROL_DOWN))            .act(event -> open())
        .on(keyPressed(S, CONTROL_DOWN))            .act(event -> save())
        .on(keyPressed(S, CONTROL_DOWN, SHIFT_DOWN)).act(event -> saveAll())
        .create();

textArea.setOnKeyPressed(keyPressedHandler);
```


(De)composable Event Handlers
-----------------------------

In the example above we have overridden `textArea`'s original `onKeyPressed` handler (if any). We may instead want to combine the new handler and the original handler, so that if the new handler does not handle (that is, does not _consume_) the event, the original handler is invoked. This is what we should have done instead:

```java
EventHandlerHelper.install(textArea.onKeyPressedProperty(), keyPressedHandler);
```

We can add another one that will take precedence over both previously installed handlers:

```java
EventHandler<? super KeyEvent> anotherHandler = ...;
EventHandlerHelper.install(textArea.onKeyPressedProperty(), anotherHandler);
```

We can as well exclude a sub-handler from a composite handler:

```java
EventHandlerHelper.remove(textArea.onKeyPressedProperty(), keyPressedHandler);
```

Now the handler that remains installed on `textArea` first invokes `anotherHandler`, and if it does not consume the event, `textArea`s original handler (if any) is invoked.


Event Handler Templates
-----------------------

TBD


Skin Scaffolding
----------------

TBD


Download
--------

Maven artifacts are deployed to Sonatype OSS snapshot repository with the given Maven coordinates:

| Group ID               | Artifact ID    | Version      |
| :--------------------: | :------------: | :----------: |
| org.fxmisc.wellbehaved | wellbehavedfx  | 0.1-SNAPSHOT |

### Gradle example

```groovy
repositories {
    maven {
        url 'https://oss.sonatype.org/content/repositories/snapshots/' 
    }
}

dependencies {
    compile group: 'org.fxmisc.wellbehaved', name: 'wellbehavedfx', version: '0.1-SNAPSHOT'
}
```

### Sbt example

```scala
resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

libraryDependencies += "org.fxmisc.wellbehaved" % "wellbehavedfx" % "0.1-SNAPSHOT"
```

### Manual download

[Download](https://oss.sonatype.org/content/repositories/snapshots/org/fxmisc/wellbehaved/wellbehavedfx/0.1-SNAPSHOT/) the latest JAR file and place it on your classpath.


License
-------

[BSD 2-Clause License](http://opensource.org/licenses/BSD-2-Clause)
