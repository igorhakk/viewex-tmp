package app.viewex.composer.layout.page

import java.util.*
import java.util.function.Consumer

open class MutableLayoutMap<Layout : PageLayout> : PageLayoutMap<Layout> {

    private val layoutMap: MutableMap<String, Layout> = mutableMapOf()

    fun addPage(vararg layout: Layout) = addPages(layout.toList())

    fun addPages(layouts: Iterable<Layout>) = layouts.forEach { putLayout(it) }

    private fun putLayout(layout: Layout) {
        if (contains(layout.name.value))
            throw IllegalStateException("Page layout with name [ ${layout.name} ] already exists")
        layoutMap[layout.name.value] = layout
    }

    final override fun getPage(name: String): Layout = getPageOrNull(name)
        ?: throw IllegalStateException("Page layout with name [ $name ] not found")

    final override fun getPageOrNull(name: String): Layout? = layoutMap[name]

    final override fun contains(name: String): Boolean = layoutMap.containsKey(name)

    final override fun iterator(): Iterator<Layout> = layoutMap.values.iterator().iterator()

    final override fun forEach(action: Consumer<in Layout>?) = super.forEach(action)

    final override fun spliterator(): Spliterator<Layout> = super.spliterator()

}
