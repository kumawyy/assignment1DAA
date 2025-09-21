import java.util.Arrays;

class Point {
    int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class ClosestPair {
    int maxDepth = 0;

    double closest(Point[] points, int depth) {
        maxDepth = Math.max(maxDepth, depth);

        Point[] px = points.clone();
        Arrays.sort(px, (a, b) -> a.x - b.x);

        Point[] py = px.clone();
        Arrays.sort(py, (a, b) -> a.y - b.y);

        return closestUtil(px, py, 0, px.length - 1, depth + 1);
    }

    double closestUtil(Point[] px, Point[] py, int l, int r, int depth) {
        if (r - l <= 3) return bruteForce(px, l, r);

        int mid = (l + r) / 2;
        Point midPoint = px[mid];

        Point[] pyl = Arrays.stream(py).filter(p -> p.x <= midPoint.x).toArray(Point[]::new);
        Point[] pyr = Arrays.stream(py).filter(p -> p.x > midPoint.x).toArray(Point[]::new);

        double dl = closestUtil(px, pyl, l, mid, depth + 1);
        double dr = closestUtil(px, pyr, mid + 1, r, depth + 1);

        double d = Math.min(dl, dr);

        Point[] strip = Arrays.stream(py).filter(p -> Math.abs(p.x - midPoint.x) < d).toArray(Point[]::new);
        return Math.min(d, stripClosest(strip, d));
    }

    double stripClosest(Point[] strip, double d) {
        double min = d;
        for (int i = 0; i < strip.length; i++) {
            for (int j = i + 1; j < strip.length && (strip[j].y - strip[i].y) < min; j++) {
                min = Math.min(min, distance(strip[i], strip[j]));
            }
        }
        return min;
    }

    double bruteForce(Point[] p, int l, int r) {
        double min = Double.MAX_VALUE;
        for (int i = l; i <= r; i++) {
            for (int j = i + 1; j <= r; j++) {
                min = Math.min(min, distance(p[i], p[j]));
            }
        }
        return min;
    }

    double distance(Point p1, Point p2) {
        return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) +
                (p1.y - p2.y) * (p1.y - p2.y));
    }
}
