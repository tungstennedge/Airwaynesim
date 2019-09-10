public class airlineRoute extends Route{
Route baseRoute = null;
Airport stop1;
Airport stop2;
airlineRoute(Route baseRoute, String airline) {
    baseRoute = this.baseRoute;
}
airlineRoute(Route baseRoute, Airport stop1, String airline){
    baseRoute = this.baseRoute;
    stop1 = this.stop1;

}
    airlineRoute(Route baseRoute, Airport stop1, Airport stop2, String airline){
        baseRoute = this.baseRoute;
        stop1 = this.stop1;
        stop2 = this.stop2;
    }
}
