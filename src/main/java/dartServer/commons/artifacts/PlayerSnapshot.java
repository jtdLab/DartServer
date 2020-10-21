package dartServer.commons.artifacts;

import dartServer.commons.validators.Username;
import dartServer.gameengine.lobby.Player;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.Objects;

public class PlayerSnapshot {

    @Username
    private final String name;

    @NotNull
    private final Boolean isNext;

    private final int lastThrow;

    @PositiveOrZero
    private final int pointsLeft;

    @PositiveOrZero
    private final int dartsThrown;

    private final int sets;

    @PositiveOrZero
    private final int legs;

    @NotNull
    private final String average;

    @NotNull
    private final String checkoutPercentage;

    public PlayerSnapshot(Player player) {
        name = player.getName();
        isNext = player.isNext();
        lastThrow = player.getLastThrow();
        pointsLeft = player.getPointsLeft();
        dartsThrown = player.getDartsThrown();
        sets = player.getSets();
        legs = player.getLegs();
        average = player.getAverage();
        checkoutPercentage = player.getCheckoutPercentage();
    }

    public String getName() {
        return name;
    }

    public Boolean isNext() {
        return isNext;
    }

    public int getLastThrow() {
        return lastThrow;
    }

    public int getPointsLeft() {
        return pointsLeft;
    }

    public int getDartsThrown() {
        return dartsThrown;
    }

    public int getSets() {
        return sets;
    }

    public int getLegs() {
        return legs;
    }

    public String getAverage() {
        return average;
    }

    public String getCheckoutPercentage() {
        return checkoutPercentage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerSnapshot that = (PlayerSnapshot) o;
        return lastThrow == that.lastThrow &&
                pointsLeft == that.pointsLeft &&
                dartsThrown == that.dartsThrown &&
                sets == that.sets &&
                legs == that.legs &&
                Objects.equals(name, that.name) &&
                Objects.equals(isNext, that.isNext) &&
                Objects.equals(average, that.average) &&
                Objects.equals(checkoutPercentage, that.checkoutPercentage);
    }

}
