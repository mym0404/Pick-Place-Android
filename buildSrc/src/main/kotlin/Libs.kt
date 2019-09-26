import kotlin.String

/**
 * Generated by https://github.com/jmfayard/buildSrcVersions
 *
 * Update this file with
 *   `$ ./gradlew buildSrcVersions`
 */
object Libs {
  /**
   * https://developer.android.com/jetpack/androidx
   */
  const val appcompat: String = "androidx.appcompat:appcompat:" + Versions.appcompat

  /**
   * http://tools.android.com
   */
  const val constraintlayout: String = "androidx.constraintlayout:constraintlayout:" +
      Versions.constraintlayout

  /**
   * http://developer.android.com/tools/extras/support-library.html
   */
  const val core_ktx: String = "androidx.core:core-ktx:" + Versions.core_ktx

  const val databinding_adapters: String = "androidx.databinding:databinding-adapters:" +
      Versions.androidx_databinding

  /**
   * https://developer.android.com/studio
   */
  const val databinding_common: String = "androidx.databinding:databinding-common:" +
      Versions.androidx_databinding

  /**
   * https://developer.android.com/studio
   */
  const val databinding_compiler: String = "androidx.databinding:databinding-compiler:" +
      Versions.androidx_databinding

  const val databinding_runtime: String = "androidx.databinding:databinding-runtime:" +
      Versions.androidx_databinding

  /**
   * http://developer.android.com/tools/extras/support-library.html
   */
  const val legacy_support_v4: String = "androidx.legacy:legacy-support-v4:" +
      Versions.legacy_support_v4

  /**
   * https://developer.android.com/topic/libraries/architecture/index.html
   */
  const val lifecycle_compiler: String = "androidx.lifecycle:lifecycle-compiler:" +
      Versions.androidx_lifecycle

  /**
   * https://developer.android.com/topic/libraries/architecture/index.html
   */
  const val lifecycle_livedata: String = "androidx.lifecycle:lifecycle-livedata:" +
      Versions.androidx_lifecycle

  /**
   * http://developer.android.com/tools/extras/support-library.html
   */
  const val lifecycle_viewmodel_ktx: String = "androidx.lifecycle:lifecycle-viewmodel-ktx:" +
      Versions.androidx_lifecycle

  /**
   * https://developer.android.com/topic/libraries/architecture/index.html
   */
  const val room_compiler: String = "androidx.room:room-compiler:" + Versions.androidx_room

  /**
   * https://developer.android.com/topic/libraries/architecture/index.html
   */
  const val room_ktx: String = "androidx.room:room-ktx:" + Versions.androidx_room

  /**
   * https://developer.android.com/topic/libraries/architecture/index.html
   */
  const val room_runtime: String = "androidx.room:room-runtime:" + Versions.androidx_room

  /**
   * https://developer.android.com/testing
   */
  const val espresso_contrib: String = "androidx.test.espresso:espresso-contrib:" +
      Versions.androidx_test_espresso

  /**
   * https://developer.android.com/testing
   */
  const val espresso_core: String = "androidx.test.espresso:espresso-core:" +
      Versions.androidx_test_espresso

  /**
   * https://developer.android.com/testing
   */
  const val androidx_test_ext_junit: String = "androidx.test.ext:junit:" +
      Versions.androidx_test_ext_junit

  /**
   * https://developer.android.com/testing
   */
  const val androidx_test_ext_truth: String = "androidx.test.ext:truth:" +
      Versions.androidx_test_ext_truth

  /**
   * https://developer.android.com/testing
   */
  const val androidx_test_core: String = "androidx.test:core:" + Versions.androidx_test

  /**
   * https://developer.android.com/testing
   */
  const val androidx_test_rules: String = "androidx.test:rules:" + Versions.androidx_test

  /**
   * https://developer.android.com/testing
   */
  const val androidx_test_runner: String = "androidx.test:runner:" + Versions.androidx_test

  const val viewpager2: String = "androidx.viewpager2:viewpager2:" + Versions.viewpager2

  const val multidex: String = "com.android.support:multidex:" + Versions.multidex

  /**
   * https://developer.android.com/studio
   */
  const val aapt2: String = "com.android.tools.build:aapt2:" + Versions.aapt2

  /**
   * https://developer.android.com/studio
   */
  const val com_android_tools_build_gradle: String = "com.android.tools.build:gradle:" +
      Versions.com_android_tools_build_gradle

  /**
   * https://developer.android.com/studio
   */
  const val lint_gradle: String = "com.android.tools.lint:lint-gradle:" + Versions.lint_gradle

  /**
   * https://github.com/bumptech/glide
   */
  const val com_github_bumptech_glide_compiler: String = "com.github.bumptech.glide:compiler:" +
      Versions.com_github_bumptech_glide

  /**
   * https://github.com/bumptech/glide
   */
  const val glide: String = "com.github.bumptech.glide:glide:" + Versions.com_github_bumptech_glide

  const val play_services_maps: String = "com.google.android.gms:play-services-maps:" +
      Versions.play_services_maps

  const val places: String = "com.google.android.libraries.places:places:" + Versions.places

  /**
   * http://developer.android.com/tools/extras/support-library.html
   */
  const val material: String = "com.google.android.material:material:" + Versions.material

  const val firebase_analytics: String = "com.google.firebase:firebase-analytics:" +
      Versions.firebase_analytics

  const val firebase_dynamic_links: String = "com.google.firebase:firebase-dynamic-links:" +
      Versions.firebase_dynamic_links

  const val google_services: String = "com.google.gms:google-services:" + Versions.google_services

  /**
   * https://github.com/googlemaps/android-maps-utils
   */
  const val android_maps_utils: String = "com.google.maps.android:android-maps-utils:" +
      Versions.android_maps_utils

  /**
   * http://github.com/google/truth
   */
  const val com_google_truth_truth: String = "com.google.truth:truth:" +
      Versions.com_google_truth_truth

  /**
   * https://github.com/karumi/Dexter
   */
  const val dexter: String = "com.karumi:dexter:" + Versions.dexter

  /**
   * https://github.com/romandanylyk/PageIndicatorView
   */
  const val pageindicatorview: String = "com.romandanylyk:pageindicatorview:" +
      Versions.pageindicatorview

  /**
   * https://github.com/square/okhttp
   */
  const val logging_interceptor: String = "com.squareup.okhttp3:logging-interceptor:" +
      Versions.logging_interceptor

  /**
   * https://github.com/square/retrofit/
   */
  const val converter_gson: String = "com.squareup.retrofit2:converter-gson:" +
      Versions.com_squareup_retrofit2

  /**
   * https://github.com/square/retrofit/
   */
  const val converter_scalars: String = "com.squareup.retrofit2:converter-scalars:" +
      Versions.com_squareup_retrofit2

  /**
   * https://github.com/square/retrofit/
   */
  const val retrofit_mock: String = "com.squareup.retrofit2:retrofit-mock:" +
      Versions.com_squareup_retrofit2

  /**
   * https://github.com/square/retrofit/
   */
  const val retrofit: String = "com.squareup.retrofit2:retrofit:" + Versions.com_squareup_retrofit2

  const val de_fayard_buildsrcversions_gradle_plugin: String =
      "de.fayard.buildSrcVersions:de.fayard.buildSrcVersions.gradle.plugin:" +
      Versions.de_fayard_buildsrcversions_gradle_plugin

  /**
   * https://github.com/hdodenhof/CircleImageView
   */
  const val circleimageview: String = "de.hdodenhof:circleimageview:" + Versions.circleimageview

  /**
   * http://junit.org
   */
  const val junit_junit: String = "junit:junit:" + Versions.junit_junit

  /**
   * https://github.com/grantland/android-autofittextview
   */
  const val autofittextview: String = "me.grantland:autofittextview:" + Versions.autofittextview

  const val numbermarbleview: String = "mjstudio:numbermarbleview:" + Versions.numbermarbleview

  /**
   * https://kotlinlang.org/
   */
  const val kotlin_android_extensions_runtime: String =
      "org.jetbrains.kotlin:kotlin-android-extensions-runtime:" + Versions.org_jetbrains_kotlin

  /**
   * https://kotlinlang.org/
   */
  const val kotlin_android_extensions: String = "org.jetbrains.kotlin:kotlin-android-extensions:" +
      Versions.org_jetbrains_kotlin

  /**
   * https://kotlinlang.org/
   */
  const val kotlin_annotation_processing_gradle: String =
      "org.jetbrains.kotlin:kotlin-annotation-processing-gradle:" + Versions.org_jetbrains_kotlin

  /**
   * https://kotlinlang.org/
   */
  const val kotlin_gradle_plugin: String = "org.jetbrains.kotlin:kotlin-gradle-plugin:" +
      Versions.org_jetbrains_kotlin

  /**
   * https://kotlinlang.org/
   */
  const val kotlin_reflect: String = "org.jetbrains.kotlin:kotlin-reflect:" +
      Versions.org_jetbrains_kotlin

  /**
   * https://kotlinlang.org/
   */
  const val kotlin_stdlib_jdk7: String = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:" +
      Versions.org_jetbrains_kotlin

  /**
   * https://kotlinlang.org/
   */
  const val kotlin_stdlib: String = "org.jetbrains.kotlin:kotlin-stdlib:" +
      Versions.org_jetbrains_kotlin

  const val koin_androidx_ext: String = "org.koin:koin-androidx-ext:" + Versions.org_koin

  const val koin_androidx_scope: String = "org.koin:koin-androidx-scope:" + Versions.org_koin

  const val koin_androidx_viewmodel: String = "org.koin:koin-androidx-viewmodel:" +
      Versions.org_koin

  /**
   * http://robolectric.org
   */
  const val robolectric: String = "org.robolectric:robolectric:" + Versions.robolectric
}
