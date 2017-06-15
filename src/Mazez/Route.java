package Mazez;

import java.awt.*;
import java.util.ArrayList;

public class Route implements IRoute {

        private Pawn p;
        private ArrayList<Move> route;
        ArrayList<Point> casasAntigas = new ArrayList<Point>();

        public Route(Pawn pa){
            this.p = pa;
            casasAntigas = new ArrayList<>();
            casasAntigas.add(new Point(p.position()[0], p.position()[1]));
            this.route = new ArrayList<>();
        }

        public int getCol() {
            int a = (int) casasAntigas.get(length()).getY();
            return a;
        }

        public int getRow() {
            int a = (int) casasAntigas.get(length()).getX();
            return a;
        }

        public int getCol(int i) {
            int a = (int) casasAntigas.get(i).getY();
            return a;
        }

        public int getRow(int i) {
            int a = (int) casasAntigas.get(i).getY();
            return a;
        }

        public int length() {
            return casasAntigas.size();
        }

        public void move(Move m) {
            route.add(m);
            casasAntigas.add(new Point(p.position()[0], p.position()[1]));
        }

    }
