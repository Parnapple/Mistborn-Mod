package Parnapple.mistbornmod.capability.feruchemy;

import Parnapple.mistbornmod.util.Metal;

public interface IFeruchemistData {

    boolean hasPower(Metal metal);

    boolean isFeruchemist();

    Metal[] getPowers();

    void givePower(Metal metal);

    void giveAllPowers();

    void removePower(Metal metal);

    void removeAllPowers();

    boolean everFeruchemist();

}
