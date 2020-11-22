package ex1.src;

import java.io.Serializable;
import java.util.Objects;

public class EdgeInfo implements edge_info, Serializable {


    private double weight;
    private String info;

//constractor 
    public EdgeInfo(double weight)
    {
        this.weight = weight;
    }
    
    //get and set 
    @Override
    public void setInfo(String s) {
        info = s;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public String getInfo() {
        return null;
    }

   

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EdgeInfo edgeInfo = (EdgeInfo) o;
        return Double.compare(edgeInfo.weight, weight) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight);
    }
}
