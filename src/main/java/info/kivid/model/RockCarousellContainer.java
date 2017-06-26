package info.kivid.model;

import java.util.ArrayList;
import java.util.List;

public class RockCarousellContainer {

    private List<List<ImageApiResult>> containers;
    private int  n;

    public RockCarousellContainer(List<ImageApiResult> images,int n) {
        this.containers = new ArrayList<List<ImageApiResult>>();
        if (images!=null) {
            if (images.size() > n) {
                int contNum = Math.floorDiv(images.size(), n);
                images.addAll(images);
                for (int i = 0; i <= contNum; i++) {
                    this.containers.add(images.subList(i * n, i * n + n));
                }
            } else {
                this.containers.add(images);
            }
        } else {
            this.containers = null;
        }
    }

    public List<List<ImageApiResult>> getContainers() {
        return containers;
    }

}
