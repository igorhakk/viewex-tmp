package app.viewex.composer.view

import app.viewex.composer.AbstractView
import app.viewex.composer.Color
import app.viewex.composer.View
import app.viewex.composer.ViewDefinition
import app.viewex.core.details.*
import app.viewex.core.util.StringUtils
import app.viewex.core.util.firstToUpper

class Tabs(
    definition: Definition
) : AbstractView.Dynamic<Tabs.Definition, Tabs.Content>(definition) {

    constructor(init: Definition.() -> Unit) : this(Definition().also(init))

    class Definition(
        var textColor: Color = Color.Empty,
        var indicatorColor: Color = Color.Empty
    ) : ViewDefinition<Props>, ViewDefinition.ContentAware<Content> {

        private var _vertical: Boolean = false

        private var _activeTabIndex = 0

        private var _content: Content? = null

        fun vertical(enabled: Boolean = true) {
            _vertical = enabled
        }

        fun tabs(container: TabContainer.() -> Unit) {
            val tabMap: MutableMap<String, Tab> = mutableMapOf()

            var activeTab: Int = 0

            object : TabContainer {
                override fun add(name: String, details: TabDetails?): Tab {
                    val tabName = TabName(name)

                    if (tabMap.containsKey(name))
                        throw IllegalArgumentException("Tab [ $name ] already exists")

                    val tabDetails = details ?: TabDetails(tabName)

                    return Tab(tabName, tabDetails, tabMap.size).also {
                        tabMap[name] = it
                    }
                }

                override fun Tab.activeTab() {
                    activeTab = index
                }

            }.container()

            _content = Content(tabMap.values)
            _activeTabIndex = activeTab
        }



        interface TabContainer {
            fun add(name: String, details: TabDetails? = null): Tab

            fun Tab.activeTab()
        }


        override fun buildProps(): Props = object : Props {
            override val vertical: Boolean = this@Definition._vertical
            override val textColor: Color = this@Definition.textColor
            override val indicatorColor: Color = this@Definition.indicatorColor
            override val activeTabIndex: Int = this@Definition._activeTabIndex
        }

        override fun getContent(): Content = _content ?: Content.Empty

    }

    interface Props : View.Props {
        val vertical: Boolean
        val textColor: Color
        val indicatorColor: Color
        val activeTabIndex: Int
    }

    class Content(
        private val tabs: Iterable<Tab>
    ) : View.Content, Iterable<Tab> {

        companion object {
            val Empty = Content(emptyList())
        }

        override fun iterator(): Iterator<Tab> = tabs.iterator()
    }

    class TabName(name: String) : IdentifiedPath.Item(name), ObjectName

    class Tab internal constructor(
        override val name: TabName,
        val details: TabDetails = TabDetails(name),
        val index: Int
    ) : Named<TabName>

    class TabDetails internal constructor(
        override val label: Label,
        override val description: Description = Description.Empty,
        override val icon: IconName = IconName.Empty
    ) : ObjectDetails {

        constructor(
            name: TabName,
            description: Description = Description.Empty,
            icon: IconName = IconName.Empty
        ) : this(
            Label.parse(StringUtils.camelCaseToString(name.value).firstToUpper()),
            description, icon
        )

        constructor(
            objectDetails: ObjectDetails
        ) : this(objectDetails.label, objectDetails.description, objectDetails.icon)
    }
}

fun Flex.Definition.ChildContainer.TabsChild(
    definition: Tabs.Definition.() -> Unit
) = add(Tabs(definition))
