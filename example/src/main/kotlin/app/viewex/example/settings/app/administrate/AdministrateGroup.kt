package app.viewex.example.settings.app.administrate

import app.viewex.composer.layout.page.PageGroup
import app.viewex.example.settings.app.SettingsApp

class AdministrateGroup(app: SettingsApp) : PageGroup() {
    init {
        addPage(TenantList(app))
        addPage(UserList(app))
    }
}
