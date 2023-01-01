package app.viewex.app.page

import app.viewex.composer.layout.page.PageLayout

abstract class Page(
    parent: PageLayout,
    pageName: String? = null
) : PageLayout(parent, pageName)
