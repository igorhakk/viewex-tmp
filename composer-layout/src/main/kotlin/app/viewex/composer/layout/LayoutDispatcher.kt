package app.viewex.composer.layout

import app.viewex.composer.Layout

abstract class LayoutDispatcher : Layout {

    protected abstract fun onSelectLayout(layout: NamedLayout)

}
