package co.uk.androidrecruitmenttask;

import android.app.Application;

import co.uk.androidrecruitmenttask.data.api.ApiComponent;
import co.uk.androidrecruitmenttask.data.api.ApiModule;
import co.uk.androidrecruitmenttask.data.api.DaggerApiComponent;

/**
 * Created by filipradon on 10/04/17.
 */

public class StarWarsApplication extends Application {

    private ApiComponent apiComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        apiComponent = DaggerApiComponent.builder()
                .apiModule(new ApiModule())
                .build();
    }

    public ApiComponent getApiComponent() {
        return apiComponent;
    }
}
