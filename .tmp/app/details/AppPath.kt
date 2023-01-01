package app.viewex.app.details

import app.viewex.core.details.IdentifiedPath

class AppPath(
    appName: AppName
) : IdentifiedPath(listOf(BaseItem, appName)) {
    companion object {
        val BaseItem = Item.parse("app")
    }
}
