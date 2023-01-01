package app.viewex.app.page

import app.viewex.composer.layout.LayoutDetails
import app.viewex.composer.layout.LayoutMetadata
import app.viewex.composer.layout.LayoutName
import app.viewex.composer.layout.page.MutableLayoutMap
import app.viewex.composer.layout.page.PageLayoutMap
import java.util.*
import java.util.function.Consumer


abstract class PageGroup(
    groupName: String? = null
) : LayoutMetadata, PageLayoutMap<Page> {

    final override val name: LayoutName = groupName?.let {
        LayoutName(it)
    } ?: LayoutName.nameOfClass(
        this::class,
        "PageGroupLayout", "GroupLayout", "Layout", "Group", "Page"
    )

    override val details: LayoutDetails = LayoutDetails.ofLayoutName(name)

    private val layoutMap = MutableLayoutMap<Page>()

    protected fun addPage(vararg page: Page) = layoutMap.addPage(*page)

    final override fun getPage(name: String): Page = layoutMap.getPage(name)

    final override fun getPageOrNull(name: String): Page? = layoutMap.getPageOrNull(name)

    final override fun contains(name: String): Boolean = layoutMap.contains(name)

    final override fun iterator(): Iterator<Page> = layoutMap.iterator().iterator()

    final override fun forEach(action: Consumer<in Page>?) = super.forEach(action)

    final override fun spliterator(): Spliterator<Page> = super.spliterator()

}
