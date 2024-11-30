@startuml
allowmixing

package "ProjectDatabase" {
  class Database <<(D,#FFAAAA)>> {
    + Tables
  }
}

package "Utility" {
  class ConnectDB {
    + getConnection(): Connection
  }
}

package "Entity Layer" {
  class Entity <<entity>> {
    + Represents database rows
  }
}

package "DAO Layer" {
  class DAO <<dao>> {
    + Handles database queries
    + CRUD operations
  }
}

package "Service Layer" {
  class Service <<service>> {
    + Business logic
    + Calls DAO layer
  }
}

package "Controller Layer" {
  class Controller <<controller>> {
    + Handles user requests
    + Calls Service layer
  }
}

package "View Layer" {
  class View <<view>> {
    + User Interface
    + Calls Controller layer
  }
}

' Relationships
ProjectDatabase <-- ConnectDB : "connects to Database"
ConnectDB <-- DAO : "provides connection"
DAO --> Entity : "Maps to Entity"
Service --> DAO : "calls"
Controller --> Service : "calls"
View --> Controller : "Handles User Interaction"

@enduml