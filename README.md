[![Code Grade](https://api.codiga.io/project/33657/score/svg)](#)
[![GitHub license](https://img.shields.io/github/license/Chubbyduck1/HologramBridge.svg)](https://github.com/Chubbyduck1/HologramBridge/blob/master/LICENSE)
[![](https://jitpack.io/v/Chubbyduck1/HologramBridge.svg)](https://jitpack.io/#Chubbyduck1/HologramBridge)<br>
[![Support Server](https://img.shields.io/discord/609145954926460928.svg?label=Discord&logo=Discord&colorB=7289da&style=for-the-badge)](https://discord.gg/y4xc5tNrbQ)

# Hologram Bridge<br>

A bridge between hologram plugins created by **Chubbyduck1**. The API has been designed to have similar methods to
HolographicDisplays, while supporting different plugins.

# Quick Links<br>

* Supported Plugins: [Click Here](#supported-plugins)
* Warning: [Click Here](#warning)
* Limitations: [Click Here](#limitations)
* Using the API: [Click Here](#using-the-api)
* Download: [Click Here](#download)
* Licensing: [Click Here](#license)

# Supported Plugins<br>

* [HolographicDisplays (2.x - 3.x)](https://dev.bukkit.org/projects/holographic-displays)
* [CMI](https://www.spigotmc.org/resources/cmi-298-commands-insane-kits-portals-essentials-economy-mysql-sqlite-much-more.3742/)
* [Holograms](https://www.spigotmc.org/resources/holograms.4924/)
* [Decent Holograms](https://www.spigotmc.org/resources/decent-holograms-1-8-1-18-2-papi-support-no-dependencies.96927/)

# Warning<br>

Some features of this bridge can be unstable. It is being worked on to be more stable. If you find any bugs, please
contact me.

# Limitations<br>

* If you find a limitation, or issue, please let us know in the discord.

# Using The API<br>

You can either run HologramBridge as a plugin, or shade it into your plugin.

If you're shading it into your plugin, use the following to start it.

```java
new HologramBridge(final JavaPlugin javaPlugin,final boolean verbose);
```

### Creating A Hologram<br>

```java
final Hologram hologram=HologramAPI.createHologram(Location);
```

### Adding Lines<br>

```java
final Hologram hologram=HologramAPI.createHologram(Location);
final ItemLine itemLine=hologram.appendItemLine(new ItemStack(Material.DIAMOND,1));
final TextLine textLine=hologram.appendTextLine("Hello!");
```

# Download</br>

Latest Release: [GitHub Release](https://github.com/Chubbyduck1/HologramBridge/releases/latest)
Latest Version:
[![GitHub release](https://img.shields.io/github/release/Chubbyduck1/HologramBridge.svg)](https://GitHub.com/Chubbyduck1/HologramBridge/releases/)

Be sure to replace the **VERSION** key below with the version shown above!

**Gradle**

```groovy
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

```groovy
dependencies {
    implementation 'com.github.Chubbyduck1:HologramBridge:VERSION'
}
```

**Maven**

```xml

<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>
```

```xml

<dependency>
    <groupId>com.github.chubbyduck1</groupId>
    <artifactId>HologramBridge</artifactId>
    <version>VERSION</version>
</dependency>
```

# License<br>

This is licensed under MIT Licensing
