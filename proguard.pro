-keep,allowshrinking,allowoptimization class com.android.launcher3.** {
  *;
}

# The support library contains references to newer platform versions.
# Don't warn about those in case this app is linking against an older
# platform version.  We know about them, and they are safe.
-dontwarn android.support.**

# Proguard will strip methods required for talkback to properly scroll to
# next row when focus is on the last item of last row when using a RecyclerView
# Keep optimized and shrunk proguard to prevent issues like this when using
# support jar.
-keep class androidx.recyclerview.widget.RecyclerView { *; }

# Fragments
-keep class ** extends androidx.fragment.app.Fragment {
    public <init>(...);
}
-keep class ** extends android.app.Fragment {
    public <init>(...);
}

## Prevent obfuscating various overridable objects
-keep class ** implements com.android.launcher3.util.ResourceBasedOverride {
    public <init>(...);
}

-keep interface com.android.launcher3.userevent.nano.LauncherLogProto.** {
  *;
}
-keep interface com.android.launcher3.model.nano.LauncherDumpProto.** {
  *;
}

# Discovery bounce animation
-keep class com.android.launcher3.allapps.DiscoveryBounce$VerticalProgressWrapper {
  public void setProgress(float);
  public float getProgress();
}

# BUG(70852369): Surpress additional warnings after changing from Proguard to R8
-dontwarn android.app.**
-dontwarn android.graphics.**
-dontwarn android.os.**
-dontwarn android.view.**
-dontwarn android.window.**

# Ignore warnings for hidden utility classes referenced from the shared lib
-dontwarn com.android.internal.util.**

################ Do not optimize recents lib #############
-keep class com.android.systemui.** {
  *;
}

-keep class com.android.quickstep.** {
  *;
}

###### LauncherC
-keep class app.lws.launcherc.quickstepcompat.** {
  *;
}
-keep class org.chickenhook.restrictionbypass.** { *; }

-keep class * extends com.google.protobuf.GeneratedMessageLite { *; }

# Please add these rules to your existing keep rules in order to suppress warnings.
# This is generated automatically by the Android Gradle plugin.
-dontwarn android.appwidget.AppWidgetHost$AppWidgetHostListener
-dontwarn android.content.pm.IPackageManager
-dontwarn android.content.pm.LauncherApps$AppUsageLimit
-dontwarn android.content.pm.ParceledListSlice
-dontwarn android.content.pm.UserInfo
-dontwarn android.hardware.devicestate.DeviceStateManager$DeviceStateCallback
-dontwarn android.hardware.devicestate.DeviceStateManager$FoldStateListener
-dontwarn android.hardware.devicestate.DeviceStateManager
-dontwarn android.media.AudioSystem
-dontwarn android.media.permission.SafeCloseable
-dontwarn android.metrics.LogMaker
-dontwarn android.provider.SearchIndexablesContract
-dontwarn android.provider.SearchIndexablesProvider
-dontwarn android.service.notification.SnoozeCriterion
-dontwarn android.util.MathUtils
-dontwarn android.util.Pools$SynchronizedPool
-dontwarn android.util.RotationUtils
-dontwarn android.util.StatsEvent$Builder
-dontwarn android.util.StatsEvent
-dontwarn android.widget.RemoteViews$InteractionHandler
-dontwarn com.android.internal.annotations.VisibleForTesting
-dontwarn com.android.internal.app.IVoiceInteractionManagerService$Stub
-dontwarn com.android.internal.app.IVoiceInteractionManagerService
-dontwarn com.android.internal.graphics.ColorUtils
-dontwarn com.android.internal.jank.InteractionJankMonitor$Configuration$Builder
-dontwarn com.android.internal.jank.InteractionJankMonitor
-dontwarn com.android.internal.logging.InstanceId
-dontwarn com.android.internal.logging.InstanceIdSequence
-dontwarn com.android.internal.logging.UiEventLogger$UiEventEnum
-dontwarn com.android.internal.logging.UiEventLogger
-dontwarn com.android.internal.logging.UiEventLoggerImpl
-dontwarn com.android.internal.os.SomeArgs
-dontwarn com.android.internal.policy.ScreenDecorationsUtils
-dontwarn com.android.internal.view.RotationPolicy
-dontwarn javax.annotation.Nullable