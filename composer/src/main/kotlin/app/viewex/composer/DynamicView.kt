package app.viewex.composer

import app.viewex.composer.action.ActionName

interface DynamicView<Definition : ViewDefinition<*>, Content : View.Content> : View {

    companion object {
        val UpdatePropsActionName = ActionName("updateViewProps")
        val UpdateContentActionName = ActionName("updateViewContent")
    }

    fun updateProps(context: ViewContext, definition: Definition.() -> Unit)

    fun updateContent(context: ViewContext, content: Content)
}
