package app.viewex.app.page

import app.viewex.composer.layout.page.PageLayoutSet

interface PageGroupMap : PageLayoutSet<Page> {

    fun getGroup(name: String): PageGroup

    fun getGroupOrNull(name: String) : PageGroup?

}
