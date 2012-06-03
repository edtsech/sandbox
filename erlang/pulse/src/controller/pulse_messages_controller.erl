-module(pulse_messages_controller, [Req, SessionID]).
-compile([export_all]).

before_(_) ->
    user_lib:require_login(Req).

index('GET', [], Person)->
    Messages  = boss_db:find(message, []),
    Timestamp = boss_mq:now("new-messages"),
        {ok, [{messages, Messages}, {timestamp, Timestamp}]}.

create('POST', [], Person) ->
    Person      = boss_db:find(Req:cookie("user_id")),
    PersonId    = Req:cookie("user_id"),
    MessageId   = Req:post_param("message_id"),
    MessageText = Req:post_param("message_text"),
    CreatedAt   = current_time(),
    NewMessage  = message:new(id, MessageText, CreatedAt, MessageId, PersonId, Person:show_name()),
    case NewMessage:save() of
        {ok, SavedMessage} ->
            {json, [{status, "ok"}]};
        {error, ErrorList} ->
            % {json, [{errors, ErrorList}, {new_msg, NewMessage}]}
            {json, [{status, "error"}]}
    end.

pull('GET', [LastTimestamp], Person) ->
    {ok, Timestamp, Messages} = boss_mq:pull("new-messages", list_to_integer(LastTimestamp)),
    {json, [{timestamp, Timestamp}, {messages, Messages}]}.

current_time() ->
  {Hours, Minutes, _} = erlang:time(),
  integer_to_list(Hours) ++ ":" ++ integer_to_list(Minutes).
