package app.viewex.example

import app.viewex.composer.Size
import app.viewex.composer.Layout
import app.viewex.composer.view.Flex
import app.viewex.composer.view.FlexChild
import app.viewex.composer.view.Tabs
import app.viewex.layout.tmp.NavigateLayout
import app.viewex.layout.tmp.View
import app.viewex.view.*
import app.viewex.view.tmp.*

class UiDesktopLayout(context: UiContext, pageContainer: PageContainer) : Layout {

    private val navigate = Navigate(context, pageContainer).also {
        it.setOnSelectItem { tab ->
            val view = pageContainer.getViewOrNull(tab.name.value)
            if (view != null) {
                content.renderContent(view)
            }
        }
    }

    private val content = ViewRender(context, Flex.Empty)

    private var view = Flex {

        width = Size.Percent(100)
        height = Size.Percent(100)

        FlexChild {
            width = Size.Px(75)
            addChild(navigate)
        }
        FlexChild {
            addChild(content)
        }

    }

    override fun composeView(): View.Template = view

    private class Navigate(
        context: UiContext,
        pages: PageContainer
    ) : NavigateLayout<Tabs.Tab> {

        private var onSelectCallback: (Tabs.Tab) -> Unit = {}

        private val tabs = Tabs(context) {
            vertical = true
        }

        init {
            apply {
                pages.pageNames.forEach {
                    val details = pages.getDetails(it)
                    tabs.addTab(it, Tabs.TabDetails(details))
                }
                tabs.addOnSelectTabListener {
                    onSelectCallback(it)
                }
            }
        }

        override fun setOnSelectItem(
            callback: (Tabs.Tab) -> Unit
        ) {
            onSelectCallback = callback
        }

        override fun composeView(): View.Template = tabs
    }
}
