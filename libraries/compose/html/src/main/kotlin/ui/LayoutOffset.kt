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

package ui

import html.StyleBuilder
import html.css

/**
 * Offset the content by ([x]dp, [y]dp). The offsets can be positive as well as non positive.
 *
 * Example usage:
 * @sample androidx.ui.layout.samples.LayoutOffsetModifier
 */
fun Modifier.offset(x: Dp = 0.dp, y: Dp = 0.dp) = this + OffsetModifier(x, y)

private data class OffsetModifier(val x: Dp, val y: Dp) : LayoutModifier {
    override fun apply(styleBuilder: StyleBuilder) {
        styleBuilder.style.left = x.css()
        styleBuilder.style.top = y.css()
    }
}