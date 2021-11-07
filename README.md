[![Code Grade](https://www.code-inspector.com/project/29640/status/svg)](https://www.code-inspector.com/public/project/136/mygithubproject/dashboard)
[![GitHub license](https://img.shields.io/github/license/StormTechnology/HologramBridge.svg)](https://github.com/StormTechnology/HologramBridge/blob/master/LICENSE)
[![](https://jitpack.io/v/StormTechnology/HologramBridge.svg)](https://jitpack.io/#StormTechnology/HologramBridge)<br>
[![Support Server](https://img.shields.io/discord/609145954926460928.svg?label=Discord&logo=Discord&colorB=7289da&style=for-the-badge)](https://discord.gg/y4xc5tNrbQ)

# Hologram Bridge<br>
A bridge between hologram plugins created by **Chubbyduck1**. The API has been designed to have similar methods to HolographicDisplays.

# Quick Links<br>
* Supported Plugins: [Click Here](#supported-plugins)
* Warning: [Click Here](#warning)
* Limitations: [Click Here](#limitations)
* Using the API: [Click Here](#using-the-api)
* Download: [Click Here](#download)
* Licensing: [Click Here](#license)
* TODO: [Click Here](https://github.com/StormTechnology/HologramBridge/blob/master/TODO.md)

# Supported Plugins<br>
* [HolographicDisplays](https://dev.bukkit.org/projects/holographic-displays)
* [CMI](https://www.spigotmc.org/resources/cmi-298-commands-insane-kits-portals-essentials-economy-mysql-sqlite-much-more.3742/)
* [Holograms](https://www.spigotmc.org/resources/holograms.4924/)

# Warning<br>
Some features of this bridge can be unstable. It is being worked on to be more stable.

# Limitations<br>
* Player Visibility not usable on HolographicDisplays

# Using The API<br>
You can either run HologramBridge as a plugin, or shade it into your plugin.

If you're shading it into your plugin, use the following to start it.
```java
new HologramBridge(JavaPlugin javaPlugin, boolean verbose);
```

### Creating A Hologram<br>
```java
final Hologram hologram = HologramAPI.createHologram(Location);
```

### Adding Lines<br>
```java
final Hologram hologram = HologramAPI.createHologram(Location);
final ItemLine itemLine = hologram.appendItemLine(new ItemStack(Material.DIAMOND, 1));
final TextLine textLine = hologram.appendTextLine("Hello!");
```

# Download</br>
Latest Release: [GitHub Release](https://github.com/DV8FromTheWorld/JDA/releases/latest)
Latest Version:
[![GitHub release](https://img.shields.io/github/release/StormTechnology/HologramBridge.svg)](https://GitHub.com/StormTechnology/HologramBridge/releases/)


Be sure to replace the **VERSION** key below with the version shown above!

**Maven**
```xml
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>
```
```xml
<dependency>
    <groupId>com.github.StormTechnology</groupId>
    <artifactId>HologramBridge</artifactId>
    <version>VERSION</version>
</dependency>
```

# License<br>
This is licensed under MIT Licensing