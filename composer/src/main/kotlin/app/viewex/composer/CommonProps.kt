package app.viewex.composer

interface CommonProps {

    interface Box {
        val height: Size
        val width: Size
    }

    interface Margin {
        val marginTop: Size
        val marginBottom: Size
        val marginLeft: Size
        val marginRight: Size
    }

    interface InitMargin {
        var marginTop: Size
        var marginBottom: Size
        var marginLeft: Size
        var marginRight: Size

        fun setMargin(all: Size) = setMargin(all, all)

        fun setMargin(topBottom: Size, leftRight: Size) = setMargin(topBottom, leftRight, topBottom)

        fun setMargin(top: Size, leftRight: Size, bottom: Size) {
            marginTop = top
            marginBottom = bottom
            marginLeft = leftRight
            marginRight = leftRight
        }
    }

    interface Padding {
        val paddingTop: Size
        val paddingBottom: Size
        val paddingLeft: Size
        val paddingRight: Size
    }

    interface InitPadding {
        var paddingTop: Size
        var paddingBottom: Size
        var paddingLeft: Size
        var paddingRight: Size

        fun setPadding(all: Size) = setPadding(all, all)

        fun setPadding(topBottom: Size, leftRight: Size) = setPadding(topBottom, leftRight, topBottom)

        fun setPadding(top: Size, leftRight: Size, bottom: Size) {
            paddingTop = top
            paddingBottom = bottom
            paddingLeft = leftRight
            paddingRight = leftRight
        }
    }
}
