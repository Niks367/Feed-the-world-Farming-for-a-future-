package main;

class World {
  Space entry;
  
  World () {
    Space entry    = new Space("Entry");
    Space corridor = new Space("Corridor");
    Space cave     = new Space("Cave");
    Space pit      = new Space("Darkest Pit");
    Space outside  = new Space("Outside");
    
    entry.addEdge("door", farm);
    farm.addEdge("door", city);
    city.addEdge("north", pit);
    city.addEdge("south", outside);
    pit.addEdge("door", city);
    outside.addEdge("door", city);
    
    this.entry = entry;
  }
  
  Space getEntry () {
    return entry;
  }
}

