{-# OPTIONS_GHC -Wall #-}
module LogAnalysis where
import Log

parseMessage :: String -> LogMessage
parseMessage str =
    let tokens = words str
    in 
        case tokens of
        ("E":level:time:rest) -> 
            LogMessage (Error (read level)) (read time) (unwords rest)
        ("I":time:rest) ->
            LogMessage Info (read time) (unwords rest)
        ("W":time:rest) ->
            LogMessage Warning (read time) (unwords rest)
        _ -> Unknown (unwords tokens)

parse :: String -> [LogMessage]
parse input = 
    let 
        line = lines input
    in
        map parseMessage line

getMessageTime :: LogMessage -> TimeStamp
getMessageTime (LogMessage (Error _) time _) = time
getMessageTime (LogMessage _ time _) = time

insert :: LogMessage -> MessageTree -> MessageTree
insert (Unknown _) tree = tree
insert message Leaf     = Node Leaf message Leaf
insert message (Node left nodeMessage right) =
  let messageTime = getMessageTime message
      nodeMessageTime = getMessageTime nodeMessage
  in
    if messageTime < nodeMessageTime
    then Node (insert message left) nodeMessage right
    else Node left nodeMessage (insert message right)

-- insert message tree = 
--     case message tree of
--         (Unknown _) tree -> tree
--         LogMessage MessageType TimeStamp String Leaf ->
--             (Node Leaf message Leaf)
--         LogMessage (Node left nodeMessage right) -> 
--             let
--                 messageTime = getMessageTime nowMessage
--                 nodeTime = getMessageTime nodeMessage
--             in
--                 if messageTime < nodeTime
--                 then Node (insert message left) nodeMessage right
--                 else Node left nodeMessage (insert message right) 

-- insert message tree
--     |   Unknown tree = tree
--     |   LogMessage Leaf =
--             (Node Leaf message Leaf)
--     |   LogMessage (Node left nodeMessage right) = 
--             let
--                 messageTime = getMessageTime LogMessage
--                 nodeTime = getMessageTime nodeMessage
--             in
--                 if messageTime < nodeTime
--                 then Node (insert message left) nodeMessage right
--                 else Node left nodeMessage (insert message right) 


build :: [LogMessage] -> MessageTree
build [] = Leaf
build (x:xs) = insert x (build xs)

inOrder :: MessageTree -> [LogMessage]
inOrder tree = 
    case tree of
        Leaf -> []
        (Node left node right) -> (inOrder left)++[node]++(inOrder right)

whatWentWrong :: [LogMessage] -> [String]
whatWentWrong message= 
    let 
        sortedMessage = inOrder (build  message)
    in
        whatWentWrongHelper sortedMessage

getErrorLevel :: LogMessage -> TimeStamp
getErrorLevel (LogMessage _ time _) = time

getErrorString :: LogMessage -> String
getErrorString (LogMessage _ _ s) = s

whatWentWrongHelper :: [LogMessage] -> [String]
whatWentWrongHelper []      = []
whatWentWrongHelper (x:zs)  = if (getErrorLevel x) >= 50
                            then [(getErrorString x)] ++ whatWentWrongHelper zs
                            else whatWentWrongHelper zs


