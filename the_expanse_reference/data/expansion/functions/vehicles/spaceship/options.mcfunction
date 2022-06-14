scoreboard players set @s optn_cooldown 100
scoreboard players enable @s spaceship_optns
scoreboard players reset @s demount_optns
tellraw @s ["",{"text":"\n"},{"selector":"@s"},{"text":"'s "},{"translate":"exp_vehicles_spaceship_name"},{"text":"\n---------------------\n"},{"translate":"exp_screentxt_enter_chat","color":"green","clickEvent":{"action":"run_command","value":"/trigger spaceship_optns set 1"}},{"text":" "},{"translate":"exp_screentxt_fuel_chat","color":"gold","clickEvent":{"action":"run_command","value":"/trigger spaceship_optns set 2"}},{"text":" ","color":"gold"},{"translate":"exp_screentxt_demount_chat","color":"dark_red","clickEvent":{"action":"run_command","value":"/trigger spaceship_optns set 3"}},{"text":"\n---------------------\n "}]
