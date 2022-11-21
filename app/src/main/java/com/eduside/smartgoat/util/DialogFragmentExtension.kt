package com.eduside.smartgoat.util
//
//import android.os.Build
//import android.view.View
//import android.view.View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
//import android.view.Window
//import android.view.WindowManager
//import androidx.annotation.ColorRes
//import androidx.core.content.ContextCompat
//import androidx.fragment.app.DialogFragment
//
//
//val DialogFragment.window: Window? get() = dialog?.window
//val DialogFragment.activityDecorView: View? get() = activity?.window?.decorView
//
//fun DialogFragment.getActivitySystemUiVisibility(): Int? =
//    window?.decorView?.systemUiVisibility
//
//fun DialogFragment.setActivitySystemUiVisibility(flags: Int) {
//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//        activityDecorView?.systemUiVisibility = flags
//    }
//}
//
//fun DialogFragment.setStatusBarColor(@ColorRes colorRes: Int) {
//    window?.run {
//        addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//        val color = ContextCompat.getColor(context, colorRes)
//        statusBarColor = color
//
//        // does not work if dim is enabled
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            val decorView = activityDecorView ?: return
//            if (!color.isDark) {
//                decorView.systemUiVisibility =
//                    decorView.systemUiVisibility or SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
//            }
//        }
//    }
//}