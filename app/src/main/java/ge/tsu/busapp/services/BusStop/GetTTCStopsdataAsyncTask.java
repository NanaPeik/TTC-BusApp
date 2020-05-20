package ge.tsu.busapp.services.BusStop;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class GetTTCStopsdataAsyncTask extends AsyncTask<Void,Void, ArrayList<BusStop>> {

    private CallBack callBack;

    @Override
    protected void onPreExecute() {
//        super.onPreExecute();
    }

    @Override
    protected ArrayList<BusStop> doInBackground(Void... voids) {

        RouteStops busStops = new RouteStops();
        try {
            ArrayList<BusStop> arrayList=new ArrayList<>();
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("http://transfer.ttc.com.ge:8080/otp/routers/ttc/routeInfo?routeNumber=1&type=bus")
                    .build();
            Response response = client.newCall(request).execute();
            String xmldata = response.body().string();
            busStops = new Gson().fromJson(xmldata, RouteStops.class);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return busStops.getRouteStops();
    }

    @Override
    protected void onPostExecute(ArrayList<BusStop> busStops) {
        if(callBack!=null){
            callBack.onDataReceived(busStops);
        }
    }

    public interface CallBack{
        void onDataReceived(ArrayList<BusStop> busStops);
    }
    public void setCallBack(CallBack callBack){
        this.callBack=callBack;
    }
}