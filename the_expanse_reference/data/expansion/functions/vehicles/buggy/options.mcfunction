scoreboard players set @s optn_cooldown 100
scoreboard players enable @s buggy_optns
tellraw @s ["",{"text":"\n"},{"selector":"@s"},{"text":"'s "},{"translate":"exp_vehicles_buggy_name"},{"text":"\n--------------\n"},{"translate":"exp_screentxt_fuel_chat","color":"gold","clickEvent":{"action":"run_command","value":"/trigger buggy_optns set 1"}},{"text":" ","color":"gold"},{"translate":"exp_screentxt_demount_chat","color":"dark_red","clickEvent":{"action":"run_command","value":"/trigger buggy_optns set 2"}},{"text":"\n--------------\n "}]
