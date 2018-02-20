package org.openfermentor.biomonitor.ui.common.views

import android.content.Context
import android.support.annotation.DrawableRes
import android.support.design.widget.TextInputLayout
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import kotlinx.android.synthetic.main.view_text_input_field.view.*
import org.openfermentor.biomonitor.R

class TextInputField @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyleAttr: Int = 0
) : TextInputLayout(context, attrs, defStyleAttr) {
  val view = LayoutInflater.from(context).inflate(R.layout.view_text_input_field, this, true)
  
  var textValue = ""
  var hint = ""
  var inputType = 0
  var imeOptions = 0
  var text: String
    get() = view.editText?.text.toString()
    set(value) { view.editText?.setText(value) }
  
  init {
    bindAttributes(context, attrs)
    initializeView()
  }
  
  private fun initializeView() {
    text = ""
    view.editTextLayout.hint = hint
    
    if (imeOptions != -1) {
      view.editText?.imeOptions = imeOptions
    }
    
    if (inputType != -1) {
      view.editText?.inputType = inputType
    }
    
    text = textValue
  }
  
  private fun bindAttributes(context: Context, attrs: AttributeSet) {
    val a = context.obtainStyledAttributes(attrs, R.styleable.TextInputField, 0, 0)
    textValue = a.getString(R.styleable.TextInputField_android_text) ?: ""
    hint = a.getString(R.styleable.TextInputField_android_hint) ?: ""
    inputType = a.getInt(R.styleable.TextInputField_android_inputType, -1)
    imeOptions = a.getInt(R.styleable.TextInputField_android_imeOptions, -1)
    a.recycle()
  }
  
  fun addTextChangedListener(watcher: TextWatcher) = view.editText?.addTextChangedListener(watcher) ?: Unit
  
  fun setRightDrawable(@DrawableRes drawable: Int, visible: Boolean) =
      view.editText.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, if (visible) drawable else 0, 0)
  
  override fun toString() = text
  
  override fun getBackground() = view.editTextLayout?.background
  
  fun linkEnterToAction(action: () -> Unit) {
    view.editText.imeOptions = EditorInfo.IME_ACTION_DONE
    view.editText.setOnEditorActionListener { _, actionId, _ ->
      if (actionId == EditorInfo.IME_ACTION_DONE) {
        action()
      }
      true
    }
  }
}
