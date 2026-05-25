pluginManagement {
    apply(from = "config.gradle")
    repositories {
        val addRepos: groovy.lang.Closure<Any> by extra
        addRepos.call(this)
        maven { url = uri("https://storage.googleapis.com/download.flutter.io") }
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

dependencyResolutionManagement {
    apply(from = "config.gradle")
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        val addRepos: groovy.lang.Closure<Any> by extra
        addRepos.call(this)
        // 添加 Flutter 官方仓库以获取 Flutter Engine 依赖
        maven { url = uri("https://storage.googleapis.com/download.flutter.io") }
    }
}

rootProject.name = "rv_framwork"
include(":app")
