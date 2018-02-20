package org.openfermentor.biomonitor.ui.common.base

import android.os.Bundle
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat
import android.view.MenuItem
import com.xmartlabs.bigbang.ui.SingleFragmentActivity
import kotlinx.android.synthetic.main.activity_biomonitor_single_fragment_with_toolbar_activity.*
import org.openfermentor.biomonitor.R

abstract class BioMonitorSingleFragmentWithToolbarActivity : SingleFragmentActivity() {
  override val layoutResId = R.layout.activity_biomonitor_single_fragment_with_toolbar_activity
  
  @ColorRes
  protected open val toolbarBackgroundColor: Int = R.color.colorPrimaryDark
  @DrawableRes
  protected open val toolbarBackButtonDrawable: Int? = null
  @ColorRes
  protected open val toolbarTitleTextColor: Int = R.color.white
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setupToolbar()
  }
  
  private fun setupToolbar() {
    setSupportActionBar(toolbar)
    toolbar.setBackgroundColor(ContextCompat.getColor(context, toolbarBackgroundColor))
    toolbar.setTitleTextColor(ContextCompat.getColor(context, toolbarTitleTextColor))
    toolbarBackButtonDrawable?.let {
      supportActionBar?.setDisplayHomeAsUpEnabled(true)
      supportActionBar?.setHomeAsUpIndicator(it)
    }
  }
  
  override fun onOptionsItemSelected(item: MenuItem) = when(item.itemId) {
    android.R.id.home -> finish().let { true }
    else -> super.onOptionsItemSelected(item)
  }
  
  fun setToolbarTitle(title: String) = supportActionBar?.let { it.title = title }
}
