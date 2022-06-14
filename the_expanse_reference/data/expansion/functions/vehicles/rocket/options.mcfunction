scoreboard players set @s optn_cooldown 100
scoreboard players enable @s rocket_optns
tellraw @s ["",{"text":"\n"},{"selector":"@s"},{"text":"'s "},{"translate":"exp_vehicles_rocket_name"},{"text":"\n--------------\n"},{"translate":"exp_screentxt_demount_chat","color":"dark_red","clickEvent":{"action":"run_command","value":"/trigger rocket_optns set 2"}},{"text":"\n--------------\n "}]
