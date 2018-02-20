package org.openfermentor.biomonitor.ui.instruction

import com.hannesdorfmann.fragmentargs.annotation.Arg
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs
import kotlinx.android.synthetic.main.fragment_instruction.*
import org.openfermentor.biomonitor.R
import org.openfermentor.biomonitor.ui.common.base.BioMonitorFragment
import javax.inject.Inject

@FragmentWithArgs
class InstructionFragment : BioMonitorFragment<InstructionView, InstructionPresenter>(), InstructionView {
  @Arg
  lateinit var instruction: String
  
  @Inject
  override lateinit var presenter: InstructionPresenter
  
  override val layoutResId = R.layout.fragment_instruction
  
  override fun setup() {
    message.text = instruction
    
    button.setOnClickListener { activity.finish() }
  }
}
