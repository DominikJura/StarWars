package co.uk.androidrecruitmenttask.util.injection.binding

import android.support.v4.app.Fragment
import dagger.Binds
import dagger.Module

@Module
abstract class FragmentBinderModule {

    @Binds
    abstract fun fragment(fragment: Fragment): Fragment
}
