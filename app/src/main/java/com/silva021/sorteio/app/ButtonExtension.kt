package com.silva021.sorteio.app

import android.graphics.Color
import android.widget.Button

fun Button.disable(color: String){
    isEnabled = false
    setBackgroundColor(Color.parseColor(color))
}

fun Button.enable(color: String){
    isEnabled = true
    setBackgroundColor(Color.parseColor(color))
}

