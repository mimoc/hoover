# hoover
Vacuum cleaner that must follow a trajectory and clean the spots


Program provides a REST service that computes the number of patches that a vacuum cleaner wipes when following a set of instructions and also save into a database the input and output of the service.

Based on the coordinates,"NNNSSNEEW" and the initial location of the vacuum cleaner, a path is buing built, consisting only of the start and end point of continuous same coordinates, ex:NNN SS N EE W. This coordinates are being stored in a LinkedList . 

Each Patch point is run trough the LinkedList of paths . If coordinates of the patch is within the boundaries of the path, then the patch is removed.
