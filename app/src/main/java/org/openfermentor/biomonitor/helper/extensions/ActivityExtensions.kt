package org.openfermentor.biomonitor.helper.extensions

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.support.v4.app.Fragment

fun Activity.openLink(url: String) = startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))

fun Intent.startFrom(activity: Activity) = activity.startActivity(this)

fun Intent.startForResult(fragment: Fragment, requestCode: Int) = fragment.startActivityForResult(this, requestCode)
