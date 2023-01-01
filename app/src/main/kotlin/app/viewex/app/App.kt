package app.viewex.app

import app.viewex.app.page.Page
import app.viewex.composer.layout.LayoutMetadata

interface App : LayoutMetadata.Routed {
    fun getBlankPages() : Collection<Page>
}
