# This is a configuration file for ProGuard.
# http://proguard.sourceforge.net/index.html#manual/usage.html

-dontusemixedcaseclassnames
-verbose

# Preserve some boilerplate
-keep public class com.android.internal.R
-keep public class com.android.internal.R$*
-keep class **.R
-keep class **.R$*

# Keep Hilt
-keep class dagger.hilt.** { *; }
-keep class javax.inject.** { *; }

# Keep Firebase
-keep class com.google.firebase.** { *; }

# Keep Retrofit
-keep class retrofit2.** { *; }
-keep class com.google.gson.** { *; }

# Keep Coroutines
-keep class kotlinx.coroutines.** { *; }

# Keep exceptions
-keepattributes SourceFile,LineNumberTable
-renamesourcefileattribute SourceFile
