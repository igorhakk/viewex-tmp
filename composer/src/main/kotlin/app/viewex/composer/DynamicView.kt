package app.viewex.composer

import app.viewex.composer.action.ActionName
import app.viewex.view.Flex

interface DynamicView<Definition : ViewDefinition<*>, Content : View.Content> : View {

    companion object {
        val UpdatePropsActionName = ActionName("updateViewProps")
        val UpdateContentActionName = ActionName("updateViewContent")
    }

    fun updateProps(context: ViewContext, definition: Flex.InitProps.() -> Unit)

    fun updateContent(context: ViewContext, content: Content)
}
