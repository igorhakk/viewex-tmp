package app.viewex.app

import app.viewex.app.details.PageDetails
import app.viewex.app.details.PageName
import app.viewex.app.details.PagePath

abstract class Page(
    app: AppDefinition<*>,
    name: String? = null
) : PageDefinition {

    final override val name: PageName = name?.let { PageName(it) } ?: PageName.nameOfClass(this::class)

    final override val path: PagePath = PagePath(app.name, this.name)

    override val details: PageDetails = PageDetails.Configurer(this.name).also {
        configureDetails(it)
    }.getDetails()

    protected abstract fun configureDetails(configurer: PageDetails.Configurer)

}
