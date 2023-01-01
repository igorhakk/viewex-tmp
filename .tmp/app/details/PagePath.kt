package app.viewex.app.details

import app.viewex.core.details.IdentifiedPath

class PagePath(
    appName: AppName,
    pageName: PageName
) : IdentifiedPath(listOf(BaseItem, appName, pageName)) {
    companion object {
        val BaseItem = Item.parse("page")
    }
}
