package app.viewex.composer

import app.viewex.composer.action.ViewAction

abstract class AbstractView(
    templateName: String? = null
) : View {

    final override val viewId: ViewId = ViewId.generate(this::class)

    final override val template: View.Template = templateName?.let {
        View.Template(it)
    } ?: View.Template.ofClassName(this::class)

    abstract class Dynamic<Definition : ViewDefinition<*>, C : View.Content> (
        private val definition: Definition,
        templateName: String? = null
    ) : AbstractView(templateName), DynamicView<Definition, C> {

        private var _props: View.Props = definition.buildProps()

        final override val props: View.Props get() = _props

        private var _content: View.Content? = null

        final override val content: View.Content get() = _content ?: View.Content.Empty


        init {
            if (definition is ViewDefinition.ContentAware<*>)
                _content = definition.getContent()
        }

        final override fun updateProps(context: ViewContext, definition: Definition.() -> Unit) {
            _props = this.definition.apply(definition).buildProps()

            context.callAction(
                ViewAction(viewId, DynamicView.UpdatePropsActionName, _props)
            )

            if (definition is ViewDefinition.ContentAware<*> && definition.getContent() != content) {
                try {
                    val content = definition.getContent()
                    updateRawContent(context, content)
                } catch (_: Exception) {}
            }
        }

        final override fun updateContent(context: ViewContext, content: C) = updateRawContent(context, content)

        private fun updateRawContent(context: ViewContext, content: View.Content) {
            _content = content
            context.callAction(
                ViewAction(viewId, DynamicView.UpdateContentActionName, _content!!)
            )
        }
    }

}
