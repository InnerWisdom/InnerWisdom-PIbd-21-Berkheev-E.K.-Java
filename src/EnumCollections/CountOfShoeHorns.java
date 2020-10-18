package EnumCollections;

public enum CountOfShoeHorns {
    one,
    three,
    two;

    public static CountOfShoeHorns getCount(int digit) {
        switch (digit) {
            case 0:
                return CountOfShoeHorns.one;
            case 1:
                return CountOfShoeHorns.two;
            case 2:
                return CountOfShoeHorns.three;
        }
        return null;
    }
}
