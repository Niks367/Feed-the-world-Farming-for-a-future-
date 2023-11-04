package main;

class World {
  Space entry;
  
  World () {
    Space entry    = new Space("Entry");
    Space farm = new Space("Farm");
    Space city     = new Space("DowntownCity");
    Space uni      = new Space("Uni");
    Space shop  = new Space("Shop");
    Space madman  = new Space("MadMan");
    Space fields  = new Space("Fields");
    Space river  = new Space("River");
    
    entry.addEdge("to_farm", farm);
    farm.addEdge("to_river", river);
    river.addEdge("river_to_farm", farm);
    farm.addEdge("to_fields", fields);
    fields.addEdge("fields_to_farm", farm);
    river.addEdge("to_city", city);
    city.addEdge("city_to_river", river);
    city.addEdge("to_uni", uni);
    uni.addEdge("east", city);
    city.addEdge("to_shop", shop);
    shop.addEdge("west", city);
    city.addEdge("to_madman", madman);
    madman.addEdge("north", city);

    
    this.entry = entry;
  }
  
  Space getEntry () {
    return entry;
  }

}

