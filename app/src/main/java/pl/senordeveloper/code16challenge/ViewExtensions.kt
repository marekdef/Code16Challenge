/*
 * Copyright (C) 2006 The Android Open Source Project
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

package pl.senordeveloper.code16challenge

import android.view.View

val View.humanId: String
    get() {
        if (id == View.NO_ID || id ushr 24 == 0)
            return javaClass.simpleName

        return runCatching {
            val out = StringBuilder()
            val pkgname: String = when (id and -0x1000000) {
                0x7f000000 -> "app"
                0x01000000 -> "android"
                else -> resources.getResourcePackageName(id)
            }
            out.append(pkgname)
            out.append(":")
            out.append(resources.getResourceTypeName(id))
            out.append("/")
            out.append(resources.getResourceEntryName(id))
            out.toString()
        }.getOrDefault(javaClass.simpleName)
    }

// const val NO_ID = -1
//
// val View.humanId: String
//     get() {
//         val stringBuilder = StringBuilder()
//         if (id == NO_ID
//             || id.shr(24) == 0) {
//             return javaClass.simpleName
//         }
//         runCatching {
//             stringBuilder.append(resources.getResourceTypeName(id))
//             stringBuilder.append("/")
//             stringBuilder.append(resources.getResourceEntryName(id))
//         }.onFailure {
//             return javaClass.simpleName
//         }
//         return stringBuilder.toString()
//     }
//
