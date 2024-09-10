Heuristics
==========

- Does this class (or method) have one and only one reason to change?
- Does my design allow for future extensions without modifying the code that already exists?
- Can my subclass be substituted for its superclass under reasonable usage of the superclasses public API?
- Is my subclass really a superclass?
- Are my subclasses preconditions stronger than its superclass?
- Are my subclasses post conditions weaker than its superclass?
- Does my subclass maintain the invariant of the superclass?
- Are the return types of my subclasses methods less constrained than its superclass?
- Do my subclasses methods accept a more constrained type than its superclass?
- Does my subclass throw a new exception that is not expected from existing class hierarchy?
- Does my interface have extraneous methods that are not required by all implementing classes?
- Does my class or method depend on a concrete implementation that is subject to change?

*Other considerations...*

- Is my code reasonably efficient?
- Is my code reusable?
- Are the types of my variables, constructor and method arguments as general as possible?
- Is inheritance really necessary or can I use composition?
- How many other classes need to be ported for me to reuse a single class?
- Does this code vary for each instance; if so, can it be encapsulated?