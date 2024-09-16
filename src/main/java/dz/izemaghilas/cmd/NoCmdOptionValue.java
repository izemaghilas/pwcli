package dz.izemaghilas.cmd;

public class NoCmdOptionValue extends Exception {
    private final String cmdName;
    private final String cmdOption;

    public NoCmdOptionValue(String cmdName, String cmdOption) {
        this.cmdName = cmdName;
        this.cmdOption = cmdOption;
    }

    @Override
    public String toString() {
        return String.format("pwcli: '%s' missing value '%s'. Try 'pwcli help' for more information", this.cmdName, this.cmdOption);
    }
}
