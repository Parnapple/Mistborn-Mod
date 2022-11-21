package Parnapple.mistbornmod.client;

import Parnapple.mistbornmod.util.Metal;

import java.util.Arrays;

public class ClientAllomancyData {
    private static final int[] stores = new int[Metal.values().length];
    private static final boolean[] burning = new boolean[Metal.values().length];
    private static final boolean[] powers = new boolean[Metal.values().length];
    private static int goldCompassPos = 0;
    private static int electrumCompassPos = 0;

    public static void set(Metal metal, int store) {
        stores[metal.getIndex()] = store;
    }

    public static void setBurning(Metal metal, boolean burn) {
        burning[metal.getIndex()] = burn;
    }

    public static int get(Metal metal) {
        return stores[metal.getIndex()];
    }

    public static boolean isBurning(Metal metal) {
        return burning[metal.getIndex()];
    }

    public static Metal[] getPowers() {
        return Arrays.stream(Metal.values()).filter(ClientAllomancyData::hasPower).toArray(Metal[]::new);
    }

    public static boolean hasPower(Metal metal) {
        return powers[metal.getIndex()];
    }

    public static void givePower(Metal metal) {
        powers[metal.getIndex()] = true;
    }

    public static void removeAllPowers() {
        Arrays.fill(powers, false);
    }

    public static int getGoldCompassPos() {
        return goldCompassPos;
    }

    public static int getElectrumCompassPos() {
        return electrumCompassPos;
    }

    public static void setElectrumCompassPos(int pos) {
        electrumCompassPos = pos;
    }

    public static void setGoldCompassPos(int pos) {
        goldCompassPos = pos;
    }


}
