package ge.tsu.busapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ge.tsu.busapp.services.BusStop.BusStop;
import ge.tsu.busapp.services.BusStop.GetTTCStopsdataAsyncTask;
import ge.tsu.busapp.services.Route.GetTTCRoutdataAsyncTask;
import ge.tsu.busapp.services.Route.Route;
import ge.tsu.busapp.services.Teblo.GetTTCTablodataAsyncTask;
import ge.tsu.busapp.services.Teblo.Tablo;

import static ge.tsu.busapp.services.Route.GetTTCRoutdataAsyncTask.*;

public class MainActivity extends Activity {

    private ListView mRoutes;
    private RouteArrayAdapter routeArrayAdapter;
    private BusStopArrayAdapter busStopArrayAdapter;
    private TabloArrayAdapter tabloArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Log.d("onCreate MainActivity",Thread.currentThread().getName());
        mRoutes=findViewById(R.id.routes);
        routeArrayAdapter=new RouteArrayAdapter(this,0,new ArrayList<Route>());
        busStopArrayAdapter=new BusStopArrayAdapter(this,0,new ArrayList<BusStop>());
        tabloArrayAdapter=new TabloArrayAdapter(this,0,new ArrayList<Tablo>());
    }

    /// method for getting routes
    public void getRouts(View view){
        findViewById(R.id.progress).setVisibility(View.VISIBLE);
        GetTTCRoutdataAsyncTask getTTCRoutdataAsyncTask=new GetTTCRoutdataAsyncTask();
        CallBack callBack=new CallBack() {
            @Override
            public void onDataReceived(ArrayList<Route> routes) {
                mRoutes.setAdapter(routeArrayAdapter);
                routeArrayAdapter.addAll(routes);
                findViewById(R.id.progress).setVisibility(View.GONE);
                findViewById(R.id.getRoutBtn).setVisibility(View.GONE);
            }
        };
        getTTCRoutdataAsyncTask.setCallBack(callBack);
        getTTCRoutdataAsyncTask.execute();
    }
    ///////// Array Adapter of Tablo
    class TabloArrayAdapter extends ArrayAdapter<Tablo>{

        private Context context;

        public TabloArrayAdapter(@NonNull Context context, int resource, @NonNull List<Tablo> objects) {
            super(context, resource, objects);
            this.context=context;
        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
            LayoutInflater layoutInflater=(LayoutInflater) context
                    .getSystemService(LAYOUT_INFLATER_SERVICE);
            View view=layoutInflater.inflate(R.layout.view_tablo_item, parent,false);
            final Tablo currentTablo=getItem(position);
            TextView textView=view.findViewById(R.id.busNumber);
            TextView textView1=view.findViewById(R.id.busNumberTime);

            textView.setText(currentTablo.getBusNumber());
            textView1.setText(currentTablo.getMinuter());


            return view;
        }
    }
    ///////////// Bus Stop Array Adapter
    class BusStopArrayAdapter extends ArrayAdapter<BusStop>{
        private Context context;
        public BusStopArrayAdapter(@NonNull Context context, int resource, @NonNull List<BusStop> objects) {
            super(context, resource, objects);
            this.context=context;
        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
            LayoutInflater layoutInflater=(LayoutInflater) context
                    .getSystemService(LAYOUT_INFLATER_SERVICE);
            View view=layoutInflater.inflate(R.layout.view_stop_item, parent,false);
            final BusStop currentBusStop=getItem(position);
            TextView textView=view.findViewById(R.id.stopName);
            TextView textView1=view.findViewById(R.id.stopId);
            TextView textView2=view.findViewById(R.id.stopType);
            TextView textView3=view.findViewById(R.id.stopSequence);

                textView.setText(currentBusStop.getName());
                textView1.setText(currentBusStop.getStopId());
                textView2.setText(currentBusStop.getType());
                textView3.setText(currentBusStop.getSequence());


            view.findViewById(R.id.showTabloItem).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GetTTCTablodataAsyncTask getTTCTablodataAsyncTask=new GetTTCTablodataAsyncTask();
                    GetTTCTablodataAsyncTask.CallBack callBack=new GetTTCTablodataAsyncTask.CallBack() {
                        @Override
                        public void onDataReceived(ArrayList<Tablo> tablos) {
                            mRoutes.setAdapter(tabloArrayAdapter);
                            tabloArrayAdapter.addAll(tablos);
                        }
                    };
                    getTTCTablodataAsyncTask.setCallBack(callBack);
                    getTTCTablodataAsyncTask.execute();
                }
            });
            return view;
        }
    }

    //////////////  Rout Array Adapter
    class RouteArrayAdapter extends ArrayAdapter<Route>{

        private Context context;
        public RouteArrayAdapter(Context context, int resource, List<Route> objects) {
            super(context, resource, objects);
            this.context=context;
        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) context
                    .getSystemService(LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.view_roure_item, parent, false);
            final Route currentRoute = getItem(position);
            TextView textView = view.findViewById(R.id.routeName);
            TextView textView1=view.findViewById(R.id.routeId);
            TextView textView2=view.findViewById(R.id.routeStopA);
            TextView textView3=view.findViewById(R.id.routeStopB);
            textView.setText(currentRoute.getRouteNumber());
            textView1.setText(currentRoute.getId());
            textView2.setText(currentRoute.getStopA());
            textView3.setText(currentRoute.getStopB());

            view.findViewById(R.id.getStops).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    findViewById(R.id.progress).setVisibility(View.VISIBLE);
                    GetTTCStopsdataAsyncTask getTTCStopsdataAsyncTask=new GetTTCStopsdataAsyncTask();
                    GetTTCStopsdataAsyncTask.CallBack callBack=new GetTTCStopsdataAsyncTask.CallBack() {
                        @Override
                        public void onDataReceived(ArrayList<BusStop> busStops) {
                            mRoutes.setAdapter(busStopArrayAdapter);
                            busStopArrayAdapter.addAll(busStops);
                            findViewById(R.id.progress).setVisibility(View.GONE);
                        }
                    };
                    getTTCStopsdataAsyncTask.setCallBack(callBack);
                    // RoutNumber ის გადასაცემად
//                    getTTCStopsdataAsyncTask.execute(Integer.getInteger(currentRoute.getRouteNumber()));
                    getTTCStopsdataAsyncTask.execute();
                    Log.d("content------",currentRoute.getRouteNumber());
                }
            });
            return view;
        }
    }
}
