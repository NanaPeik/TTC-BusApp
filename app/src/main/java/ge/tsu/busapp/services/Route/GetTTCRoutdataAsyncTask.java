package ge.tsu.busapp.services.Route;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import java.util.ArrayList;


public class GetTTCRoutdataAsyncTask extends AsyncTask<Void, Void, ArrayList<Route>> {

    private CallBack callBack;

    @Override
    protected ArrayList<Route> doInBackground(Void... voids) {
        Log.d("doInBackground", Thread.currentThread().getName());
        ArrayList<Route> arrayList = new ArrayList<>();
        try {
            Document document=Jsoup.connect("http://transfer.ttc.com.ge:8080/otp/routers/ttc/routes").get();
            Elements elements = document.getElementsByTag("Route");

            for (int i = 0; i < elements.size(); i++) {
                Element element = elements.get(i);
                Elements RouteNumberTags = element.getElementsByTag("RouteNumber");
                Elements IdTags=element.getElementsByTag("Id");
                Elements StopATags=element.getElementsByTag("StopA");
                Elements StopBTags=element.getElementsByTag("StopB");
                Route route = new Route();
                if (RouteNumberTags.size() > 0) {
                    String routnumber = RouteNumberTags.get(0).text();
                    String id=IdTags.get(0).text();
                    String stopA=StopATags.get(0).text();
                    String stopB=StopBTags.get(0).text();
                    route.setRouteNumber(routnumber);
                    route.setId(id);
                    route.setStopA(stopA);
                    route.setStopB(stopB);
                }
                arrayList.add(route);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    @Override
    protected void onPostExecute(ArrayList<Route> routes) {
        if(callBack!=null){
            callBack.onDataReceived(routes);
        }
    }

    public interface CallBack{
        void onDataReceived(ArrayList<Route> routes);
    }
    public void setCallBack(CallBack callBack){
        this.callBack=callBack;
    }
}