package business.implementations;

import business.interfaces.Field;

public class FieldImplementation implements Field {
    private final String[] fieldArray = new String[]{
            "Phosphorus is crucial to all life on Earth, and it’s irreplaceable in food production.",
            "One of the problems that comes with the importance of phosphorus, is that the raw material reserves are unevenly distributed globally.",
            "Phosphorus access is a matter of survival for humans and nations.",
            "Most countries, including Denmark and Europe, rely heavily on phosphorus imports.",
            "The geopolitical aspects of phosphorus distribution affect global food chains.",
            "The concept of 'peak phosphorus' highlights when global phosphorus production will reach its maximum, after which supply adjustment becomes challenging.",
            "The depletion of reserves in major countries within 40 years could significantly impact future political dynamics and potentially lead to resource conflicts.",
            "Phosphorus also plays a significant role in freshwater quality, with losses from agriculture leading to pollution.",
            "The EU's focus on circular economy and initiatives like phosphorus recycling from wastewater",
            "The term “peak phosphorus” is the term used to predict the maximum production of phosphorus.",
            "It is predicted that maximum production of phosphorus will be around 2030, raising concerns about future phosphorus availability for agriculture. "
    };
    @Override
    public String visitFields() {
        return (this.fieldArray[(int) (Math.random() * 11)]);
    }
}
