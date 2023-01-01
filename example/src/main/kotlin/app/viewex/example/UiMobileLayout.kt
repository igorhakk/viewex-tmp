package app.viewex.example

import app.viewex.composer.Layout
import app.viewex.layout.tmp.View
import app.viewex.view.tmp.Flex
import app.viewex.composer.Size
import app.viewex.view.tmp.ViewRender

class UiMobileLayout(context: UiContext, pageContainer: PageContainer) : Layout {

    private val content = ViewRender(context, Flex.Empty)

    private var view = Flex {

        width = Size.Percent(100)
        height = Size.Percent(100)

        addChild(content)


    }

    override fun composeView(): View.Template = view
}
