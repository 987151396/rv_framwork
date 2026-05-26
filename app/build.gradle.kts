plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

// 尝试使用更直接的 extra 访问方式
val androidConfigs = rootProject.extra["android"] as Map<*, *>
val compileSdkVersionParam = androidConfigs["compileSdkVersion"].toString().toInt()
val minSdkVersionParam = androidConfigs["minSdkVersion"].toString().toInt()
val targetSdkVersionParam = androidConfigs["targetSdkVersion"].toString().toInt()

android {
    namespace = "com.rv.framework"
    compileSdk = compileSdkVersionParam

    sourceSets {
        getByName("main") {
            java.srcDirs("src/main/java", "src/main/kotlin")
        }
    }

    defaultConfig {
        minSdk = minSdkVersionParam
        targetSdk = targetSdkVersionParam

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(files("libs/autolib.jar"))
    // 引入 Flutter Engine 依赖，支持 io.flutter 相关类导入
    // 使用 IDE 推荐的可用版本
    compileOnly(libs.flutter.embedding.debug)
    implementation(libs.vioserial)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)
    
    // ViewModel & LiveData 支持
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
}
