package Mazez;

/**
 * Created by PC on 11/06/2017.
 */
public class Route implements IRoute {

        private Move[] route;

        public int getCol() {
            return 1;
        }

        public int getRow() {
            return 2;
        }

        public int getCol(int i) {
            return 3;
        }

        public int getRow(int i) {
            return 4;
        }

        public int length() {
            return 5;
        }

        public void move(Move m) {
        }
    }
