package org.openfermentor.biomonitor.ui.instruction

import com.f2prateek.dart.InjectExtra
import com.xmartlabs.bigbang.ui.SingleFragmentActivity

class InstructionActivity : SingleFragmentActivity() {
  @InjectExtra
  lateinit var instruction: String
  
  override fun createFragment() = InstructionFragmentBuilder(instruction).build()
  
  override fun onBackPressed() { }
}
