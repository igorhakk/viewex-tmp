package app.viewex.composer.layout

import app.viewex.core.details.Description
import app.viewex.core.details.IconName
import app.viewex.core.details.Label
import app.viewex.core.details.ObjectDetails
import app.viewex.core.util.StringUtils
import app.viewex.core.util.firstToUpper

open class LayoutDetails(
    override val label: Label,
    override val description: Description = Description.Empty,
    override val icon: IconName = IconName.Empty
) : ObjectDetails {

    constructor(
        label: String,
        description: Description? = null,
        icon: IconName = IconName.Empty
    ) : this(
        Label.parse(label),
        Description.parseOrEmpty(description),
        icon
    )

    companion object {
        fun ofLayoutName(name: LayoutName) : LayoutDetails = LayoutDetails(
            StringUtils.camelCaseToString(name.value).firstToUpper()
        )

        fun ofObjectDetails(details: ObjectDetails) : LayoutDetails = LayoutDetails(
            details.label,
            details.description,
            details.icon
        )
    }
}
