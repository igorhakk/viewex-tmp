package app.viewex.app.details

import app.viewex.core.details.*

class PageDetails private constructor(
    override val label: Label,
    override val description: Description,
    override val icon: IconName
) : ObjectDetails {

    class Configurer(
        pageName: PageName
    ) : MutableDetails<PageName, PageDetails>(pageName) {

        override fun mapDetails(
            label: Label,
            description: Description,
            icon: IconName
        ): PageDetails = PageDetails(label, description, icon)

    }
}
