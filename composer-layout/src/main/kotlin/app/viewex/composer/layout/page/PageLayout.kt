package app.viewex.composer.layout.page

import app.viewex.composer.Layout
import app.viewex.composer.layout.LayoutDetails
import app.viewex.composer.layout.LayoutMetadata
import app.viewex.composer.layout.LayoutName
import app.viewex.core.type.UrlPath

abstract class PageLayout(
    parent: PageLayout? = null,
    name: String? = null
) : Layout, LayoutMetadata.Routed {

    final override val name: LayoutName = name?.let {
        LayoutName(it)
    } ?: LayoutName.nameOfClass(
        this::class,
        "PageLayout", "Layout", "Page"
    )

    private val _pageUrl = parent?.let {
        UrlPath(it.details.url.items.plus(this.name))
    } ?: UrlPath(listOf(this.name))

    final override val details: LayoutDetails.Route = this.getDetails(_pageUrl)

    protected open fun getDetails(
        pageUrl: UrlPath
    ): LayoutDetails.Route = LayoutDetails.Route.ofLayoutName(this.name, pageUrl)
}
