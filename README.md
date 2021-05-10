# LettuceWrap-Standalone

LettuceWrap is a lightweight Bukkit plugin designed to make utilizing PubSub messaging from lettuce.io clean and easy. For more information, see [lettuce.io](http://lettuce.io). This libraru is only designed to subscribe (listen) to channels and publish messages to channels. Patterns and listening for subscribing/unsubscribing is NOT supported. This library may be expanded in the future to accommodate more redis features.

&#9888;&nbsp;*Note: This dependency must be shaded into your plugin to work correctly! Unlike the original LettuceWrap plugin, this is not a Bukkit plugin and will not work by itself.*

## Using LettuceWrap-Standalone as a Dependency

&#9888;&nbsp;*Note: This dependency must be shaded into your plugin to work correctly! This is not a standalone plugin. See the getting started page for information on how to shade this into your plugin.*

### Maven

Add the following repository:
```
<repository>
    <id>magicmq-repo</id>
    <url>https://repo.magicmq.dev/repository/maven-releases/</url>
</repository>
```
Then, add the following dependency:
```
<dependency>
    <groupId>dev.magicmq</groupId>
    <artifactId>lettucewrap-standalone</artifactId>
    <version>{VERSION}</version>
</dependency>
```
Replace `{VERSION}` with the version shown above.

### Gradle

Add the following repository:
```
repositories {
    ...
    magicmq-repo { url 'https://repo.magicmq.dev/repository/maven-releases/' }
}
```
Then, add the following dependency:
```
dependencies {
    ...
    compile 'dev.magicmq.lettucewrap-standalone:{VERSION}'
}
```
Replace `{VERSION}` with the version shown above.

### Manual Usage

Releases are also published on GitHub [here](https://github.com/magicmq/LettuceWrap-Standalone/releases). You may download the JAR and import it yourself into your IDE of choice, or you may install it into your local repository.

## Building

Building requires [Maven](https://maven.apache.org/) and [Git](https://git-scm.com/). Maven 3+ is recommended for building the project. Follow these steps:

1. Clone the repository: `git clone https://github.com/magicmq/LettuceWrap-Standalone.git`
2. Enter the repository root: `cd LettuceWrap-Standalone`
3. Build with Maven: `mvn clean package`
4. Built files will be located in the `target` directory.

## Issues/Suggestions

Do you have any issues or suggestions? [Submit an issue report.](https://github.com/magicmq/LettuceWrap-Standalone/issues/new)
