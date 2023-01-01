package app.viewex.app.details


import app.viewex.app.localization.TranslateService
import java.util.*

interface DetailsProvider<Details : ObjectDetails> {

    fun defaultDetails(): Details

    suspend fun localizedDetails(
        translateService: TranslateService,
        locale: Locale = Locale.getDefault()
    ): Details

}
