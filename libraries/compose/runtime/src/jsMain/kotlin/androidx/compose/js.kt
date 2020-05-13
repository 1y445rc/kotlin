/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package androidx.compose

import kotlin.js.Date

import org.w3c.dom.Window

actual typealias EmbeddingUIContext = Window

actual abstract class Reference<T>() {
    public actual open fun get(): T? = null
}

actual open class ReferenceQueue<T> actual constructor() {
    actual open fun poll(): Reference<out T>? = null
}

actual open class WeakReference<T> : Reference<T> {
    private val value_: T?

    actual constructor(referent: T, q: ReferenceQueue<in T>?) {
        value_ = referent
    }

    constructor() {
        value_ = null
    }
    actual constructor(referent: T) : this(referent, null)

    override fun get(): T? = value_
}

actual typealias WeakHashMap<K, V> = HashMap<K, V>

internal actual open class ThreadLocal<T> actual constructor(
    private val initialValue: () -> T
) {
    private var value: T = initialValue()

    actual fun get(): T = value

    actual fun set(value: T) {
        this.value = value
    }
}

internal actual fun identityHashCode(instance: Any?): Int =
    if (instance == null) 0 else (instance as Any).hashCode()

internal actual inline fun <R> synchronized(lock: Any, block: () -> R): R {
    return block()
}

internal actual class BuildableMapBuilder<K, V>() : MutableMap<K, V> by HashMap<K, V>() {
    actual fun build(): BuildableMap<K, V> {
        TODO()
    }
}

actual class BuildableMap<K, V>() : Map<K, V> by HashMap<K, V>() {
    internal actual fun builder(): BuildableMapBuilder<K, V> {
        TODO()
    }
}
internal actual fun <K, V> buildableMapOf(): BuildableMap<K, V> = BuildableMap<K, V>()

actual class Looper

internal actual fun isMainThread(): Boolean = true

external fun setTimeout(
    handler: dynamic,
    timeout: Int = definedExternally,
    vararg arguments: Any?
): Int

internal actual class Handler {
    actual constructor(looper: Looper) {}
    actual fun postAtFrontOfQueue(block: () -> Unit): Boolean {
        setTimeout(block, 0)
        return true
    }
}

internal actual object LooperWrapper {
    private val mainLooper = Looper()
    actual fun getMainLooper(): Looper = mainLooper
}

internal actual object Choreographer {
    private val cancelled = mutableSetOf<ChoreographerFrameCallback>()

    actual fun postFrameCallback(callback: ChoreographerFrameCallback) {
        postFrameCallbackDelayed(0, callback)
    }
    actual fun postFrameCallbackDelayed(delayMillis: Long, callback: ChoreographerFrameCallback) {
        setTimeout({
                       if (callback !in cancelled) {
                           callback.doFrame(Date().getTime().toLong())
                       } else {
                           cancelled.remove(callback)
                       }
                   }, delayMillis.toInt())
    }
    actual fun removeFrameCallback(callback: ChoreographerFrameCallback) {
        cancelled += callback
    }
}

actual interface ChoreographerFrameCallback {
    actual fun doFrame(frameTimeNanos: Long)
}

internal actual object Trace {
    actual fun beginSection(name: String) {
        // Do nothing.
    }

    actual fun endSection() {
        // Do nothing.
    }
}

interface Runnable {
    fun run()
}

internal class JSRecomposer : Recomposer() {

    private var frameScheduled = false

    inner class Callback : Runnable {
        var cancelled: Boolean = false

        override fun run() {
            if (cancelled) return
            frameScheduled = false
            dispatchRecomposes()
        }
    }

    private val frameCallback = Callback()

    init {
        setTimeout({ Callback() }, 0)
    }

    override fun scheduleChangesDispatch() {
        if (!frameScheduled) {
            frameScheduled = true
            setTimeout({ Callback() }, 0)
        }
    }

    override fun hasPendingChanges(): Boolean = frameScheduled

    override fun recomposeSync() {
        if (frameScheduled) {
            frameCallback.cancelled = true
            frameCallback.run()
        }
    }
}

internal actual fun createRecomposer(): Recomposer {
    return JSRecomposer()
}

internal actual fun recordSourceKeyInfo(key: Any) {
    println("recordSourceKeyInfo")
}

actual fun keySourceInfoOf(key: Any): String? = null

actual annotation class MainThread()
actual annotation class CheckResult(actual val suggest: String)
actual annotation class TestOnly()