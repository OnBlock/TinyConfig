# TinyConfig
##### Easy configuration for the modder, and the end user.
-----
To include it in your project, add this to your build.gradle:
```groovy
repositories {
    maven { url 'http://server.bbkr.space:8081/artifactory/libs-release' } // Jankson
    maven { url 'https://jitpack.io' } // TinyConfig
}
dependencies {
    modImplementation "com.github.indi-code:TinyConfig:${project.tiny_config_version}"
    include "com.github.indi-code:TinyConfig:${project.tiny_config_version}"
}
```
Where `tiny_config_version` is the latest tag under the releases tab.
### Usage
-----
You can get a config file for your mod like this:
```java
ModConfig yourConfig = new ModConfig("modid"/*, optional jankson instance*/)
```
To use the config, in, for instance a load method, do this:
```java
yourConfig.configure(/*config filename under your modid as a directory, */config -> {
    // Do Stuff
});
```
But what if you want to change your config from your mod? Then
```java
yourConfig.configure(/*config filename under your modid as a directory, */true, config -> {
    // Do Stuff
});
```
What this does, is instead of loading from the file, it presents an empty JSON. If you saved all your original values, you can just use the defaulted get statements from `DefaultedJsonObject`.
You should probably add it as a booean into your config's `sync` method, like so:
```java
public void sync(boolean overwrite) {
    yourConfig.configure(overwrite, config -> {
        // Do Stuff
    }
}
```
### Now, how do I actually use it?
-----
You are probably wondering about those `// Do Stuff` comments. Well all will be revealed here.

##### Getters:
Getters are defaulted. If the config dosen't contain the field, the getter will be run to retrieve it, and it will be added to the JSON.
Here is the base getter:
```java
config.get(String key, Getter<JsonElement> defaultValueGetter, String comment)
```
Note that a comment is not required. On all of the getters, you can specify `null` for a comment, or omit the parameter entirely.
Value getters can be specified in two ways. A Lambda/Getter Instance, or a value(Just the `JsonElement`).
You should specify a getter lambda if your getter involves creating arrays, calculating values, etc. If its a static value, just enter the value.

The existing getters are as follows(comments can be omitted, and getters replaced with static values):
```java
JsonElement get(String key, Getter<? extends JsonElement> defaultValueGetter, String comment)
JsonArray getArray(String key, Getter<? extends JsonArray> defaultValueGetter, String comment)
<T extends Enum<T>> Enum<T>/*This means your enum type*/ getEnum(String key, Enum<T> defaultValue, String comment)
Number getNumber(String key, Getter<? extends Number> defaultValueGetter, String comment)
// Primitive types
String getString(String key, Getter<String> defaultValue, String comment)
boolean getBool(String key, Getter<Boolean> defaultValue, String comment)
double getDouble(String key, Getter<Double> defaultValue, String comment)
getLong(String key, Getter<Long> defaultValue, String comment)
```
##### Setters:
I really dont know why you'd want to force a config value to be a certain thing and ignore user input, but if you want to, you're covered here too.
Setters are basically the same as getters, but with a `void` returned, and no value getters.
```java
set(String key, JsonElement value, String comment)
```
The following setters are in place(again, comments can be omitted):
```java
set(String key, JsonElement value, String comment)
// Arrays can just be put directly into this one
// Primitives
setString(String key, String value, String comment)
setBool(String key, boolean value, String comment)
setInt(String key, int value, String comment)
setDouble(String key, double value, String comment)
setLong(String key, long value, String comment)
```
#### Accessing children
You might have thought, as you read, what do I do about children? Do I need to make some fancy getter/setter that dosen't replace original values?
The short answer: No.

You can access children using an easy method in `DefaultedJsonObject`:
```java
accessChild(String key, Consumer<DefaultedJsonObject> modifier, String comment)
```
It returns nothing, and again, the comment can be omitted.

What, may you ask do I do with the consumer?

It returns a `DefauledJsonObject`, which is the same thing as the base config, so you can use it exactly as you would the base config.

-----
I hope everything works for you, and if it dosen't, feel free to use the issue tracker, or ping/dm @GiantNuker#8194 on [the fabric discord](https://discord.gg/v6v4pMv).
