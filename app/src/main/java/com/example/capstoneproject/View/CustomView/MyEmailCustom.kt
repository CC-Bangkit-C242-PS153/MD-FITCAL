package com.example.capstoneproject.View.CustomView

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Patterns
import android.view.MotionEvent
import android.view.View
import android.view.ViewTreeObserver
import androidx.appcompat.widget.AppCompatEditText
import com.google.android.material.textfield.TextInputLayout

class MyEmailCustom @JvmOverloads constructor (
    context: Context,
    attrs: AttributeSet? = null
): AppCompatEditText(context,attrs), View.OnTouchListener {
    init {
        viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                (parent as? TextInputLayout)?.isHintEnabled = false
                viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
        setOnTouchListener(this)
        addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(s != null && !Patterns.EMAIL_ADDRESS.matcher(s).matches()){
                    setError("Invalid email format", null)
                } else{
                    null
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
    }
    override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
        return false
    }
}