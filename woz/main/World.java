package main;

class World {
    Space entry;
  World () {
    Space entry    = new Space("Lounge");
    Space farm = new Space("Farm");
    Space city     = new Space("DowntownCity");
    Space uni      = new Space("Uni");
    Space shop  = new Space("Shop");
    Space madman  = new Space("MadMan");
    Space fields  = new Space("Fields");
    Space lake  = new Space("Lake");

    entry.addEdge("to_farm", farm);
    farm.addEdge("to_lake", lake);
    lake.addEdge("lake_to_farm", farm);
    farm.addEdge("to_fields", fields);
    fields.addEdge("fields_to_farm", farm);
    lake.addEdge("to_city", city);
    city.addEdge("city_to_lake", lake);
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

