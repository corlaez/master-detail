package jarmandocordova.masterdetail.global.data.belatrixsf;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by jarma on 10/5/2016.
 */

public interface BelatrixsfApi {
    /*
    String END = "http://ca9z.com/belatrixsf/data.json";
    @GET("/")
    Observable<Data> getData();*/
    String END = "http://ca9z.com";
    String EXAMPLE = "{ \"period\": \"2016/09\", \"myWhy\": \"SkyDiving (MOCK)\", \"dream\": \"100 + 4 by OCT 2018\", \"roadmap\": \"Blue Diamond by December 2016\", \"metric\": [{ \"type\": \"Total Breakaway Volume\", \"value\": \"0.00\", \"yoy\": \"0.00%\", \"goal\": \"50,000\" }, { \"type\": \"Recruiting Rate\", \"value\": \"0.00%\", \"yoy\": \"0.00%\", \"goal\": \"\" }, { \"type\": \"Lapse Rate (Retention)\", \"value\": \"100.00%\", \"yoy\": \"-1,428.57%\", \"goal\": \"\" }, { \"type\": \"Rate of Monthly Volume\", \"value\": \"0.00\", \"yoy\": \"0.00%\", \"goal\": \"\" }, { \"type\": \"Total Actives\", \"value\": \"0\", \"yoy\": \"0.00%\", \"goal\": \"\" }, { \"type\": \"New Actives\", \"value\": \"0\", \"yoy\": \"0.00%\", \"goal\": \"\" }, { \"type\": \"Passed LOIs\", \"value\": \"0\", \"yoy\": \"0.00%\", \"goal\": \"\" }, { \"type\": \"Executives - Blue Diamonds\", \"value\": \"0\", \"yoy\": \"0.00%\", \"goal\": \"\" }, { \"type\": \"Ruby Executives and Above\", \"value\": \"0\", \"yoy\": \"0.00%\", \"goal\": \"\" }, { \"type\": \"% of Execs-Blues >= 3000 GSV\", \"value\": \"0.00%\", \"yoy\": \"0.00%\", \"goal\": \"50\" }, { \"type\": \"Total Executives\", \"value\": \"0\", \"yoy\": \"0.00%\", \"goal\": \"50\" }], \"volume\": [{ \"type\": \"ARO PSV\", \"value\": \"110.00\", \"minRequired\": \"Y\", \"targetAmount\": \"100.00\", \"volumeTarget\": \"Y\" }, { \"type\": \"PSV\", \"value\": \"110.00\", \"minRequired\": \"Y\", \"targetAmount\": \"100.00\", \"volumeTarget\": \"Y\" }, { \"type\": \"GSV\", \"value\": \"3,028.45\", \"minRequired\": \"Y\", \"targetAmount\": \"3,000.00\", \"volumeTarget\": \"Y\" }] }";

    @GET("/belatrixsf/data.json")
    Observable<Data> getData();
}

/* EXAMPLE

{
period: "2016/09",
myWhy: "SkyDiving",
dream: "100 + 4 by OCT 2018",
roadmap: "Blue Diamond by December 2016",
metric: [
{
type: "Total Breakaway Volume",
value: "0.00",
yoy: "0.00%",
goal: "50,000"
},

 */
