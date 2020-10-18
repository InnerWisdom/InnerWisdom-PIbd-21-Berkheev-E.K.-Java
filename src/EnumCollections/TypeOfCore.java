package EnumCollections;

public enum TypeOfCore {
    rectangle,
    oval,
    combined;

    public static TypeOfCore getType(int digit) {
        switch (digit) {
            case 0:
                return TypeOfCore.rectangle;
            case 1:
                return TypeOfCore.oval;
            case 2:
                return TypeOfCore.combined;
        }
        return null;
    }
}
