-module(problem1).
-export([solve/1]).

% solve(9)   => 23
% solve(999) => 233168
solve(N) ->
  lists:sum([ X || X <- lists:seq(1, N), X rem 5 =:= 0 orelse X rem 3 =:= 0]).
