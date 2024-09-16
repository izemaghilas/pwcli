package dz.izemaghilas.cmd;

public class UnknownCmdOptionException extends Exception {
    private final String cmdName;
    private final String cmdOption;

    public UnknownCmdOptionException(String cmdName, String cmdOption) {
        super();
        this.cmdName = cmdName;
        this.cmdOption = cmdOption;
    }

    @Override
    public String toString() {
        return String.format("pwcli: '%s' unrecognized option '%s'. Try 'pwcli help' for more information", this.cmdName, this.cmdOption);
    }
}
