package co.uk.androidrecruitmenttask.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
class People(
        val name: String,
        val starships: ArrayList<String>
) : Parcelable {

    /**
     * name : Luke Skywalker
     * height : 172
     * mass : 77
     * hair_color : blond
     * skin_color : fair
     * eye_color : blue
     * birth_year : 19BBY
     * gender : male
     * homeworld : http://swapi.co/api/planets/1/
     * films : ["http://swapi.co/api/films/6/","http://swapi.co/api/films/3/","http://swapi.co/api/films/2/","http://swapi.co/api/films/1/","http://swapi.co/api/films/7/"]
     * species : ["http://swapi.co/api/species/1/"]
     * vehicles : ["http://swapi.co/api/vehicles/14/","http://swapi.co/api/vehicles/30/"]
     * starships : ["http://swapi.co/api/starships/12/","http://swapi.co/api/starships/22/"]
     * created : 2014-12-09T13:50:51.644000Z
     * edited : 2014-12-20T21:17:56.891000Z
     * url : http://swapi.co/api/people/1/
     */
}
