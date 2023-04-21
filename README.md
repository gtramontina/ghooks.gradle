<h1 align="center">
  <img src="https://user-images.githubusercontent.com/374635/58805639-d1b3a980-8657-11e9-95e0-b5cc4142c064.png" width="100"><br>
  ghooks<br>
  <sup><sub><sup><sub>SIMPLE GIT HOOKS</sub></sup></sub></sup>
</h1>

<p align="center">
  <a href="https://github.com/gtramontina/ghooks.gradle/actions/workflows/pipeline.yml" title="Pipeline"><img alt="GitHub Workflow Status" src="https://img.shields.io/github/actions/workflow/status/gtramontina/ghooks.gradle/pipeline.yml?label=Build&logo=github&style=flat-square"></a> <a href="https://plugins.gradle.org/plugin/com.gtramontina.ghooks.gradle" title="Gradle Plugin Portal"><img alt="Maven metadata URL" src="https://img.shields.io/maven-metadata/v/https/plugins.gradle.org/m2/com/gtramontina/ghooks/gradle/com.gtramontina.ghooks.gradle.gradle.plugin/maven-metadata.xml.svg?color=%23007396&label=Version&logo=gradle&style=flat-square"></a> <a href="https://github.com/gtramontina/ghooks.gradle/blob/master/LICENSE" title="License"><img alt="GitHub" src="https://img.shields.io/github/license/gtramontina/ghooks.gradle.svg?logo=GitHub&style=flat-square"></a> <a href="http://commitizen.github.io/cz-cli/" title="Commitizen Friendly"><img alt="Commitizen Friendly" src="https://img.shields.io/badge/Commitizen-friendly-brightgreen.svg?logo=conventionalcommits&style=flat-square"></a> <a href="https://github.com/semantic-release/semantic-release" title="Semantic Release"><img alt="semantic-release" src="https://img.shields.io/badge/Semantic-release-e10079.svg?logo=semanticrelease&style=flat-square"></a>
</p>

## What <img src="https://img.icons8.com/clouds/344/help.png" align="right" width="24">

Share and version-control all your git hooks. This plugin is a Gradle version of [ghooks for Node.js](https://github.com/ghooks-org/ghooks).

## Installing <img src="https://img.icons8.com/clouds/344/maintenance.png" align="right" width="24">

Add the following entry to your `build.gradle` on the `plugins` section.

```groovy
plugins {
    id "com.gtramontina.ghooks.gradle" version "2.0.0"
}
```
<p align="right"><sup><code>build.gradle</code></sup></p>

Next, create a `.githooks/` directory on the root of your git project. This is where you'll keep your git hooks:

```shell
mkdir .githooks/
```

You may now execute any `gradle` command. The plugin will ensure it has everything it needs in order to get your git hooks working.

With additions to your build file, you can even use this plugin if your hooks are in a dependency and shared amongst multiple projects:

```groovy
task addHooksFromJar {
    def hookDir = new File(projectDir, ".githooks")

    if(!hookDir.exists()) {
        hookDir.mkdir()
    }

    configurations.compile.each { jar ->
        if (jar.name.contains("hooks")) {
            copy {
                from(zipTree(jar))
                into(hookDir)
            }
        }
    }
}

installGitHooks.dependsOn("addHooksFromJar")
```

## Contributing <img src="https://img.icons8.com/clouds/344/laptop.png" align="right" width="24">

Contributions of any kind are very welcome! 🙏

## References <img src="https://img.icons8.com/clouds/344/moleskine-1.png" align="right" width="24">

* Icons by [Icons8](https://icons8.com).
* Inspired by [ghooks](https://github.com/ghooks-org/ghooks) for Node.js and [ghooks](https://github.com/gtramontina/ghooks.cr) for Crystal.
