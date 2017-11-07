package org.openfermentor.biomonitor.helper.extensions

import java.util.Random

fun <T> MutableList<T>.shuffle(): List<T> {
  val random = Random()
  for (i in 0 until size) {
    val randomPosition = random.nextInt(size)
    val temp = this[i]
    this[i] = this[randomPosition]
    this[randomPosition] = temp
  }
  return this
}
