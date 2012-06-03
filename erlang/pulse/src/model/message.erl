-module(message, [Id, MessageText, CreatedAt, MessageId, PersonId, PersonName]).
-compile(export_all).
-has({messages, many}).
-has({person, 1}).

validation_tests() ->
    [{fun() -> length(MessageText) > 0 end, 
        "Message must be non-empty!"}].