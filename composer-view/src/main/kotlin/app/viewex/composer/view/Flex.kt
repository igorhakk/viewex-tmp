package app.viewex.composer.view

import app.viewex.composer.*

class Flex(
    definition: Definition,
) : AbstractView.Dynamic<Flex.Definition, Flex.Content>(definition) {

    constructor(definition: Definition.() -> Unit) : this(Definition().also(definition))

    constructor() : this(Definition())

    companion object {
        val Empty = Flex()
    }

    class Definition(
        var height: Size = Size.Inherit,
        var width: Size = Size.Inherit,
        override var marginTop: Size = Size.Empty,
        override var marginBottom: Size = Size.Empty,
        override var marginLeft: Size = Size.Empty,
        override var marginRight: Size = Size.Empty,
        override var paddingTop: Size = Size.Empty,
        override var paddingBottom: Size = Size.Empty,
        override var paddingLeft: Size = Size.Empty,
        override var paddingRight: Size = Size.Empty,
        var direction: Props.Direction = Props.Direction.Row,
        var wrap: Props.Wrap = Props.Wrap.Nowrap,
        var justifyContent: Props.JustifyContent = Props.JustifyContent.FlexStart,
        var alignItems: Props.AlignItems = Props.AlignItems.Stretch,
        var alignContent: Props.AlignContent = Props.AlignContent.Normal
    ) : ViewDefinition<Props>, ViewDefinition.ContentAware<Content>, CommonProps.InitMargin, CommonProps.InitPadding {

        private var _content : Content? = null

        override fun getContent(): Content = _content ?: Content.Empty

        fun children(container: ChildContainer.() -> Unit) {

            val childList = mutableListOf<View>()

            object : ChildContainer {
                override fun add(view: View) {
                    childList.add(view)
                }
            }.container()

            this._content = Content(childList.toList())
        }

        interface ChildContainer {
            fun add(view: () -> View) = add(view())
            fun add(view: View)
        }

        override fun buildProps(): Props = object : Props {
            override val direction: Props.Direction = this@Definition.direction
            override val wrap: Props.Wrap = this@Definition.wrap
            override val justifyContent: Props.JustifyContent = this@Definition.justifyContent
            override val alignItems: Props.AlignItems = this@Definition.alignItems
            override val alignContent: Props.AlignContent = this@Definition.alignContent
            override val height: Size = this@Definition.height
            override val width: Size = this@Definition.width
            override val marginTop: Size = this@Definition.marginTop
            override val marginBottom: Size = this@Definition.marginBottom
            override val marginLeft: Size = this@Definition.marginLeft
            override val marginRight: Size = this@Definition.marginRight
            override val paddingTop: Size = this@Definition.paddingTop
            override val paddingBottom: Size = this@Definition.paddingBottom
            override val paddingLeft: Size = this@Definition.paddingLeft
            override val paddingRight: Size = this@Definition.paddingRight
        }
    }

    interface Props : CommonProps.Box, CommonProps.Margin, CommonProps.Padding, View.Props {

        val direction: Direction

        val wrap: Wrap

        val justifyContent: JustifyContent

        val alignItems: AlignItems

        val alignContent: AlignContent

        enum class Direction : EnumProp { //def Row
            Row, RowReverse, Column, ColumnReverse
        }

        enum class Wrap : EnumProp { //def Nowrap
            Nowrap, Wrap, WrapReverse
        }

        enum class JustifyContent : EnumProp { //def FlexStart
            FlexStart, FlexEnd, Center, SpaceBetween, SpaceAround, SpaceEvenly
        }

        enum class AlignItems : EnumProp { //def Stretch
            Stretch, FlexStart, FlexEnd, Center, Baseline
        }

        enum class AlignContent : EnumProp { //def Normal
            Normal, FlexStart, FlexEnd, Center, SpaceBetween, SpaceAround, Stretch
        }
    }

    class Content(
        views: Iterable<View>?
    ) : View.Content, Iterable<View> {

        private val _views = views?.toList() ?: emptySet()

        companion object {
            val Empty = Content(null)
        }

        override fun iterator(): Iterator<View> = _views.iterator()
    }
}

fun Flex.Definition.ChildContainer.FlexChild(
    definition: Flex.Definition.() -> Unit
) = add(Flex(definition))
