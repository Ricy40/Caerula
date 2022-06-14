execute if score @s cw_hp_old matches 1.. run function expansion:handy_tools/custom_damage/return_health
execute if score @s[gamemode=!creative,gamemode=!spectator] exp_damage matches 1.. run function expansion:handy_tools/custom_damage/deal_damage
scoreboard players set @s exp_damage 0