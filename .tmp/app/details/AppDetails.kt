package app.viewex.app.details

import app.viewex.core.details.*

class AppDetails private constructor(
    override val label: Label,
    override val description: Description,
    override val icon: IconName
) : ObjectDetails {

    class Configurer(name: AppName) : MutableDetails<AppName, AppDetails>(name) {
        override fun mapDetails(
            label: Label,
            description: Description,
            icon: IconName
        ): AppDetails = AppDetails(label, description, icon)
    }
}
