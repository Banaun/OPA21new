package opa21thefarm;

public class Farmer extends Person {

    private Barn myBarn;
    private int churn = 0;
    private double pork = 0;

    public Farmer(String name) {

        super(name);
        myBarn = new Barn();
    }

    public Barn getMyBarn() {

        return myBarn;
    }

    public int getChurn() {

        return churn;
    }

    public double getPork() {

        return pork;
    }

    public String getMilkFromCow(Cow cow) {

        churn += cow.milkCow();

        return cow.getName() + " was milked for " + cow.getMilkAmount() + "L.\n";

        /*Cow chosenCow = (Cow) myBarn.getAnimalFromList(nameOfCow);

        if (chosenCow == null) {

            return "There is no cow in the barn named " + "'" + nameOfCow + "'!";
        }
        else {

            churn += chosenCow.milkCow();

            return nameOfCow + " was milked for " + chosenCow.getMilkAmount() + "L.";
        }*/
    }

    public String getPorkFromPig(Pig pig) {

        pork += pig.slaughterPig();

        myBarn.removeAnimalFromBarn(pig);

        return pig.getName() + " was slaughtered for " + pig.getPorkAmount() + "Kg.";

        /*Pig chosenPig = (Pig) myBarn.getAnimalFromList(nameOfPig);

        if (chosenPig == null) {

            return "There is no pig in the barn named " + "'" + nameOfPig + "'!";
        }
        else {

            pork += chosenPig.slaughterPig();

            myBarn.removeAnimalFromBarn(chosenPig);

            return nameOfPig + " was slaughtered for " + chosenPig.getPorkAmount() + "Kg.";
        }*/
    }
}
