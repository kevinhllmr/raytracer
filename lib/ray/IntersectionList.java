package lib.ray;

import java.util.ArrayList;
import java.util.List;

public class IntersectionList {
    private List<Intersection> intersections;

    public IntersectionList() {
        intersections = new ArrayList<>();
    }

    public void addIntersection(Intersection intersection) {
        int index = 0;
        while (index < intersections.size() && intersection.t() > intersections.get(index).t()) {
            index++;
        }
        
        intersections.add(index, intersection);
    }

    public void addIntersectionList(IntersectionList xs) {

        for(int i = 0; i < xs.size(); i++) {
            Intersection intersection = xs.getIntersection(i);

            int index = 0;
            while (index < intersections.size() && intersection.t() > intersections.get(index).t()) {
                index++;
            }
            
            intersections.add(index, intersection);
        }
    }

    public Intersection getIntersection(int index) {
        if (index >= 0 && index < intersections.size()) {
            return intersections.get(index);
        }

        return null;
    }

    public int size() {
        return intersections.size();
    }

    public Intersection hit() {
        for (Intersection intersection : intersections) {
            if(intersection.t() > 0) {
                return intersection;
            }
        }

        return null;
    }
}
