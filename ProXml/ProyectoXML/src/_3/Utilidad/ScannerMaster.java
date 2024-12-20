package _3.Utilidad;

import java.util.Scanner;

public class ScannerMaster {

    private static Scanner scn = new Scanner(System.in);

    public ScannerMaster() {
    }

    public static Scanner getScn() {
        return scn;
    }

    public static void setScn(Scanner scn) {
        ScannerMaster.scn = scn;
    }
}