package Mazez;

import java.util.ArrayList;

public class Route implements IRoute {

        private Pawn p;
        private ArrayList<Move> route;
        private ArrayList<int[]> numroute;

        public Route(Pawn pa){
            this.p = pa;
            numroute = new ArrayList<>();
            numroute.add(p.position());
            this.route = new ArrayList<>();
        }

        public int getCol() {
            int a = numroute.get(numroute.size())[1];
            return a;
        }

        public int getRow() {
            int a = numroute.get(numroute.size())[0];
            return a;
        }

        public int getCol(int i) {
            int a = numroute.get(i)[1];
            return a;
        }

        public int getRow(int i) {
            int a = numroute.get(i)[0];
            return a;
        }

        public int length() {
            return route.size();
        }

        public void move(Move m) {
            route.add(m);
            numroute.add(p.position());
        }

        boolean haveBeen(int row, int col){
            return numroute.contains(new int[]{getCol(), getRow()});
        }
    }
