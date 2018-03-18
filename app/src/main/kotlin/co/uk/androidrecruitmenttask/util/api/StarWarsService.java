package co.uk.androidrecruitmenttask.util.api;


import co.uk.androidrecruitmenttask.data.api.ListResponse;
import co.uk.androidrecruitmenttask.data.api.People;
import co.uk.androidrecruitmenttask.data.api.Starships;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by filipradon on 10/04/17.
 */

public interface StarWarsService {

    @GET("people/")
    Single<ListResponse<People>> getPeople(@Query("page") int page);

    @GET("starships/{id}/")
    Single<Starships> getStarshipById(@Path("id") int starshipId);

}
