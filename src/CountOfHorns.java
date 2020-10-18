public enum CountOfHorns {
    one,
    three,
    two;

    public static CountOfHorns getCount(int digit) {
        switch (digit) {
            case 1:
                return CountOfHorns.one;
            case 2:
                return CountOfHorns.two;
            case 3:
                return CountOfHorns.three;
        }
        return null;
    }
}
