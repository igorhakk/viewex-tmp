package app.viewex.app

object AppRegistry : Set<AppLayout> {

    private val appSet: MutableSet<AppLayout> = mutableSetOf()

    override val size: Int get() = appSet.size

    override fun isEmpty(): Boolean = appSet.isEmpty()

    override fun iterator(): Iterator<AppLayout> = appSet.iterator()

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun containsAll(apps: Collection<AppLayout>): Boolean = appSet.containsAll(apps)

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun contains(app: AppLayout): Boolean = appSet.contains(app)

    fun registerApp(app: AppLayout) {
        if (contains(app))
            throw IllegalStateException(
                "App [ name: ${app.name}, class: ${app::class.qualifiedName} ] already exists"
            )
        appSet.add(app)
    }
}
