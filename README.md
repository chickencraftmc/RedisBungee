# RedisBungee fork By Limework

*if you are here for transferring players to another proxy when the first proxy crashes or whatever this plugin won't do
it, tell mojang to implement transfer packet*.
[Click here, for more information about transfer packet](https://hypixel.net/threads/why-do-we-need-transfer-packets.1390307/)

The original project of RedisBungee is no longer maintained, so we have forked the plugin.
RedisBungee uses [Redis](https://redis.io) with Java client [Jedis](https://github.com/redis/jedis/)
to Synchronize players data between [BungeeCord](https://github.com/SpigotMC/BungeeCord)
or [Velocity*](https://github.com/PaperMC/Velocity) proxies

Velocity*: *version 3.1.2 or above is only supported, any version below that might work but might be
unstable* [#40](https://github.com/ProxioDev/RedisBungee/pull/40)

## Downloads

[![](https://raw.githubusercontent.com/Prospector/badges/master/modrinth-badge-72h-padded.png)](https://modrinth.com/plugin/redisbungee)

or from github releases

https://github.com/ProxioDev/RedisBungee/releases

## notes

If you are looking to use Original RedisBungee without a change to internals,
with critical bugs fixed, please use version [0.6.5](https://github.com/ProxioDev/RedisBungee/releases/tag/0.6.5) and
java docs For legacy Version [0.6.5](https://proxiodev.github.io/RedisBungee-JavaDocs/0.6.5-SNAPSHOT/)
as its last version before internal changes. please note that you will not get support for any old builds unless
critical bugs effecting both 0.6.5 and 0.7.0 or above.

SpigotMC resource page: [click](https://www.spigotmc.org/resources/redisbungee.87700/)

## Supported Redis versions

| Redis version | Supported |
|:-------------:|:---------:|
|     1.x.x     | &#x2716;	 |
|     2.x.x     | &#x2716;	 |
|     3.x.x     | &#x2714;	 |
|     4.x.x     | &#x2714;	 |
|     5.x.x     | &#x2714;	 |
|     6.x.x     | &#x2714;  |
|     7.x.x     | &#x2714;  |

## Implementing RedisBungee in your plugin: [![RedisBungee Build](https://github.com/proxiodev/RedisBungee/actions/workflows/maven.yml/badge.svg)](https://github.com/Limework/RedisBungee/actions/workflows/maven.yml) [![](https://jitpack.io/v/ProxioDev/redisbungee.svg)](https://jitpack.io/#ProxioDev/redisbungee)

RedisBungee is distributed as a [Gradle](https://gradle.org/) project.

By using jitpack [![](https://jitpack.io/v/ProxioDev/redisbungee.svg)](https://jitpack.io/#ProxioDev/redisbungee)

# Setup jitpack repository

## maven

```xml
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
```

## gradle (kotlin dsl)

```kotlin
repositories {
    maven("https://jitpack.io/")
}
```

# [BungeeCord](https://github.com/SpigotMC/BungeeCord)

add this in your project dependencies

## maven

```xml
	<dependency>
	    <groupId>com.github.proxiodev.redisbungee</groupId>
	    <artifactId>RedisBungee-Bungee</artifactId>
	    <version>VERSION</version>
	    <scope>provided</scope>
	    <!-- <classifier>all</classifier>  USE THIS IF YOU WANT TO USE INCLUDED JEDIS LIB BECAUSE OF RELOCATION -->
	</dependency>
	
```

## gradle (kotlin dsl)

```
implementation("com.github.ProxioDev.redisbungee:RedisBungee-Bungee:0.11.0")

// USE THIS IF YOU WANT TO USE INCLUDED JEDIS LIB BECAUSE OF RELOACTION AND REMOVE THE ABOVE STATEMENT
implementation("com.github.ProxioDev.redisbungee:RedisBungee-Bungee:0.11.0:all") {
        exclude("redis.clients", "jedis")
}
```

then in your project plugin.yml add `RedisBungee` to `depends` like this

```yaml
name: "yourplugin"
main: your.loaders.class
version: 1.0.0-SNAPSHOT
author: idk
depends: [ RedisBungee ]
```

## [Velocity](https://github.com/PaperMC/Velocity)

## maven

```xml
	<dependency>
	    <groupId>com.github.proxiodev.redisbungee</groupId>
	    <artifactId>RedisBungee-Velocity</artifactId>
	    <version>VERSION</version>
	    <scope>provided</scope>
	    <!-- <classifier>all</classifier>  USE THIS IF YOU WANT TO USE INCLUDED JEDIS LIB BECAUSE OF RELOCATION -->
	</dependency>
```

## gradle (kotlin dsl)

```
implementation("com.github.ProxioDev.redisbungee:RedisBungee-Velocity:0.11.0")

// USE THIS IF YOU WANT TO USE INCLUDED JEDIS LIB BECAUSE OF RELOACTION AND REMOVE THE ABOVE STATEMENT
implementation("com.github.ProxioDev.redisbungee:RedisBungee-Velocity:0.11.0:all") {
        exclude("redis.clients", "jedis")
}
```

then to make your plugin depends on RedisBungee, make sure your plugin class Annotation
have `@Dependency(id = "redisbungee")` like this

```java
@Plugin(
  id = "myplugin",
  name = "My Plugin",
  version = "0.1.0-beta",
  dependencies = {
    @Dependency(id = "redisbungee")
  }
)
public class PluginMainClass {

}
```

## Getting the latest commits to your code

If you want to use the latest commits without waiting for releases.
first, install it to your maven local repo

```bash
git clone https://github.com/ProxioDev/RedisBungee.git
cd RedisBungee
./gradlew publishToMavenLocal
```

then use any of these in your project.

```xml
<dependency>
        <groupId>com.imaginarycode.minecraft</groupId>
        <artifactId>RedisBungee-Bungee</artifactId>
        <version>VERSION</version>
        <scope>provided</scope>
	<!-- <classifier>all</classifier>  USE THIS IF YOU WANT TO USE INCLUDED JEDIS LIB BECAUSE OF RELOCATION -->
</dependency>
```

```xml
<dependency>
        <groupId>com.imaginarycode.minecraft</groupId>
        <artifactId>RedisBungee-Velocity</artifactId>
        <version>VERSION</version>
        <scope>provided</scope>
	<!-- <classifier>all</classifier>  USE THIS IF YOU WANT TO USE INCLUDED JEDIS LIB BECAUSE OF RELOCATION -->
</dependency>
```

## Javadocs

* API: https://ci.limework.net/RedisBungee/RedisBungee-API/build/docs/javadoc/
* Velocity: https://ci.limework.net/RedisBungee/RedisBungee-Velocity/build/docs/javadoc/
* Bungeecord: https://ci.limework.net/RedisBungee/RedisBungee-Bungee/build/docs/javadoc/

## Configuration

**REDISBUNGEE REQUIRES A REDIS SERVER**, preferably with reasonably low latency. The
default [config](https://github.com/ProxioDev/RedisBungee/blob/develop/RedisBungee-API/src/main/resources/config.yml) is
saved when the plugin first starts.

## compatibility with original RedisBungee in Bungeecord ecosystem

This fork ensures compatibility with old plugins, so it should work as drop replacement,
but since Api has been split from the platform there some changes that have to be done, so your plugin might not work
if:

* there is none at the moment, please report any findings at the issue page.

Cluster mode compatibility in version 0.8.0:

If you are using static legacy method `RedisBungee#getPool()` it might fail in:

* if Cluster mode is enabled, due fact its Uses different classes
* if JedisPool compatibility mode is disabled in the config due fact project internally switched to JedisPooled than
  Jedis

## Support

open an issue with question button

## License

This project is distributed under Eclipse Public License 1.0

You can find it [here](https://github.com/proxiodev/RedisBungee/blob/master/LICENSE)

You can find the original RedisBungee is by [astei](https://github.com/astei) and project can be
found [here](https://github.com/minecrafter/RedisBungee) or spigot
page [here, but its no longer available](https://www.spigotmc.org/resources/redisbungee.13494/)

## YourKit

YourKit supports open source projects with innovative and intelligent tools for monitoring and profiling Java and .NET
applications. YourKit is the creator
of [YourKit Java Profiler](https://www.yourkit.com/java/profiler/), [YourKit .NET Profiler](https://www.yourkit.com/.net/profiler/)
and [YourKit YouMonitor](https://www.yourkit.com/youmonitor/).

![YourKit](https://www.yourkit.com/images/yklogo.png)
