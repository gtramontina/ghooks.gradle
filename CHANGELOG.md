# [2.0.0](https://github.com/gtramontina/ghooks.gradle/compare/v1.2.1...v2.0.0) (2023-04-21)


### Bug Fixes

* make String.exec more resilient—handle arguments with spaces and across OS' ([01d632e](https://github.com/gtramontina/ghooks.gradle/commit/01d632e285e1ee29ca06d96c2383525ce21138a9))


### Features

* use "git config core.hooksPath" instead of symlinking ([396f0c0](https://github.com/gtramontina/ghooks.gradle/commit/396f0c07adaec34dce2edcd4a320146542acd812))


### BREAKING CHANGES

* this note is more to signify that this change can
have unexpected consequences on repositories that used any previous
version where GHooks used to use symbolic links. While I can't
think of anything that can go wrong with simply re-setting what the
local config for hooksPath is, this note is meant to be a trigger
for a major version change and to give a potential migration path:

- Remove the symlinked directory (and potentially re-create an
  empty directory: `rm -rf .git/hooks && mkdir .git/hooks`;
- Run GHooks' install task: `./gradlew installGitHooks`;

## [1.2.1](https://github.com/gtramontina/ghooks.gradle/compare/v1.2.0...v1.2.1) (2023-04-16)


### Bug Fixes

* remove forbidden gradle tags ('gradle', 'plugin') ([c2fdf34](https://github.com/gtramontina/ghooks.gradle/commit/c2fdf3412e35cbfb57140a3bf4ed4e153614cc48))

# [1.2.0](https://github.com/gtramontina/ghooks.gradle/compare/v1.1.1...v1.2.0) (2023-04-16)


### Features

* release dependency updates ([47b36fd](https://github.com/gtramontina/ghooks.gradle/commit/47b36fd60d2d4c833d7914031fe9bec5e634b83c))

## [1.1.1](https://github.com/gtramontina/ghooks.gradle/compare/v1.1.0...v1.1.1) (2019-08-06)


### Bug Fixes

* **dependabot:** gradle does not support live updates ([3f4601b](https://github.com/gtramontina/ghooks.gradle/commit/3f4601b))

# [1.1.0](https://github.com/gtramontina/ghooks.gradle/compare/v1.0.1...v1.1.0) (2019-06-02)


### Features

* ensure the symlink created is relative ([63d0fe3](https://github.com/gtramontina/ghooks.gradle/commit/63d0fe3))

## [1.0.1](https://github.com/gtramontina/ghooks.gradle/compare/v1.0.0...v1.0.1) (2019-06-02)


### Bug Fixes

* runtime error not finding FileUtils ([cea3acf](https://github.com/gtramontina/ghooks.gradle/commit/cea3acf))
