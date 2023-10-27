package business.implementations;

import business.interfaces.Player;
import business.interfaces.Seed;
import business.interfaces.Time;

public class SeedImplementation implements Seed {
    int profit = 0;
    int seedAmount = 1; //Har kun sat den til én, da vi skal have fundet ud af hvor mange frø der skal involveres.
    int valueSeed = 100; //Ved ikke hvad værdien af vores frø skal være.
    int daysToRipe = 1; //Bruges til "isSeedRipe" metoden, tænker at frøene skal være modne hver dag.
    private Player player;
    private Time time;// Kalder på player for at få adgang til money.

    // Construktor til at opdatere Player.
    public SeedImplementation(Player player){
        this.player = player;
    }

    // Metoden til at udregne vores spillers profit, tænker at udregningen giver lidt sig selv.

    @Override
    public int calculateProfit() {
        profit = valueSeed * seedAmount;

        return profit;
    }

    /* Metoden tager udgangpunkt i at hvis isSeedRipe er true, så henter den calculateprofit, til at udregne
        hvor mange penge spilleren får. Den antager også at vi opretter en metode i player, som hedder addMoney.
        Jeg tænkte det kunne være en nem måde at gøre det på, men ved ikke om jeg tager fejl.
         */
    @Override
    public void sendMoney(){
        if(isSeedRipe()) {
            int profit = calculateProfit();
            player.addMoney(profit);
        }
    }
    /*metode til at fortælle om frøene er modne, den tager udgangspunkt i vores "time" klasse,
        så frøene bliver modne hver dag. Jeg bruger midlertidigt Time som parameter, fordi jeg stadig er usikker på om,
        vi skal have en seperat klasse for time, eller om vi bare skal have den implementeret i de klasser der nu skal bruge,
        tiden til at udløse noget.
         */
    @Override
    public boolean isSeedRipe() {
        return time.getDaysPassed() >= daysToRipe;
    }



}
