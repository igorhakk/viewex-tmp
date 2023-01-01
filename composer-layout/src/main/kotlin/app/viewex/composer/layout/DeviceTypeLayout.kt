package app.viewex.composer.layout

import app.viewex.composer.DeviceInfo
import app.viewex.composer.Layout
import app.viewex.composer.LayoutParams
import app.viewex.composer.ViewContext


class DeviceTypeLayout<T : Layout>(
    context: ViewContext,
    private val desktopLayout: T,
    mobileLayout: T = desktopLayout,
    tabletLayout: T = desktopLayout,
) : Layout {

    val currentLayout: T = when(context.sessionInfo.deviceInfo.type) {
        DeviceInfo.Type.Desktop -> desktopLayout
        DeviceInfo.Type.Mobile -> mobileLayout
        DeviceInfo.Type.Tablet -> tabletLayout
    }

    override fun composeView(params: LayoutParams) = currentLayout.composeView(params)

}
