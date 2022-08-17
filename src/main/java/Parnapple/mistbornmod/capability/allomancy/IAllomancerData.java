package Parnapple.mistbornmod.capability.allomancy;

import Parnapple.mistbornmod.util.Metal;

public interface IAllomancerData {

    boolean hasPower(Metal metal);

    boolean isBurning(Metal metal);

    boolean toggleBurn(Metal metal);

    boolean isAllomancer();

    Metal[] getPowers();

    void givePower(Metal metal);

    void giveAllPowers();

    void removePower(Metal metal);

    void removeAllPowers();

}
