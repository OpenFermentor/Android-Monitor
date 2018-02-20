package org.openfermentor.biomonitor.ui.main.view

import com.f2prateek.dart.HensonNavigable
import com.xmartlabs.bigbang.ui.SingleFragmentActivity

@HensonNavigable
class MainActivity : SingleFragmentActivity() {
  override fun createFragment() = MainFragmentBuilder().build()
}
