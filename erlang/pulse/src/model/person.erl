-module(person, [Id, Email, FirstName, LastName, PwdHash]).
-define(SECRET_STRING, "Don't tell anyone!").
-compile(export_all).

session_identifier()->
    mochihex:to_hex(erlang:md5(?SECRET_STRING ++ Id)).

check_password(Password) ->
    Salt = mochihex:to_hex(erlang:md5(Email)),
    user_lib:hash_password(Password, Salt) =:= binary_to_list(PwdHash).

login_cookies()->
    [ mochiweb_cookies:cookie("user_id", Id, [{path, "/"}]),
        mochiweb_cookies:cookie("session_id", session_identifier(), [{path, "/"}]) ].

show_name()->
  binary_to_list(FirstName) ++ " " ++ string:substr(binary_to_list(LastName), 1, 1) ++ ".".

validation_tests() ->
  [{fun() -> length(Email) > 0 end,
    "Please enter a email"},
  {fun() -> length(FirstName) > 0 end,
    "Please enter a first name"},
  {fun() -> length(LastName) > 0 end,
    "Please enter a last name"},
  {fun() -> length(PwdHash) > 0 end,
    "Please enter a password"},
  {fun() -> boss_db:find(person, [{email, Email}], 1) == [] end,
    "Please choose a different email"}].
