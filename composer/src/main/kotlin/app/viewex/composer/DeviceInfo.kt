package app.viewex.composer


class DeviceInfo(
    val type: Type,
    val width: Size,
    val height: Size,
    val orientation: Orientation
) {

    enum class Type : EnumProp {
        Mobile, Tablet, Desktop;
    }

    enum class Orientation : EnumProp {
        Landscape, Portrait
    }
}
