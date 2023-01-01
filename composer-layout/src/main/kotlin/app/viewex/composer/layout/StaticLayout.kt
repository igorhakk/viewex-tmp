package app.viewex.composer.layout

import app.viewex.composer.Layout
import app.viewex.composer.View

interface StaticLayout : Layout {
    fun renderContent(content: View)
}
