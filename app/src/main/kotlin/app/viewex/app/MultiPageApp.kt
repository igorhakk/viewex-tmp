package app.viewex.app

import app.viewex.app.page.Page
import app.viewex.composer.ViewContext
import app.viewex.composer.layout.LayoutDetails
import app.viewex.composer.layout.LayoutName
import app.viewex.core.type.UrlPath

abstract class MultiPageApp(
    context: ViewContext,
    appName: String? = null
) : AppLayout {

    final override val name: LayoutName = appName?.let {
        LayoutName(it)
    } ?: LayoutName.nameOfClass(this::class, "LayoutApp", "Layout", "App")

    private val pageGroup = PageGroup(name)

    private val _appUrl = UrlPath(listOf(name))

    final override val details: LayoutDetails.Route = this.getDetails(_appUrl)

    protected open fun getDetails(
        appUrl: UrlPath
    ): LayoutDetails.Route = LayoutDetails.Route.ofLayoutName(this.name, appUrl)

    protected fun registerPage(page: Page) = pageGroup.registerPage(page)



    private class PageGroup(
        appName: LayoutName
    ) : app.viewex.app.page.PageGroup(appName.value) {
        fun registerPage(page: Page) = this.addPage(page)
    }
}
