package main;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class IOPair {
    private final PrintWriter out;
    private final BufferedReader in;

    public IOPair(PrintWriter out, BufferedReader in) {
        this.out = out;
        this.in = in;
    }


    public PrintWriter getOut() {
        return out;
    }

    public BufferedReader getIn() {
        return in;
    }
}
