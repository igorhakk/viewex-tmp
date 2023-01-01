package app.viewex.example.settings.app

import app.viewex.composer.layout.LayoutDetails
import app.viewex.core.details.IconName
import app.viewex.example.settings.app.administrate.AdministrateGroup
import app.viewex.composer.ui.DeviceTypeLayout
import app.viewex.composer.ui.UiContext

class SettingsApp(
    context: UiContext
) : UiApp() {

    private val groupContainer = PageGroupContainer()

    private val layout = DeviceTypeLayout(
        context,
        SettingsDesktopLayout(context, groupContainer),
        SettingsMobileLayout(context, groupContainer)
    )

    init {
        groupContainer.addGroup(AdministrateGroup(this))
    }

    override val details: LayoutDetails = LayoutDetails(
        "Settings",
        null,
        IconName.parse("settings")
    )

}
