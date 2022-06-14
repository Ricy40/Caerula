scoreboard players set #global dimension_test 0
execute in expansion:storage run scoreboard players set #global dimension_test 1
execute if score #global dimension_test matches 0 run tellraw @a [{"translate":"exp_screentxt_loaderror_chat"}]
