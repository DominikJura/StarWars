package co.uk.androidrecruitmenttask.util.injection.modules

import co.uk.androidrecruitmenttask.util.api.StarWarsService
import co.uk.androidrecruitmenttask.util.repository.Repository
import co.uk.androidrecruitmenttask.util.repository.RepositoryImpl
import co.uk.androidrecruitmenttask.util.tools.StarWarsParser
import co.uk.androidrecruitmenttask.util.tools.StarWarsParserImpl
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun starWarsParser(): StarWarsParser =
            StarWarsParserImpl()

    @Provides
    fun repository(starWarsService: StarWarsService, starWarsParser: StarWarsParser): Repository =
            RepositoryImpl(starWarsService, starWarsParser)
}