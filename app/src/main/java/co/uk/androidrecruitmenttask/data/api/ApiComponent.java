package co.uk.androidrecruitmenttask.data.api;

import javax.inject.Singleton;

import co.uk.androidrecruitmenttask.ui.MainActivity;
import dagger.Component;

/**
 * Created by filipradon on 10/04/17.
 */

@Singleton
@Component(modules={ApiModule.class})
public interface ApiComponent {

    void inject(MainActivity activity);

}