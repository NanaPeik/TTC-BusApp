package ge.tsu.busapp.services.Route;

public class Route {

    private String RouteNumber;
    private String id;
    private String stopA;
    private String stopB;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStopA() {
        return stopA;
    }

    public void setStopA(String stopA) {
        this.stopA = stopA;
    }

    public String getStopB() {
        return stopB;
    }

    public void setStopB(String stopB) {
        this.stopB = stopB;
    }

    public String getRouteNumber() {
        return RouteNumber;
    }

    public void setRouteNumber(String routeNumber) {
        RouteNumber = routeNumber;
    }
}
