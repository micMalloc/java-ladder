package ladder.legacy.domain;

import java.util.ArrayList;
import java.util.List;

import static ladder.utils.RandomBoolGenerator.generate;

public class Line {

    private final List<Point> points;

    public Line(List<Point> points) {
        this.points = points;
    }

    public static Line of(Participants participants) {
        List<Point> points = new ArrayList<>();

        points.add(Point.ofFirstPoint(generate()));
        while (!participants.isLastParticipant(points.size() + 1)) {
            Point nextPoint = points.get(points.size() - 1).getNext();
            points.add(nextPoint);
        }
        boolean nextLeft = points.get(points.size() - 1).getRight();
        points.add(Point.ofLastPoint(nextLeft));

        return new Line(points);
    }

    public static Line of(List<Point> points) {
        return new Line(points);
    }

    public Point getPoint(int pos) {
        if (pos >= points.size() || pos < 0) {
            throw new IndexOutOfBoundsException("라인의 범위를 초과하였습니다.");
        }
        return points.get(pos);
    }

    public List<Point> getPoints() {
        return points;
    }

    public int move(int position) {
        int direction = points.get(position).move();
        return position + direction;
    }
}
