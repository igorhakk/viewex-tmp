package app.viewex.example.settings.app

import app.viewex.composer.layout.page.PageGroupProvider
import app.viewex.composer.Layout
import app.viewex.layout.tmp.View
import app.viewex.view.tmp.Flex
import app.viewex.view.tmp.FlexChild
import app.viewex.composer.Size
import app.viewex.view.tmp.ViewRender

class SettingsMobileLayout(
    context: UiContext,
    groupProvider: PageGroupProvider
) : Layout {

    private val content = ViewRender(context, Flex.Empty)

    private val view = Flex {
        width = Size.Inherit
        height = Size.Inherit

        FlexChild {

        }
        FlexChild {
            addChild(content)
        }
    }

    override fun composeView(): View.Template = view
}
