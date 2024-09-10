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