package com.example.lojong.base.extensions

fun Any?.whenNull(action: () -> Unit) {
    if (this == null) action()
}

fun  <T> T?.whenNullReturn(action: () -> T): T {
    return this ?: return action()
}