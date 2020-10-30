
% [db]. command for loading the database

% loves is a predicate
% romeo, juliet are atoms and they must start with lowercase letter
loves(romeo, juliet).

% this is a rule, where ':-' can be thought of as 'if' 
loves(juliet, romeo) :- 
    \+loves(romeo, juliet).

%facts and rules are also reffered to as clauses

%variables always start with uppercase letter
go_swim(peter).
go_swim(susie) :- 
    go_swim(peter).

% listing(go_swim) to print to console all atoms that "go to swim"

car(audi).
car(jaguar).
car(aston_martin).
car(seat).
car(bmw).
car(skoda).

pc(asus).
pc(hp).
pc(lenovo).

% conditionals

% same relationsiphs must be always one after the other
owns_car(philip, audi).
owns_car(ellen, seat).
owns_car(joe, jaguar).
owns_car(albert, jaguar).

owns_pc(ellen, hp).
owns_pc(joe, asus).
owns_pc(ellen, asus).
owns_pc(albert, hp).

fancy(X) :-
    owns_car(X, jaguar),  % comma is AND
    owns_pc(X, hp).

still_fancy(Y) :-
    owns_car(Y, jaguar);  % semi-colon is OR
    owns_car(Y, audi).

what_grade(5) :- 
    write('Grade 5').

what_grade(4) :-
    write('Grade 4').

what_grade(Other) :-
    format('Grade ~w', [Other]).

%structures
vertical_line(point(X, Y), point(X, Y2)).

% {trace} for debugging
% notrace. for stopping the debugger

% count to N
count_to_n(N, N) :-
    write(N), nl.

count_to_n(N, I) :-
    write(I), nl,
    J is I + 1,
    count_to_n(N, J).
