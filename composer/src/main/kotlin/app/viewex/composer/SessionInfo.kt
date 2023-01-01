package app.viewex.composer

import java.util.*

interface SessionInfo {

    fun getLocale() : Locale

    fun getDeviceInfo(): DeviceInfo

    fun getRoute(): Route

}
