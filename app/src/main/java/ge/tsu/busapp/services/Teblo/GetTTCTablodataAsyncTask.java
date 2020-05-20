package ge.tsu.busapp.services.Teblo;

import android.os.AsyncTask;

import androidx.appcompat.app.ActionBar;

import com.google.gson.JsonElement;
import com.google.gson.TypeAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Objects;

public class GetTTCTablodataAsyncTask extends AsyncTask<Void,Void, ArrayList<Tablo>> {

    private CallBack callBack;

    @Override
    protected ArrayList<Tablo> doInBackground(Void... voids) {
        ArrayList<Tablo> arrayList = new ArrayList<>();
        Tablo tablo=new Tablo();
        tablo.setBusNumber("124");
        tablo.setMinuter("7");
        arrayList.add(tablo);
        tablo.setBusNumber("150");
        tablo.setMinuter("2");
        arrayList.add(tablo);
        return arrayList;
    }

    @Override
    protected void onPostExecute(ArrayList<Tablo> routes) {
        if(callBack!=null){
            callBack.onDataReceived(routes);
        }
    }
    public interface CallBack{
        void onDataReceived(ArrayList<Tablo> tablos);
    }
    public void setCallBack(CallBack callBack){
        this.callBack=callBack;
    }
}