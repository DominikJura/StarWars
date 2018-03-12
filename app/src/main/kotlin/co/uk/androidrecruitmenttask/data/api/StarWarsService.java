package co.uk.androidrecruitmenttask.data.api;


import co.uk.androidrecruitmenttask.data.People;
import co.uk.androidrecruitmenttask.data.Starships;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by filipradon on 10/04/17.
 */

public interface StarWarsService {

    @GET("people/")
    public Single<ListResponse<People>> getPeople(@Query("page") int page);

    @GET("starships/{id}/")
    public Single<Starships> getStarshipById(@Path("id") int starshipId);

}