package app.viewex.app

import app.viewex.app.details.PageDetails
import app.viewex.app.details.PageName
import app.viewex.app.details.PagePath
import app.viewex.core.details.ObjectDefinition
import app.viewex.view.ViewBuilder

interface PageDefinition : ObjectDefinition<PageName, PagePath, PageDetails>, ViewBuilder {

}
