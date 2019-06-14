<h1 align="center">
  <img src="https://user-images.githubusercontent.com/374635/58805639-d1b3a980-8657-11e9-95e0-b5cc4142c064.png" width="100"><br>
  ghooks<br>
  <sup><sub><sup><sub>SIMPLE GIT HOOKS</sub></sup></sub></sup>
</h1>

<p align="center">
  <a href="https://travis-ci.org/gtramontina/ghooks.gradle" title="Master build status"><img alt="Travis (.org)" src="https://img.shields.io/travis/gtramontina/ghooks.gradle.svg?logo=Travis&style=flat-square"></a> <a href="https://plugins.gradle.org/plugin/com.gtramontina.ghooks.gradle" title="Gradle Plugin Portal"><img alt="Maven metadata URL" src="https://img.shields.io/maven-metadata/v/https/plugins.gradle.org/m2/com/gtramontina/ghooks/gradle/com.gtramontina.ghooks.gradle.gradle.plugin/maven-metadata.xml.svg?color=%23007396&label=Gradle%20Plugin&logo=java&style=flat-square"></a> <a href="https://github.com/gtramontina/ghooks.gradle/blob/master/LICENSE" title="License"><img alt="GitHub" src="https://img.shields.io/github/license/gtramontina/ghooks.gradle.svg?logo=GitHub&style=flat-square"></a> <a href="http://commitizen.github.io/cz-cli/" title="Commitizen Friendly"><img alt="Commitizen Friendly" src="https://img.shields.io/badge/commitizen-friendly-brightgreen.svg?style=flat-square"></a> <a href="https://github.com/semantic-release/semantic-release" title="Semantic Release"><img alt="semantic-release" src="https://img.shields.io/badge/%20%20%F0%9F%93%A6%F0%9F%9A%80-semantic--release-e10079.svg?style=flat-square"></a>
</p>

## What <img src="https://png.icons8.com/wired/96/000000/help.png" align="right" width="24">

Share and version-control all your git hooks. This plugin is a Gradle version of [ghooks for Node.js](https://github.com/ghooks-org/ghooks).

## Installing <img src="https://png.icons8.com/wired/96/000000/maintenance.png" align="right" width="24">

Add the following entry to your `build.gradle` on the `plugins` section.

```groovy
plugins {
    id "com.gtramontina.ghooks.gradle" version "1.1.0"
}
```
<p align="right"><sup><code>build.gradle</code></sup></p>

Next, create a `.githooks/` directory on the root of your git project. This is where you'll keep your git hooks:

```shell
mkdir .githooks/
```

You may now execute any `gradle` command. The plugin will ensure it has everything it needs in order to get your git hooks working.

## Contributing <img src="https://png.icons8.com/wired/96/000000/laptop.png" align="right" width="24">

Contributions of any kind are very welcome! 🙏

## References <img src="https://png.icons8.com/wired/96/000000/moleskine.png" align="right" width="24">

* Icons by [Icons8](https://icons8.com).
* Inspired by [ghooks](https://github.com/ghooks-org/ghooks) for Node.js and [ghooks](https://github.com/gtramontina/ghooks.cr) for Crystal.
