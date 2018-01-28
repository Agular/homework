package streetnetwork;

/**
 * Author of streetnetwork solution: Samed Düzçay
 * Graph solution taken from his GitHub: https://gist.github.com/smddzcy/bf8fc17dedf4d40b0a873fc44f855a58
 * Renamed Edge to Street.
 */

public class Street {
    Intersection i1, i2;
    String name;
    boolean isBroken;

    public Street(Intersection i1, Intersection i2, String name) {
        this(i1, i2, false, name);
    }

    public Street(Intersection i1, Intersection i2, boolean isBroken, String name) {
        super();
        this.i1 = i1;
        this.i2 = i2;
        this.name = name;
        this.isBroken = isBroken;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Street)) return false;

        Street _obj = (Street) obj;
        return _obj.i1.equals(i1) && _obj.i2.equals(i2) &&
                _obj.isBroken == isBroken && _obj.name.equals(name);
    }

    @Override
    public int hashCode() {
        int result = i1.hashCode();
        result = 31 * result + i2.hashCode();
        result = 31 * result + Boolean.hashCode(isBroken);
        result = 31 * result + name.hashCode();
        return result;
    }

    public boolean isBroken(){
        return isBroken;
    }

    @Override
    public String toString(){
        return name;
    }
}
