/*
 * Copyright (c) 2018 Fondesa
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.fondesa.kpermissions.request.manifest

import android.content.Context
import com.fondesa.kpermissions.extensions.isPermissionGranted
import com.fondesa.kpermissions.request.BasePermissionRequest

/**
 * Created by antoniolig on 05/01/18.
 */
class ManifestPermissionRequest(private val context: Context,
                                private val permissions: Array<out String>) :
        BasePermissionRequest() {

    override fun send() {
        val deniedPermissions = permissions.filter {
            !context.isPermissionGranted(it)
        }.toTypedArray()

        if (deniedPermissions.isNotEmpty()) {
            deniedListener?.onPermissionsPermanentlyDenied(deniedPermissions)
        } else {
            acceptedListener?.onPermissionsAccepted(permissions)
        }
    }
}