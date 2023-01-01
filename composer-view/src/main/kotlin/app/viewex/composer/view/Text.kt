package app.viewex.composer.view

import app.viewex.composer.AbstractView
import app.viewex.composer.Color
import app.viewex.composer.View
import app.viewex.composer.ViewDefinition
import app.viewex.core.util.clearHtml

class Text(
    definition: Definition
) : AbstractView.Dynamic<Text.Definition, Text.Content>(definition) {

    constructor(init: Definition.() -> Unit) : this(Definition().also(init))

    class Definition(
        var textColor: Color = Color.Empty,
        var content: String? = null
    ) : ViewDefinition<Props>, ViewDefinition.ContentAware<Content> {

        private val _content: Content? = null

        override fun getContent(): Content = _content ?: Content.Empty

        override fun buildProps(): Props = object : Props {
            override val textColor: Color = this@Definition.textColor
        }

    }

    interface Props : View.Props {
        val textColor: Color
    }

    class Content(
        private val text: String?
    ) : View.Content, CharSequence {

        companion object {
            val Empty = Content(null)

            fun clearedHtml(text: String): Content = Content(text.clearHtml())
        }

        override val length: Int = text?.length ?: 0

        override fun get(
            index: Int
        ): Char = text?.get(index) ?: throw NullPointerException("Text is null")

        override fun subSequence(
            startIndex: Int,
            endIndex: Int
        ): CharSequence = text?.subSequence(startIndex, endIndex)
            ?: throw NullPointerException("Text is null")

    }
}

fun Flex.Definition.ChildContainer.TextChild(
    definition: Text.Definition.() -> Unit
) = add(Text(definition))
